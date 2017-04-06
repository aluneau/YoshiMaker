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
        WIDTH = 50, 
        HEIGHT = 50;
    protected static float 
        DENSITY = 1f, 
        FRICTION = 0.9f, 
        RESTITUTION = 0.4f;
    
    /**
     * Boite
     * @param x
     * @param y
     * @throws SlickException 
     */
    public Box(int x, int y) throws SlickException {
        //Initialisation
        super("./resources/box.png");
        //Coordonnées
        setX(x).setY(y).setWidth(WIDTH).setHeight(HEIGHT);
        //Défintion de la physique
        physics
            .at(x, y)
            .hitbox(width, height)
            .fixtures(DENSITY, FRICTION, RESTITUTION)
            .create();
    }
}
