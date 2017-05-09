package yoshimaker.global.items;

import org.newdawn.slick.Animation;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;

/**
 * Created by adrien on 09/05/2017.
 */
public class Star extends Item {

    protected static SpriteSheet STAR;

    static {
        //Initalisation
        try {
            STAR = new SpriteSheet("./assets/images/items/star.png", 64, 46, 0);
        } catch (Exception ignore) { }
    }

    protected static int
            WIDTH = 64,
            HEIGHT = 46;
    protected static float
            DENSITY = 3f,
            FRICTION = 1f,
            RESTITUTION = 0f;
    protected static int
            TILE_X = 0,
            TILE_Y = 0;
    private Animation inactive, active;

    public Star(int x, int y) throws SlickException {
        //Initialisation
        super(STAR.getSprite(TILE_X, TILE_Y));
        //Coordonnées
//        this.inactive = new Animation(STAR, 0, 0, 0, 0, true, 1, true);
//        this.active = new Animation(SHELL, 1, 0, 2, 0, true, 1, true);
//        this.sprite = this.inactive;
//        this.sprite.start();
        setX(x).setY(y).setWidth(WIDTH).setHeight(HEIGHT);
        //Défintion de la physique
        physics
                .at(x, y)
                .hitbox(width/2, height/2)
                .fixtures(DENSITY, FRICTION, RESTITUTION)
                .data(this)
                .create();
    }

    @Override
    public String toString() {
        return "star";
    }
}
