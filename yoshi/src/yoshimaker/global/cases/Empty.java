/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package yoshimaker.global.cases;

import org.newdawn.slick.SlickException;
import static yoshimaker.global.cases.Case.SPRITESHEET;

/**
 *
 * @author punpun
 */
public class Empty extends Case {
    protected static float 
        DENSITY = 1f, 
        FRICTION = 1f, 
        RESTITUTION = 0f;
    protected static int
        TILE_X = 0,
        TILE_Y = 0;
    
    /**
     * Yoshi
     * @param x
     * @param y
     * @throws SlickException 
     */
    public Empty(int x, int y) throws SlickException {
        //Initialisation
        super(SPRITESHEET.getSprite(TILE_X, TILE_Y));
        //Coordonnées
        setX(x).setY(y).setWidth(WIDTH).setHeight(HEIGHT);
    }
}
