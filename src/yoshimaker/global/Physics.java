/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package yoshimaker.global;

import java.awt.Rectangle;
import org.jbox2d.collision.shapes.PolygonShape;
import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.Body;
import org.jbox2d.dynamics.BodyDef;
import org.jbox2d.dynamics.BodyType;
import org.jbox2d.dynamics.FixtureDef;
import org.jbox2d.dynamics.World;

/**
 *
 * @author punpun
 */
public class Physics {
    private double weight;
    private World world ;
    private Body body ;
    
    public Physics() {
        Vec2 gravity = new Vec2(0.0f, -10.0f);
        world = new World(gravity, false);
        
        //Sol
        BodyDef groundBodyDef = new BodyDef();
        //Positionnement
        groundBodyDef.position.set(0.0f, -10.0f);
        //Création du corps du sol
        Body groundBody = world.createBody(groundBodyDef);
        //Polygone 
        PolygonShape groundBox = new PolygonShape();
        groundBox.setAsBox(50.0f, 10.0f);
        groundBody.createFixture(groundBox, 0.0f);
        
        
        //Test bloc
        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyType.DYNAMIC ;
        bodyDef.position.set(0.0f, 4.0f);
        body = world.createBody(bodyDef);
        
        
        PolygonShape dynamicBox = new PolygonShape();
        dynamicBox.setAsBox(1.0f, 1.0f);
        
        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = dynamicBox;
        fixtureDef.density = 1.0f;
        fixtureDef.friction = 0.3f;
        
        body.createFixture(fixtureDef);
        
        
    }
    
    public void update() {
        float timeStep = 1.0f / 60.0f;
        int velocityIterations = 6;
        int positionIterations = 2;
        
        for (int i = 0; i < 60; ++i)
        {
         world.step(timeStep, velocityIterations, positionIterations);
         Vec2 position = body.getPosition();
         float angle = body.getAngle();
         System.out.printf("%4.2f %4.2f %4.2f\n", position.x, position.y, angle);
        }
    }
    
    public void setHitbox(Rectangle r) {
        r.setRect(0, 0, 10, weight);
    }
    
 
}
