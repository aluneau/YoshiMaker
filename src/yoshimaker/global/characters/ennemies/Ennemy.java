/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package yoshimaker.global.characters.ennemies;

import java.util.HashSet;
import org.jbox2d.dynamics.BodyType;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import yoshimaker.global.characters.players.Player;

/**
 *
 * @author punpun
 */
public abstract class Ennemy extends yoshimaker.global.characters.Character {
    /**
     * Liste des joueurs
     */
    protected final static HashSet<Ennemy> ENNEMIES = new HashSet();
   
    
    /**
     * Joueur
     * @param files
     * @throws SlickException 
     */
    public Ennemy(String... files) throws SlickException {
        //Initialiastion
        super(files);
        //Référencement
        ENNEMIES.add(this);
        //Corps dynamique
        physics.define(BodyType.DYNAMIC);
    }
    
    public Ennemy(Image... files) throws SlickException {
        //Initialiastion
        super(files);
        //Référencement
        ENNEMIES.add(this);
        //Corps dynamique
        physics.define(BodyType.DYNAMIC);
    }
    
    
    @Override
    public void destroy() {
        ENNEMIES.remove(this);
        super.destroy();
    }
}
