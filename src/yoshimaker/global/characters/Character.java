/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package yoshimaker.global.characters;

import org.jbox2d.callbacks.ContactImpulse;
import org.jbox2d.callbacks.ContactListener;
import org.jbox2d.collision.Manifold;
import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.BodyType;
import org.jbox2d.dynamics.contacts.Contact;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import yoshimaker.global.Entity;
import yoshimaker.physics.Physics;

/**
 *
 * @author punpun
 */
public abstract class Character extends Entity {
    protected boolean jump = true ;
    
    /**
     * Personnage
     * @param files
     * @throws SlickException 
     */
    public Character(String... files) throws SlickException {
        //Initialiastion
        super(files);
        //Référencement
        ENTITIES.add(this);
        //Corps dynamique
        physics.define(BodyType.DYNAMIC);
    }
    
    public Character(Image... files) throws SlickException {
        //Initialiastion
        super(files);
        //Référencement
        ENTITIES.add(this);
        //Corps dynamique
        physics.define(BodyType.DYNAMIC);
    }

    @Override
    public String toString() { return "character" ; }
    
    public void die() {
        this.destroy();
    }
}
