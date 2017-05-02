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
import yoshimaker.views.camera.Camera;

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
     
    protected static Camera CAMERA;
    private static int displayed;
    
    protected int 
        x = 0, 
        y = 0, 
        width = 0, half_width = 0,
        height = 0, half_height = 0;
    
    protected Physics physics;
    protected Animation sprite;
    
    protected boolean destroyed = false;
    
    /**
     * Entité
     * @param files
     * @throws SlickException 
     */
    public Entity(String... files) throws SlickException {
        //Référencement
        ENTITIES.add(this);
        //Sprites
        Image[] images = new Image[files.length];
        for(int i = 0; i < files.length; i++){ images[i] = new Image(files[i]); }
        this.sprite = new Animation(images, 1, true);
        //Physique
        physics = new Physics();
    }
    
    public Entity(Image... images) throws SlickException {
        //Référencement
        ENTITIES.add(this);
        //Sprites
        this.sprite = new Animation(images, 1, true);
        //Physique
        physics = new Physics();
    }
    
    public Entity(Animation sprite){
        //Référencement
        ENTITIES.add(this);
        //Sprite
        this.sprite = sprite;
        //Physique
        physics = new Physics();
    }
    
    public Entity() {
        
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
        if (destroyed) { return this ; }
        setX((int) physics.x()).setY((int) physics.y());
        return this;
    }

    public void draw(GameContainer container, Graphics g){
        if (destroyed) { return ; }
        sprite.draw(x-half_width, y-half_height, width, height);
        
        Vec2[] vertices = physics.hitbox().getVertices();
        
        int dx = (int)physics.x() -2, dy = (int)physics.y() -2;
        for (int i = 0; i < vertices.length; i++) { 
            g.fillOval(dx+Physics.toPixels(vertices[i].x), dy+Physics.toPixels(vertices[i].y), 4, 4);
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
    
    public static void drawCamera(GameContainer container, Graphics g) {
        //Dessine toutes les entités présentes dans la caméra
        if (CAMERA == null) { return ; }
        for (Entity entity : ENTITIES) { if (CAMERA.isVisible(entity)) { entity.draw(container, g); } }
        /*
            displayed = 0;
            for (Entity entity : ENTITIES) { if (CAMERA.isVisible(entity)) { entity.draw(container, g); displayed++;} }
            System.out.println("Displayed "+displayed);
        */
    }
    
    public void destroy() {
        physics.destroy();
        destroyed = true ;
        ENTITIES.removeIf(f -> { return f.destroyed ;});
    }
    
    public static void setCamera(Camera camera) {
        CAMERA = camera;
    }
}