package yoshimaker.global;

import org.newdawn.slick.Animation;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

/**
 * Class entity.
 * Contain X, Y and an image
 * Created by adrien on 22/03/2017.
 */
public class Entity {
    private int x;
    private int y;
    private Animation sprite;

    public Entity(int x, int y, String... files) throws SlickException {
        Image[] images = new Image[files.length];
        this.x = x;
        this.y = y;
        for(int i = 0; i < files.length; i++){
            images[i] = new Image(files[i]);
        }
        sprite = new Animation(images, 1, true);
    }
    public Entity(Animation sprite, int x, int y){
        this.sprite = sprite;
        this.x = x;
        this.y = y;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public Animation getSprite() {
        return sprite;
    }

    public void setSprite(Animation sprite) {
        this.sprite = sprite;
    }

    public void draw(){
        sprite.draw(x, y);
    }
}
