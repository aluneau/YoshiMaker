/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package yoshimaker.global.items;

import org.newdawn.slick.Animation;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;
import yoshimaker.global.characters.players.Player;

/**
 *
 * @author punpun
 */
public class FireBall extends Item {
    protected static SpriteSheet FIREBALL;
    protected Player creator;
    
    static {
        //Initalisation
        try {
            FIREBALL = new SpriteSheet("./assets/feu.png", 64, 64, 0);
        } catch (Exception ignore) { }
    }
    
    protected static int 
        WIDTH = 32, 
        HEIGHT = 32;
    protected static float 
        DENSITY = 3f, 
        FRICTION = 1f, 
        RESTITUTION = 1f;
    protected static int
        TILE_X = 0,
        TILE_Y = 0;
    private Animation animation;
    /**
     * Boite
     * @param x
     * @param y
     * @throws SlickException 
     */
    public FireBall(int x, int y) throws SlickException {
        //Initialisation
        super(FIREBALL.getSprite(TILE_X, TILE_Y));
        //Coordonnées
        this.animation = new Animation(FIREBALL, 0, 0, 0, 0, true, 1, true);
        this.sprite = this.animation;
        this.sprite.start();
        setX(x).setY(y).setWidth(WIDTH).setHeight(HEIGHT);
        //Défintion de la physique
        physics
            .at(x, y)
            .hitbox(width/2, height/2)
            .fixtures(DENSITY, FRICTION, RESTITUTION)
            .data(this)
            .create();
    }
    
    public void setCreator(Player p) {
        creator = p;
    }
    
    public Player getCreator() {
        return creator;
    }
    
    @Override
    public void onCreate() {
        if (creator != null) {
            physics.impulse(((creator.getDirection() == "left") ? -1f : 1f)*25f, 10f);
        }
        
    }
    
    @Override
    public void destroy() {
        this.die();
    }
    
    public void die() {
        if (this.creator instanceof Player) { this.creator.fired-- ; }
        this.creator = null;
        super.destroy();
    }
}
