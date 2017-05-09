/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package yoshimaker.global.items;

import org.jbox2d.dynamics.BodyType;
import org.newdawn.slick.Animation;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;
import yoshimaker.global.Entity;
import static yoshimaker.global.Entity.ENTITIES;
import yoshimaker.global.cases.DoorBrick;
import static yoshimaker.global.items.FireBall.FIREBALL;

/**
 *
 * @author punpun
 */
public class Switch extends Item {
    protected Animation open, closed;
    /**
     * Objet
     * @param files
     * @throws org.newdawn.slick.SlickException
     */
    public Switch(int x, int y) throws SlickException {
        //Initialisation
        super("./assets/images/test/button.png");
        //Coordonnées
        this.open = new Animation();
        this.open.addFrame(SPRITESHEET.getSprite(0, 0), 1000);
        this.closed = new Animation();
        this.closed.addFrame(SPRITESHEET.getSprite(1, 0), 1000);
        this.sprite = open;
        this.sprite.start();
        setX(x).setY(y).setWidth(WIDTH).setHeight(HEIGHT);
        //Défintion de la physique
        physics
            .at(x, y)
            .define(BodyType.STATIC)
            .hitbox(width/2, height/2)
            .fixtures(DENSITY, FRICTION, RESTITUTION)
            .data(this)
            .create();     
    }
    
    protected static SpriteSheet SPRITESHEET;
    static {
        //Initalisation
        try {
            SPRITESHEET = new SpriteSheet("./assets/images/test/button.png", 48, 63, 1);
        } catch (Exception ignore) { }
    }
     protected static float 
        DENSITY = 1f, 
        FRICTION = 0.7f, 
        RESTITUTION = 0f;
         protected static int 
        WIDTH = 64, 
        HEIGHT = 64;
    @Override
    public String toString() {
        return "item";
    }
    
    public void enable() {
        this.sprite = closed;
        DoorBrick.opened = true ;
    }
    
    public void disable() {
        this.sprite = open;
        DoorBrick.opened = false ;
    }
    
    @Override
    public Entity update(){
        super.update();
        return this; 
    }
    
   
}
