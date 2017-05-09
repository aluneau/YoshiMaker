/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package yoshimaker.global.characters.ennemies;

import org.newdawn.slick.Animation;
import org.newdawn.slick.SlickException;
import yoshimaker.global.Entity;
import yoshimaker.global.cases.Type;
import static yoshimaker.global.characters.ennemies.Goomba.WALKING;
import static yoshimaker.global.characters.ennemies.Goomba.RETURN;
import yoshimaker.map.Map;


/**
 *
 * @author punpun
 */


public class ParaGoomba extends Goomba {
 
    private Animation walkingL,walkingR;
    private int where = getX();
    private int mouvement =2 ;
    private Animation sens;
    public ParaGoomba(int x, int y) throws SlickException {
       super(x, y);
       this.walkingL = new Animation(WALKING, 2, 0, 4, 0, true, 1, true);
       this.walkingR = new Animation(RETURN,2 ,0 ,4 ,0, true ,1 ,true); 
       sens = walkingR;
       this.sprite = sens;     
       this.sprite.setSpeed(0.01f);
    }
    
    static {
        RESTITUTION = 0.95f;
    }
    
    @Override
     public Entity update(){
        if (destroyed) { return this ; }
        setX((int) physics.x()).setY((int) physics.y());
        if (physics.getBody() == null) { return this; }
            physics.moveX(mouvement);
            this.sprite = sens;
        if( getX() < where-250 ){
            sens = walkingR;
            mouvement = 2;        
        }else if( getX() > where+250 ){ 
            sens = walkingL;
            this.sprite.setSpeed(0.01f);
            mouvement =-2;
        }
        if (Map.CURRENT == null) { return this ; }        
        if (Map.CURRENT.whatIs(getX()+getHeight()+75, getY()) != Type.EMPTY ){
            sens = walkingL;
            this.sprite.setSpeed(0.01f);            
            mouvement = -2;            
        }       
        if(Map.CURRENT.whatIs(getX()+getHeight()-75, getY()) != Type.EMPTY ) {
            sens = walkingR;            
            mouvement = 2;
        }        
        return this;
     }
    
}

     