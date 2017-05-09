/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package yoshimaker.global.cases;

import org.newdawn.slick.Animation;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import yoshimaker.global.Entity;
import static yoshimaker.global.cases.Brick.TILE_X;
import static yoshimaker.global.cases.Case.SPRITESHEET;

/**
 *
 * @author punpun
 */
public class DoorBrick extends Case {
     protected static float 
        DENSITY = 1f, 
        FRICTION = 0.7f, 
        RESTITUTION = 0f;
    protected static int
        TILE_X = 1,
        TILE_Y = 64;
    public boolean opened = true ;
    protected Animation open, closed;
    public DoorBrick(int x, int y) throws SlickException {
        //Initialisation
        super(SPRITESHEET.getSprite(TILE_X, TILE_Y));
        
        this.open = new Animation();
        this.open.addFrame(SPRITESHEET.getSprite(1, 64), 1000);
        this.closed = new Animation();
        this.closed.addFrame(SPRITESHEET.getSprite(1, 64), 1000);
        this.sprite = closed;
        
        //Coordonnées
        setX(x).setY(y).setWidth(WIDTH).setHeight(HEIGHT);
        
        //Définition de la physique
        physics
            .at(x*width, y*height)
            .hitbox(width/2, height/2)
            .fixtures(DENSITY, FRICTION, RESTITUTION).data(this)
            .create();
        update();
        type = Type.DOORBRICK;
    }
    
     @Override
    public Entity update(){
        if (destroyed) { return this ; }
        setX((int) physics.x()).setY((int) physics.y());
        if (physics.getBody() == null) { return this; }
        if (opened) {
            physics.getBody().getFixtureList().m_filter.categoryBits = 1;
            physics.getBody().getFixtureList().m_filter.maskBits = 2;
            this.sprite = open;
        } else {
        //Collisions activé
            physics.getBody().getFixtureList().m_filter.categoryBits = 1;
            physics.getBody().getFixtureList().m_filter.maskBits = 1;
            physics.move(0, 0); 
            this.sprite = closed;
        }
        sprite.start();
        sprite.setSpeed(0.01f);
        return this ;
    }
    
}
