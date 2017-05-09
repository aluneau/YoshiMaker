/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package yoshimaker.global.items;

import org.newdawn.slick.Animation;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;
import yoshimaker.global.Entity;
import static yoshimaker.global.items.Item.SPRITESHEET;

/**
 *
 * @author punpun
 */
public class Shell extends Item {
    protected static SpriteSheet SHELL;
    
    static {
        //Initalisation
        try {
            SHELL = new SpriteSheet("./assets/koopa_down.png", 64, 46, 0);
        } catch (Exception ignore) { }
    }
    
    protected static int 
        WIDTH = 64, 
        HEIGHT = 46;
    protected static float 
        DENSITY = 3f, 
        FRICTION = 1f, 
        RESTITUTION = 0f;
    protected static int
        TILE_X = 0,
        TILE_Y = 0;
    private Animation inactive, active;
    protected boolean spinning = false;
    public int direction = 1;
    /**
     * Boite
     * @param x
     * @param y
     * @throws SlickException 
     */
    public Shell(int x, int y) throws SlickException {
        //Initialisation
        super(SHELL.getSprite(TILE_X, TILE_Y));
        //Coordonnées
        this.inactive = new Animation(SHELL, 0, 0, 0, 0, true, 1, true);
        this.active = new Animation(SHELL, 1, 0, 2, 0, true, 1, true);
        this.sprite = this.inactive;
        this.sprite.start();
        setX(x).setY(y).setWidth(WIDTH).setHeight(HEIGHT);
        //Défintion de la physique
        physics
            .at(x, y)
            .hitbox(width/2, height/2)
            .fixtures(DENSITY, FRICTION, RESTITUTION)
            .data(this)
            .create();
    }
    
    public void spinning(boolean v, int d) {
        spinning = v;
        direction = d;
        this.sprite = spinning ? active : inactive;
    }
    
    public boolean spinning() {
        return spinning;
    }
    
    @Override
    public Entity update(){
        super.update();
        if (spinning()) {
            physics.moveX(direction*10);
        }
        return this; 
    }

    @Override
    public String toString() {
        return "shell";
    }
}
