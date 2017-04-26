/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package yoshimaker.global.items;

import org.jbox2d.dynamics.BodyType;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;
import yoshimaker.global.Entity;

/**
 *
 * @author punpun
 */
public abstract class Item extends Entity {
    
    /**
     * Objet
     * @param files
     * @throws org.newdawn.slick.SlickException
     */
    public Item(String... files) throws SlickException {
        //Initialiastion
        super(files);
        //Référencement
        ENTITIES.add(this);
        //Corps dynamique
        physics.define(BodyType.DYNAMIC);
    }
    
    public Item(Image... files) throws SlickException {
        //Initialiastion
        super(files);
        //Référencement
        ENTITIES.add(this);
        //Corps dynamique
        physics.define(BodyType.DYNAMIC);
    }
    
    protected static SpriteSheet SPRITESHEET;
    static {
        //Initalisation
        try {
            SPRITESHEET = new SpriteSheet("./assets/tileset1.png", 64, 64, 1);
        } catch (Exception ignore) { }
    }
}
