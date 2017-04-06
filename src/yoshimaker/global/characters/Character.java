/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package yoshimaker.global.characters;

import org.jbox2d.dynamics.BodyType;
import org.newdawn.slick.SlickException;
import yoshimaker.global.Entity;

/**
 *
 * @author punpun
 */
public abstract class Character extends Entity {
    /**
     * Personnage
     * @param files
     * @throws SlickException 
     */
    public Character(String... files) throws SlickException {
        //Initialiastion
        super(files);
        //Corps dynamique
        physics.define(BodyType.DYNAMIC);
    }
}
