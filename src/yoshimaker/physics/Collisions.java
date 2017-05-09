package yoshimaker.physics;

import org.jbox2d.callbacks.ContactImpulse;
import org.jbox2d.callbacks.ContactListener;
import org.jbox2d.collision.Manifold;
import org.jbox2d.dynamics.BodyType;
import org.jbox2d.dynamics.contacts.Contact;
import yoshimaker.global.Entity;
import yoshimaker.global.characters.ennemies.Ennemy;
import yoshimaker.global.characters.ennemies.Thwomp;
import yoshimaker.global.characters.players.Player;
import yoshimaker.global.items.Shell;
import yoshimaker.global.items.Star;

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
        //System.out.println(a);
        //System.out.println(b);
        if ((a == null)||(b == null)) { return ; }
        
        if (((a instanceof Thwomp)&&(b instanceof yoshimaker.global.characters.Character))||((b instanceof Thwomp)&&(a instanceof yoshimaker.global.characters.Character))) {
            Thwomp t = (Thwomp) ((a instanceof Thwomp) ? a : b);
            yoshimaker.global.characters.Character e = (yoshimaker.global.characters.Character) ((a instanceof Thwomp) ? b : a);
            //Note axe des y inversés
            if (t.getY()+(t.getHeight()/2) < e.getY()) { e.die(); }
        }
        
        
        if (((a instanceof Player)&&(b instanceof Ennemy))||((b instanceof Player)&&(a instanceof Ennemy))) {
            Player p = (Player) ((a instanceof Player) ? a : b);
            Ennemy e = (Ennemy) ((a instanceof Player) ? b : a);
            //Note axe des y inversés
            //System.out.println("p:"+p.getY()+" ph:"+p.getHeight()+" e:"+e.getY()+" eh:"+e.getHeight()+" ph2:"+(p.getY()+(p.getHeight()/2)));
            if (p.getY()+(p.getHeight()/2) < e.getY()) { if (e.isKillable()) { e.die() ; p.jump(true); }} else { p.die(); }
        }
        
        //Shell
        if (((a instanceof Player)&&(b instanceof Shell))||((b instanceof Player)&&(a instanceof Shell))) {
            Player p = (Player) ((a instanceof Player) ? a : b);
            Shell e = (Shell) ((a instanceof Player) ? b : a);
            //Note axe des y inversés
            if (p.getY()+(p.getHeight()/2) < e.getY()) { e.spinning(!e.spinning()) ; p.jump(true); }
        }
        
        if (((a instanceof Shell)&&(b instanceof yoshimaker.global.characters.Character))||((b instanceof Shell)&&(a instanceof yoshimaker.global.characters.Character))) {
            Shell t = (Shell) ((a instanceof Shell) ? a : b);
            yoshimaker.global.characters.Character e = (yoshimaker.global.characters.Character) ((a instanceof Shell) ? b : a);
            //Note axe des y inversés
            if (t.getY()+(t.getHeight()/2) < e.getY()) { e.die(); }
        }

        //Star
        if (((a instanceof Player)&&(b instanceof Star))||((b instanceof Player)&&(a instanceof Star))) {
            Player p = (Player) ((a instanceof Player) ? a : b);
            Star e = (Star) ((a instanceof Player) ? b : a);

            System.out.println("Star");
            if(!e.destroyed) {
                e.destroy();
                Player.countStar--;
            }

            System.out.println(Player.countStar);



        }
        
    }
    
    @Override
    public void endContact(Contact contact) {}
    @Override
    public void preSolve(Contact contact, Manifold oldManifold) {}
    @Override
    public void postSolve(Contact contact, ContactImpulse impulse) {}

}
