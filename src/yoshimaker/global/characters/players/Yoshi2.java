    /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package yoshimaker.global.characters.players;

import org.newdawn.slick.*;
import yoshimaker.global.items.FireBall;

/**
 *
 * @author punpun
 */
public class Yoshi2 extends Player {
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
    private Sound saut;
    private Sound fireball;

    /**
     * Yoshi
     * @param x
     * @param y
     * @throws SlickException
     */
    public Yoshi2(int x, int y) throws SlickException {
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
        saut = new Sound("sounds/saut.wav");
        fireball = new Sound("sounds/fireball.wav");

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
    @Override public void key_o(){
        try {
            if (fired >= 1) { return ; } else { ++fired; }
            FireBall f = new FireBall(getX(), getY());
            f.setCreator(this);
            fireball.play(1f, 0.6f);
            yoshimaker.physics.Timer.add(f, 500);
        } catch (SlickException ex) { }
    }
    /**
     * Callback à la touche Q
     */
    @Override
    public void key_j() {
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
    public void key_l() {
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
    public void key_i() {
        saut.play(1.1f,0.4f);
        jump(false);
    }

    @Override
    public void key_space() {
    }
    
    protected static SpriteSheet SPRITESHEET;
    protected static SpriteSheet RETURNO;
    protected static SpriteSheet STATUS_RIGHT;
    protected static SpriteSheet STATUS_LEFT;
    static {
        //Initalisation
        try {
            SPRITESHEET = new SpriteSheet("./assets/right2.png", 32, 32,0);
            RETURNO = new SpriteSheet("./assets/left2.png", 32, 32,0);
            STATUS_RIGHT = new SpriteSheet("./assets/stopRight2.png",32,32,0);
            STATUS_LEFT = new SpriteSheet("./assets/stopLeft2.png",32,32,0);        
        } catch (Exception ignore) { }
    }
    
    public void draw(GameContainer container, Graphics g){
        super.draw(container, g);
    }
    
}
