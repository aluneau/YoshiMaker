/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package yoshimaker.global.characters.players;

import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;

/**
 *
 * @author punpun
 */
public class Yoshi extends Player {
    protected static int 
        WIDTH = 52, 
        HEIGHT = 70;
    protected static float 
        DENSITY = 1f, 
        FRICTION = 0.7f, 
        RESTITUTION = 0f;
    
    private int jumped = 0 ;
    
    /**
     * Yoshi
     * @param x
     * @param y
     * @throws SlickException 
     */
    public Yoshi(int x, int y) throws SlickException {
        //Initialisation
        super(SPRITESHEET.getSprite(0, 1), SPRITESHEET.getSprite(1, 1), SPRITESHEET.getSprite(2, 1), SPRITESHEET.getSprite(3, 1), SPRITESHEET.getSprite(4, 1),
                RETURNO.getSprite(0, 1), RETURNO.getSprite(1, 1), RETURNO.getSprite(2, 1), RETURNO.getSprite(3, 1),RETURNO.getSprite(4, 1));
        sprite.stop();
        //Coordonnées
        setX(x).setY(y).setWidth(WIDTH).setHeight(HEIGHT);
        //Défintion de la physique
        physics
            .at(x, y)
            .hitbox(width/2, height/2)
            .fixtures(DENSITY, FRICTION, RESTITUTION)
            .create();
    }
    
    public void no_key(){
        sprite.stop();
        physics.translate(0,0);
    }
    
    /**
     * Callback à la touche Q
     */
    @Override
    public void key_q() {
        physics.translate(-10, 0);
        sprite.start();
        sprite.setCurrentFrame(5);
        sprite.stopAt(10);
        sprite.setSpeed(0.001f);            
    }
    
    /**
     * Callback à la touche D
     */
    @Override
    public void key_d() {
        physics.translate(+10, 0);
        sprite.start();
        sprite.setSpeed(0.01f);

    }
    
    /**
     * Callback à la touche Espace
     */
    @Override
    public void key_space() {
        if (jump) {
            jump = false ;  
            physics.impulse(0, -30);
        }
        jump = true;
    }
    
    
    protected static SpriteSheet SPRITESHEET;
    protected static SpriteSheet RETURNO;
    static {
        //Initalisation
        try {
            SPRITESHEET = new SpriteSheet("./assets/yoshi.png", 32, 32, 0);
            RETURNO = new SpriteSheet("./assets/demi-tour.png", 32, 32, 0);
        } catch (Exception ignore) { }
    }
}
