/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package yoshimaker.global.cases;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

/**
 *
 * @author punpun
 */
public class Brick extends Case {
    protected static float 
        DENSITY = 1f, 
        FRICTION = 0.7f, 
        RESTITUTION = 0f;
    protected static int
        TILE_X = 5,
        TILE_Y = 0;
    
    /**
     * Yoshi
     * @param x
     * @param y
     * @throws SlickException 
     */
    public Brick(int x, int y) throws SlickException {
        //Initialisation
        super(SPRITESHEET.getSprite(TILE_X, TILE_Y));
        //Coordonnées
        setX(x).setY(y).setWidth(WIDTH).setHeight(HEIGHT);
        //Définition de la physique
        physics
            .at(x*64, y*64)
            .hitbox(width/2, height/2)
            .fixtures(DENSITY, FRICTION, RESTITUTION)
            .create();
        update();
    }
}
