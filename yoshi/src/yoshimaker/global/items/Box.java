/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package yoshimaker.global.items;

import org.jbox2d.dynamics.BodyType;
import org.newdawn.slick.SlickException;

/**
 *
 * @author punpun
 */
public class Box extends Item {
    protected static int 
        WIDTH = 64, 
        HEIGHT = 64;
    protected static float 
        DENSITY = 3f, 
        FRICTION = 1f, 
        RESTITUTION = 0f;
    protected static int
        TILE_X = 2,
        TILE_Y = 0;
    
    /**
     * Boite
     * @param x
     * @param y
     * @throws SlickException 
     */
    public Box(int x, int y) throws SlickException {
        //Initialisation
        super(SPRITESHEET.getSprite(TILE_X, TILE_Y));
        //Coordonnées
        setX(x).setY(y).setWidth(WIDTH).setHeight(HEIGHT);
        //Défintion de la physique
        physics
            .at(x, y)
            .hitbox(width/2, height/2)
            .fixtures(DENSITY, FRICTION, RESTITUTION)
            .create();
    }
}