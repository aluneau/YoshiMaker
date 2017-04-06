package yoshimaker.global;

import java.util.HashSet;
import org.jbox2d.common.Vec2;
import org.newdawn.slick.Animation;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;
import yoshimaker.physics.Physics;

/**
 * Class entity.
 * Contain X, Y and an image
 * Created by adrien on 22/03/2017.
 */
public abstract class Entity {
    /**
     * Liste des entités
     */
    protected final static HashSet<Entity> ENTITIES = new HashSet();
     
    protected int 
        x = 0, 
        y = 0, 
        width = 0, half_width = 0,
        height = 0, half_height = 0;
    
    protected Physics physics;
    protected Animation sprite;
    
    /**
     * Entité
     * @param files
     * @throws SlickException 
     */
    public Entity(String... files) throws SlickException {
        //Sprites
        Image[] images = new Image[files.length];
        for(int i = 0; i < files.length; i++){ images[i] = new Image(files[i]); }
        this.sprite = new Animation(images, 1, true);
        //Physique
        physics = new Physics();
    }
    
    public Entity(Image... images) throws SlickException {
        //Sprites
        this.sprite = new Animation(images, 1, true);
        //Physique
        physics = new Physics();
    }
    
    public Entity(Animation sprite){
        //Sprite
        this.sprite = sprite;
        //Physique
        physics = new Physics();
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

    public int getWidth() {
        return width;
    }

    public Entity setWidth(int width) {
        this.width = width;
        this.half_width = this.width/2;
        return this;
    }
    
    public int getHeight() {
        return height;
    }

    public Entity setHeight(int height) {
        this.height = height;
        this.half_height = this.height/2;
        return this;
    }
    
    public Entity update(){
        try{
            x = (int) physics.x();
            y = (int) physics.y();
        } catch(Exception ignore) {  }
        return this;
    }

    public void draw(GameContainer container, Graphics g){
        sprite.draw(x-half_width, y-half_height, width, height);
        
        Vec2[] vertices = physics.hitbox().getVertices();
        
        int dx = (int)physics.x(), dy = (int)physics.y();
        for (int i = 0; i < vertices.length; i++) { 
            g.drawOval(dx+vertices[i].x, dy+vertices[i].y, 5, 5);
            /*g.drawLine(
            physics.x() + vertices[i-1 < 0 ? vertices.length-1 : i-1].x, 
            physics.y() + vertices[i-1 < 0 ? vertices.length-1 : i-1].y, 
            physics.x() + vertices[i].x, 
            physics.y() + vertices[i].y
            */
        }
    }   
    
    /**
     * Met à jour toutes les entités crées
     */
    public static void updateAll() {
        //Met à jour toutes les entités crées
        for (Entity entity : ENTITIES) { entity.update(); }
    }
    
    /**
     * Dessine toutes les entités crées
     * @param container
     * @param g
     */
    public static void drawAll(GameContainer container, Graphics g) {
        //Dessine toutes les entités
        for (Entity entity : ENTITIES) { entity.draw(container, g); }
    }
}
