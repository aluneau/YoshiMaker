/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package yoshimaker.global.characters.ennemies;

import static java.lang.Math.sqrt;
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
public class Boo extends Ennemy {
    public Boo(int x, int y) throws SlickException {
        //Initialisation
        super(SPRITESHEET.getSprite(0, 0));
        this.walkingL = new Animation(SPRITESHEET, 0, 0, 1, 0, true, 1, true);
        this.walkingR = new Animation(RETURNO,0 ,0 ,1 ,0, true ,1 ,true);
        this.hiddenL = new Animation(SPRITESHEET, 2, 0, 2, 0, true, 1, true);
        this.hiddenR = new Animation(RETURNO, 2, 0, 2, 0, true, 1, true);
        this.sprite = this.hiddenR;
        sprite.setSpeed(0.01f);
        sprite.start();
        //Coordonnées
        setX(x).setY(y).setWidth(WIDTH).setHeight(HEIGHT);
        //Défintion de la physique
        physics
            .at(x, y)
            .define(BodyType.KINEMATIC, 2)
            .hitbox(width/2, height/2-10)
            .fixtures(DENSITY, FRICTION, RESTITUTION)
            .data(this)
            .create();
        killable = true;
    }
    
    @Override
     public Entity update(){
        if (destroyed) { return this ; }
        setX((int) physics.x()).setY((int) physics.y());
        if (physics.getBody() == null) { return this; }
            for (Player p : Player.PLAYERS) {
                if (Math.abs(p.getY()-getY())< 200 && 
                        ((p.getX() < getX())&&(p.getDirection().equals("right")) || 
                        (p.getX() > getX())&&(p.getDirection().equals("left")))){
                    if((p.getX() < getX())&&(p.getDirection().equals("right"))){
                        this.sprite =  this.hiddenL;
                        //Collisions activé
                        physics.getBody().getFixtureList().m_filter.categoryBits = 1;
                        physics.getBody().getFixtureList().m_filter.maskBits = 1;
                        physics.move(0, 0);                            
                    }
                    else if((p.getX() > getX())&&(p.getDirection().equals("left"))) { 
                        this.sprite =  this.hiddenR;
                        //Collisions activé
                        physics.getBody().getFixtureList().m_filter.categoryBits = 1;
                        physics.getBody().getFixtureList().m_filter.maskBits = 1;
                        physics.move(0, 0);    
                    }
                } else { 
                    physics.getBody().getFixtureList().m_filter.categoryBits = 1;
                    physics.getBody().getFixtureList().m_filter.maskBits = 2;
                    if( p.getX() > getX() && p.getY() < getY() ){physics.move(5, -5); this.sprite = this.walkingR; sprite.setSpeed(0.01f); } // detection haut droit
                    else if( p.getX() > getX() && p.getY() > getY()){ physics.move(5, 5); this.sprite = this.walkingR; sprite.setSpeed(0.01f);} // detection bas droit*/
                    else if( p.getX() < getX() && p.getY() > getY()) {physics.move(-5, 5);this.sprite = this.walkingL;sprite.setSpeed(0.01f);} //detection bas gauche 
                    else if( p.getX() < getX() && p.getY() < getY()) {physics.move(-5, -5);this.sprite = this.walkingL;sprite.setSpeed(0.01f);} //detection haut gauche
                    /*if (Map.CURRENT.whatIs(getX(), getY()+getHeight()+100) != Type.EMPTY && Map.CURRENT.whatIs(getX()+getWidth()+100, getY()) != Type.EMPTY ) {                      
                        physics.move(5 , 0);                
                    } */         
                }
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
    
    private Animation walkingL,walkingR, hiddenL, hiddenR;
    
    protected static SpriteSheet SPRITESHEET;
    protected static SpriteSheet RETURNO;
    
    static {
        //Initalisation
        try {
            SPRITESHEET = new SpriteSheet("./assets/boo.png", 64, 64, 0);
            RETURNO = new SpriteSheet("./assets/booRetourner.png",64,64,0);
        } catch (Exception ignore) { }
    }
}
