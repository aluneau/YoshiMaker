/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package yoshimaker.global.items;

import org.newdawn.slick.Animation;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;
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
    private Animation animation;
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
        //this.animation = new Animation(new SpriteSheet("./assets/images/test/block1.png", 64, 64,1),100);
        //this.sprite = this.animation;
        //this.sprite.start();
        setX(x).setY(y).setWidth(WIDTH).setHeight(HEIGHT);
        //Défintion de la physique
        physics
            .at(x, y)
            .hitbox(width/2, height/2)
            .fixtures(DENSITY, FRICTION, RESTITUTION)
            .create();
    }
}
