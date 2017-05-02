/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package yoshimaker.global.characters.players;

import org.newdawn.slick.Animation;
import org.newdawn.slick.Music;
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
    
    private boolean saveDirection = false;
    
 
    private Animation left;
    private Animation right;
    private Animation stopLeft;
    private Animation stopRight;
    private Music saut;

    
    /**
     * Yoshi
     * @param x
     * @param y
     * @throws SlickException 
     */
    public Yoshi(int x, int y) throws SlickException {
        //Initialisation
        super(STATUS_RIGHT.getSprite(0, 0));
        this.stopLeft = new Animation(STATUS_LEFT,1);
        this.stopRight = new Animation(STATUS_RIGHT,1);
        this.left = new Animation(RETURNO,1);
        this.right = new Animation(SPRITESHEET,1);
        sprite.stop();
        //Coordonnées
        setX(x).setY(y).setWidth(WIDTH).setHeight(HEIGHT);
        //Défintion de la physique
        physics
            .at(x, y)
            .hitbox(width/2, height/2)
            .fixtures(DENSITY, FRICTION, RESTITUTION)
            .data(this)
            .create();
        setSpawn((int)physics.at().x, (int)physics.at().y);
        saut = new Music("sounds/saut.wav");
    }
    
    @Override 
    public void no_key(){
        physics.translate(0,0);        
        if(saveDirection == true){
            sprite = stopLeft;
            sprite.start();
            sprite.setSpeed(0.01f);              
        }else {
            sprite = stopRight;
            sprite.start();
            sprite.setSpeed(0.01f);              
        }                  
    }
    
    /**
     * Callback à la touche Q
     */
    @Override
    public void key_q() {
        super.key_q();
        saveDirection = true;
        physics.moveX(-10);
        sprite = this.left;
        sprite.start();
        sprite.setSpeed(0.01f);            
    }
    
    /**
     * Callback à la touche D
     */
    @Override
    public void key_d() {
        super.key_d();
        saveDirection = false;
        physics.moveX(+10);
        sprite = this.right;
        sprite.start();
        sprite.setSpeed(0.01f);

    }
    
    /**
     * Callback à la touche Espace
     */
    @Override
    public void key_space() {
            saut.play();

        jumped = false;
        jump(false);
    }
    
    
    protected static SpriteSheet SPRITESHEET;
    protected static SpriteSheet RETURNO;
    protected static SpriteSheet STATUS_RIGHT;
    protected static SpriteSheet STATUS_LEFT;
    static {
        //Initalisation
        try {
            SPRITESHEET = new SpriteSheet("./assets/right.png", 32, 32,0);
            RETURNO = new SpriteSheet("./assets/left.png", 32, 32,0);
            STATUS_RIGHT = new SpriteSheet("./assets/stopRight.png",32,32,0);
            STATUS_LEFT = new SpriteSheet("./assets/stopLeft.png",32,32,0);        
        } catch (Exception ignore) { }
    }
    
}
