/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package map;

import java.util.logging.Level;
import java.util.logging.Logger;
import org.jbox2d.dynamics.BodyType;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import yoshimaker.physics.Physics;

/**
 *
 * @author gaetane
 */
public class Case {
    private final int x,y;
    private Block block;
    private Physics physics;
    private Image test;
    
    public Case(int x, int y){
        this.x = x;
        this.y = y;
        block = Block.NOTHING;
        physics = new Physics();
        
        
    }
    
    public Case(int x, int y, Block block){
        this(x, y);
        this.block = block; 
        
        try {
            if (block == Block.BRICK) { test = new Image("./resources/cloud_yoshi.png"); }
            physics.define(BodyType.STATIC).at(x*64, y*64).hitbox(32, 32).fixtures(1f, 1f, 0f).create();
        
        } catch (SlickException ex) {
            Logger.getLogger(Case.class.getName()).log(Level.SEVERE, null, ex);
        }
    } 

    public Block getBlock() {
        return block;
    }

    public void setBlock(Block block) {
        this.block = block;
    }
    
    
     public void draw(GameContainer container, Graphics g){
        try{
            g.drawImage(test, physics.x(), physics.y());
        }catch(NullPointerException ignore) {}
    }
    
}
