/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package yoshimaker.global.characters.ennemies;

import org.jbox2d.dynamics.BodyType;
import org.newdawn.slick.Animation;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;
import yoshimaker.global.Entity;
import yoshimaker.global.characters.players.Player;

/**
 *
 * @author punpun
 */
public class Boo extends Ennemy {
    public Boo(int x, int y) throws SlickException {
        //Initialisation
        super(SPRITESHEET.getSprite(0, 0));
        this.walking = new Animation(SPRITESHEET, 0, 0, 1, 0, true, 1, true);
        this.hidden = new Animation(SPRITESHEET, 2, 0, 2, 0, true, 1, true);
        this.sprite = this.walking;
        sprite.setSpeed(0.01f);   
        sprite.start();
        //Coordonnées
        setX(x).setY(y).setWidth(WIDTH).setHeight(HEIGHT);
        //Défintion de la physique
        physics
            .at(x, y)
            .define(BodyType.KINEMATIC)
            .hitbox(width/2, height/2-10)
            .fixtures(DENSITY, FRICTION, RESTITUTION)
            .data(this)
            .create();
    }
    
    @Override
     public Entity update(){
        if (destroyed) { return this ; }
        setX((int) physics.x()).setY((int) physics.y());
        //
            for (Player p : Player.PLAYERS) {
                if ((Math.abs(p.getY()-getY()) < 200)&&(
                        ((p.getX() < getX())&&(p.getDirection().equals("right")))
                        ||((p.getX() > getX())&&(p.getDirection().equals("left")))
                    )) { 
                    this.sprite =  this.hidden;
                } else { this.sprite = this.walking ; }
            }
        return this;
    }
       
    protected static int 
        WIDTH = 52, 
        HEIGHT = 70;
    protected static float 
        DENSITY = 0f, 
        FRICTION = 0f, 
        RESTITUTION = 0f;
    
    private Animation walking, hidden;
    
    protected static SpriteSheet SPRITESHEET;
    static {
        //Initalisation
        try {
            SPRITESHEET = new SpriteSheet("./assets/boo.png", 64, 64, 0);
        } catch (Exception ignore) { }
    }
}
