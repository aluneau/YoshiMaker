package yoshimaker.global.items;

import org.newdawn.slick.Animation;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;
import yoshimaker.global.Entity;
import yoshimaker.global.characters.players.Player;

public class Door extends Item {
    protected static SpriteSheet DOOR;
    protected Player creator;
    
    static {
        //Initalisation
        try {
            DOOR = new SpriteSheet("./assets/images/test/doorB.png", 78,156,1);
        } catch (Exception ignore) { }
    }
    
    protected static int 
        WIDTH = 64, 
        HEIGHT = 100;
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
    public Door(int x, int y) throws SlickException {
        //Initialisation
        super(DOOR.getSprite(TILE_X, TILE_Y));
        //Coordonnées
        this.animation = new Animation(DOOR,100);
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
        
    }
    
    @Override
    public void destroy() {
        this.die();
    }
    
    public void die() {
        this.creator = null;
        super.destroy();
    }
}
