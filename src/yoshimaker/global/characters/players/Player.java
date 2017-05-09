/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package yoshimaker.global.characters.players;

import java.util.HashSet;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.jbox2d.dynamics.BodyType;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import yoshimaker.global.Entity;
import static yoshimaker.global.Entity.ENTITIES;
import yoshimaker.global.JSPolyfill;
import yoshimaker.global.items.FireBall;

/**
 *
 * @author punpun
 */
public abstract class Player extends yoshimaker.global.characters.Character {
    /**
     * Liste des joueurs
     */
    public final static HashSet<Player> PLAYERS = new HashSet();
       protected boolean jumped = false;
       public int fired = 0 ;
       protected String direction;

       public static int countStar = 3;
       public int death = 0;

       
    
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
        direction = "right";
    }
    
    public Player(Image... files) throws SlickException {
        //Initialiastion
        super(files);
        //Référencement
        PLAYERS.add(this);
        //Corps dynamique
        physics.define(BodyType.DYNAMIC);
        direction = "right";
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
            if(input.isKeyDown(Input.KEY_K)){ player.key_k();}
            if(input.isKeyDown(Input.KEY_J)){ player.key_j();}
            if(input.isKeyDown(Input.KEY_L)){ player.key_l();}
            if (input.isKeyDown(Input.KEY_D)) { player.key_d(); }
            if (input.isKeyDown(Input.KEY_Q)) { player.key_q(); }
            if (input.isKeyDown(Input.KEY_E)) { player.key_e(); }
            if(input.isKeyDown(Input.KEY_O))  {player.key_o(); }
            if(input.isKeyDown(Input.KEY_A))  {player.key_a(); }
            if(input.isKeyDown(Input.KEY_U))  {player.key_u(); }
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
            if(key==Input.KEY_I){ player.key_i();}
            if (key == Input.KEY_Z || key==Input.KEY_W) { player.key_space(); }
        }
    }
    
    /**
     *  Aucune touche 
     */
    public abstract void no_key();

    public void key_l(){direction = "right";}

    public void key_k(){}

    public void key_j(){direction = "left";}

    public void key_i(){}

    public void key_o(){}
    
    public void key_a(){
         Iterator<Player> it = PLAYERS.iterator();
        while (it.hasNext()) {
            Player p = it.next();
            if (p == null) { continue ; }
            if (p == this) { continue ; }
            System.out.println(Math.pow(getX() - p.getX(), 2) + Math.pow(getY() - p.getY(), 2));
            if (Math.pow(getX() - p.getX(), 2) + Math.pow(getY() - p.getY(), 2) < Math.pow(75, 2)) {
                System.out.println("ok");
                p.physics.impulse((getDirection() == "left" ? -1 : 1)*20f, -50f);
                physics.move(0, 0);
            }
        }
    }
    public void key_u(){
        this.key_a();
    }

    /**
     * Callback à la touche Q
     */
    public void key_q() { direction = "left" ;};
    
    /**
     * Callback à la touche D
     */
    public void key_d() { direction = "right"; };
    
    public void key_e() {}
    
    /**
     * Callback à la touche Espace
     */
    public abstract void key_space();    
    
    @Override
    public String toString() { return "player" ; }
    
    @Override
    public void die() {
        physics.forcePosition();
        this.death++;
        update();
    }
    
    public void jump(boolean forced) {
        if ((!jumped)||(forced)) {
            jumped = true ;  
            physics.moveY(-30);
            if (forced) { physics.moveY(-20); }
        }
    }
    
    public String getDirection() {
        return direction ;
    }
    
    private float sx, sy;
    public Player setSpawn(float x, float y) {
        sx = x; sy = y;
        return this;
    }
    
    public float getSpawnX() { return sx ; }
    public float getSpawnY() { return sy ; }
}
