/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package yoshimaker.global.items;

import org.jbox2d.dynamics.BodyType;
import org.newdawn.slick.SlickException;
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
}
