/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package yoshimaker.global.cases;

import org.newdawn.slick.Animation;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;
import static yoshimaker.global.cases.Case.SPRITESHEET;

/**
 *
 * @author punpun
 */
public class Lava extends Case {
    protected static float 
        DENSITY = 1f, 
        FRICTION = 0f, 
        RESTITUTION = 0f;
    protected static int
        TILE_X = 1,
        TILE_Y = 4;
    
    /**
     * Yoshi
     * @param x
     * @param y
     * @throws SlickException 
     */
    public Lava(int x, int y) throws SlickException {
        //Initialisation
        super();
        this.sprite = new Animation(SPRITE_LAVA, 0, 0, 10, 0, true, 1, true);
        sprite.setSpeed(0.01f);
        sprite.start();
        //Coordonnées
        setX(x).setY(y).setWidth(WIDTH).setHeight(HEIGHT);
        //Définition de la physique
        physics
            .at(x*width, y*height)
            .hitbox(width/2, height/2)
            .fixtures(DENSITY, FRICTION, RESTITUTION).data(this)
            .create();
        update();
        type = Type.LAVA;
    }
    
     protected static SpriteSheet SPRITE_LAVA;
    
    static {
        //Initalisation
        try {
            SPRITE_LAVA = new SpriteSheet("./assets/lava.png", 90, 90, 0);
           } catch (Exception ignore) { }
    }
}
