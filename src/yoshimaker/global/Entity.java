package yoshimaker.global;

import org.newdawn.slick.Animation;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import yoshimaker.physics.Physics;

/**
 * Class entity.
 * Contain X, Y and an image
 * Created by adrien on 22/03/2017.
 */
public class Entity {
    private int x;
    private int y;
    private int width;
    private int height;
    public Physics physics;

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

    public Entity setY(int y) {
        this.y = y;
        return this;
    }

    public int getX() {
        return x;
    }

    public Entity setX(int x) {
        this.x = x;
        return this;
    }

    public Animation getSprite() {
        return sprite;
    }

    public Entity setSprite(Animation sprite) {
        this.sprite = sprite;
        return this;
    }

    public Entity update(){
        try{
            System.out.println("Update physics");
        }catch(NullPointerException ignore){
            System.err.println("No physics avaiable for entity: " + this.toString());
        }
        return this;
    }

    public void draw(){
        try{
            x = (int) physics.x();
            y = (int) physics.y();
        }catch(NullPointerException ignore) {
        }
        if (width == 0 || height == 0) {
            sprite.draw(x, y);
        } else {
            sprite.draw(x, y, width, height);
        }
    }

    public int getWidth() {
        return width;
    }

    public Entity setWidth(int width) {
        this.width = width;
        return this;
    }

    public Entity setHeight(int height) {
        this.height = height;
        return this;
    }
}
