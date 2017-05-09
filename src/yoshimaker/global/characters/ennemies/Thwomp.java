/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package yoshimaker.global.characters.ennemies;

import org.jbox2d.collision.shapes.MassData;
import org.jbox2d.dynamics.BodyType;
import org.newdawn.slick.Animation;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;
import yoshimaker.global.Entity;
import yoshimaker.global.cases.Type;
import yoshimaker.global.characters.players.Player;
import yoshimaker.map.Map;

/**
 *
 * @author punpun
 */
public class Thwomp extends Ennemy {
    private int sx, sy, state = 0;
    private Player trigger = null;
    
    public Thwomp(int x, int y) throws SlickException {
        //Initialisation
        super(SPRITESHEET.getSprite(0, 0));
        this.sleeping = new Animation(SPRITESHEET, 0, 0, 0, 0, true, 1, true);
        this.falling = new Animation(SPRITESHEET, 1, 0, 1, 0, true, 1, true);
        this.sprite = this.sleeping;
        sprite.setSpeed(0.01f);   
        sprite.start();
        //Coordonnées
        sx = x; sy = y;
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
    
    protected static int 
        WIDTH = 96, 
        HEIGHT = 124;
    protected static float 
        DENSITY = 1f, 
        FRICTION = 1f, 
        RESTITUTION = 0f;
    
    private Animation sleeping, falling;
    
    protected static SpriteSheet SPRITESHEET;
    static {
        //Initalisation
        try {
            SPRITESHEET = new SpriteSheet("./assets/thwomp.png", 64, 82, 0);
        } catch (Exception ignore) { }
    }
    
    
    public Entity update(){
        if (destroyed) { return this ; }
        setX((int) physics.x()).setY((int) physics.y());
        //
        if (physics.getBody() == null) { return this; }
        if (state < 0) { state++; return this ;}
        physics.move(0, 0);
        boolean player = false;
        if(Map.CURRENT == null) { return this ; }
            for (Player p : Player.PLAYERS) {
                if ((trigger != null )&&(p != trigger)) { continue; }
                if (((Math.abs(p.getY()-getY()) < 400)&&(Math.abs(p.getX()-getX()) < 120)&&(state == 0))||(state == 1)) {
                    trigger = p;
                    this.sprite =  this.falling;
                    state = 1 ;
                    if (Map.CURRENT.whatIs(getX(), getY()+getHeight()) == Type.EMPTY) {
                        physics.move(0, 27);
                    } else { state = 2 ; }
                } else { 
                    this.sprite = this.sleeping;
                    if ((!((Math.abs(p.getY()-getY()) < 400)&&(Math.abs(p.getX()-getX()) < 120)))) {
                        state = 3;
                    }                  
                    if (state == 3) {
                        if (getY() > sy) { physics.move(0, -8); }
                        else { state = -5 ;}
                    }
                }
            }
        return this;
    }
}
