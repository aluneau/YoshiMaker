/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package yoshimaker.global.characters.ennemies;

import java.util.logging.Level;
import java.util.logging.Logger;
import org.newdawn.slick.Animation;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;
import yoshimaker.global.Entity;
import yoshimaker.global.cases.Type;
import yoshimaker.global.items.Shell;
import yoshimaker.map.Map;

/**
 *
 * @author punpun
 */
public class Koopa extends Ennemy {
    private Animation walkingL,walkingR;
    private int where = getX();
    private int mouvement = 3 ;
    private Animation sens;
    
    
    public Koopa(int x, int y) throws SlickException {
        //Initialisation
        super(WALKING.getSprite(0, 0));
        this.walkingR = new Animation(WALKING, 0, 0, 1, 0, true, 1, true);
        this.walkingL = new Animation(RETURN,0 ,0 ,1 ,0, true ,1 ,true);
        sens = walkingR;
        this.sprite = sens;       
        sprite.setSpeed(0.01f);   
        sprite.start();
        //Coordonnées
        setX(x).setY(y).setWidth(WIDTH).setHeight(HEIGHT);
        //Défintion de la physique
        physics
            .at(x, y)
            .hitbox(width/2, height/2-10)
            .fixtures(DENSITY, FRICTION, RESTITUTION)
            .data(this)
            .create();
        killable = true ;
    }
       
    protected static int 
        WIDTH = 48, 
        HEIGHT = 64;
    protected static float 
        DENSITY = 1f, 
        FRICTION = 0.7f, 
        RESTITUTION = 0f;
    
    private Animation walking;
    
    protected static SpriteSheet WALKING;
    protected static SpriteSheet RETURN;
    static {
        //Initalisation
        try {
            WALKING = new SpriteSheet("./assets/koopa.png", 48, 64, 0);
            RETURN = new SpriteSheet("./assets/koopaRetourner.png",48,64,0);
        } catch (Exception ignore) { }
    }
    
    @Override
    public void die() {
        super.die();
        try {
            new Shell(getX(), getY());
        } catch (SlickException ex) {
            Logger.getLogger(Koopa.class.getName()).log(Level.SEVERE, null, ex);
        }        
    }
    @Override
     public Entity update(){
        if (destroyed) { return this ; }
        setX((int) physics.x()).setY((int) physics.y());
        if (physics.getBody() == null) { return this; }
            physics.moveX(mouvement);
            this.sprite = sens;
        if( getX() < where-500 ){
            sens = walkingR;
            sprite.setSpeed(0.01f);   
            mouvement = 3;         
        }else if( getX() > where+500 ){ 
            sens = walkingL;
            sprite.setSpeed(0.01f);    
            mouvement =-3;
        }
        
        if (Map.CURRENT == null) { return this ; }
            if (Map.CURRENT.whatIs(getX()+getWidth()+100, getY()) != Type.EMPTY  ){  
                System.out.println(" left ");
                sens = walkingL;
                sprite.setSpeed(0.01f);   
                mouvement = -3;            
            }      
            if(Map.CURRENT.whatIs(getX()+getWidth()-100, getY()) != Type.EMPTY ) { 
                System.out.println(" right ");
                sens = walkingR;
                sprite.setSpeed(0.01f);   
                mouvement = 3;
            }
        return this;
    }  
    
    
}
