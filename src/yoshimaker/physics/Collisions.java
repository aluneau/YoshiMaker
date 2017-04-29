package yoshimaker.physics;

import org.jbox2d.callbacks.ContactImpulse;
import org.jbox2d.callbacks.ContactListener;
import org.jbox2d.collision.Manifold;
import org.jbox2d.dynamics.BodyType;
import org.jbox2d.dynamics.contacts.Contact;
import yoshimaker.global.Entity;
import yoshimaker.global.characters.ennemies.Ennemy;
import yoshimaker.global.characters.players.Player;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author punpun
 */
public class Collisions  implements ContactListener  {
    
        
    @Override
    public void beginContact(Contact contact) {
        Entity a = (Entity) contact.getFixtureA().getBody().getUserData();
        Entity b = (Entity) contact.getFixtureB().getBody().getUserData();
        
        if ((a == null)||(b == null)) { return ; }
        
        
        if (((a instanceof Player)&&(b instanceof Ennemy))||((b instanceof Player)&&(a instanceof Ennemy))) {
            Player p = (Player) ((a instanceof Player) ? a : b);
            Ennemy e = (Ennemy) ((a instanceof Player) ? b : a);
            //Note axe des y inversés
            System.out.println("p:"+p.getY()+" ph:"+p.getHeight()+" e:"+e.getY()+" eh:"+e.getHeight()+" ph2:"+(p.getY()+(p.getHeight()/2)));
            if (p.getY()+(p.getHeight()/2) < e.getY()) { e.die() ; p.jump(true);} else { p.die(); }
        }
        
        //if (contact.getFixtureB().getBody().getType() == BodyType.STATIC) { }
         Entity entity = (Entity) contact.getFixtureB().getBody().getUserData();

        if (entity instanceof Player) { System.out.println("ouch"); }
    
    }
    
    @Override
    public void endContact(Contact contact) {}
    @Override
    public void preSolve(Contact contact, Manifold oldManifold) {}
    @Override
    public void postSolve(Contact contact, ContactImpulse impulse) {}

}
