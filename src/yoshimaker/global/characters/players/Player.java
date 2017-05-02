/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package yoshimaker.global.characters.players;

import java.util.HashSet;
import org.jbox2d.dynamics.BodyType;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import yoshimaker.global.Entity;

/**
 *
 * @author punpun
 */
public abstract class Player extends yoshimaker.global.characters.Character {
    /**
     * Liste des joueurs
     */
    protected final static HashSet<Player> PLAYERS = new HashSet();
   
    
    /**
     * Joueur
     * @param files
     * @throws SlickException 
     */
    public Player(String... files) throws SlickException {
        //Initialiastion
        super(files);
        //Référencement
        PLAYERS.add(this);
        //Corps dynamique
        physics.define(BodyType.DYNAMIC);
    }
    
    public Player(Image... files) throws SlickException {
        //Initialiastion
        super(files);
        //Référencement
        PLAYERS.add(this);
        //Corps dynamique
        physics.define(BodyType.DYNAMIC);
    }
    
    
    @Override
    public void destroy() {
        PLAYERS.remove(this);
        super.destroy();
    }
    
    /**
     * Controller permanent
     * @param input 
     */
    public static void controller(Input input) {
        Player.keycheck(input);
    }
    
    /**
     * Controller de changement d'état
     * @param key
     * @param c
     * @param type 
     */
    public static void controller(int key, char c, boolean type) {
        if (type) { keydown(key, c); } else { keyup(key, c); }
    }
    
    /**
     * Vérification des touches déjà pressés
     * @param input 
     */
    public static void keycheck(Input input) {
        try { for (Player player : PLAYERS) {
            if (input.isKeyDown(Input.KEY_D)) { player.key_d(); }
            if (input.isKeyDown(Input.KEY_Q)) { player.key_q(); }
            if (!input.isKeyDown(Input.KEY_D) && !input.isKeyDown(Input.KEY_Q)) { player.no_key(); }
        } } catch (Exception ignore) {}
    }
    
    /**
     * Keyup
     * @param key
     * @param c 
     */
    public static void keyup(int key, char c) {}
    
    /**
     * Keydown
     * @param key
     * @param c 
     */
    public static void keydown(int key, char c) {
        for (Player player : PLAYERS) {
            if (key == Input.KEY_SPACE) { player.key_space(); }
        }
    }
    
    /**
     *  Aucune touche 
     */
    public abstract void no_key();
    
    /**
     * Callback à la touche Q
     */
    public abstract void key_q();
    
    /**
     * Callback à la touche D
     */
    public abstract void key_d();
    
    /**
     * Callback à la touche Espace
     */
    public abstract void key_space();    
}
