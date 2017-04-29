/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package yoshimaker.global.characters.ennemies;

import org.jbox2d.dynamics.BodyType;
import org.jbox2d.dynamics.contacts.Contact;
import org.newdawn.slick.Animation;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;
import yoshimaker.global.Entity;
import yoshimaker.global.characters.players.Player;

/**
 *
 * @author punpun
 */
public class Goomba extends Ennemy {
    
     public Goomba(int x, int y) throws SlickException {
        //Initialisation
        super(WALKING.getSprite(0, 0));
        this.sprite = new Animation(WALKING, 0, 0, 1, 0, true, 1, true);
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
    }
       
    protected static int 
        WIDTH = 52, 
        HEIGHT = 70;
    protected static float 
        DENSITY = 1f, 
        FRICTION = 0.7f, 
        RESTITUTION = 0f;
    
    private int jumped = 0 ;
    private Animation walking;
    
    protected static SpriteSheet WALKING;
    static {
        //Initalisation
        try {
            WALKING = new SpriteSheet("./assets/goombas.png", 64, 64, 0);
        } catch (Exception ignore) { }
    }
}
