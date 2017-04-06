/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package yoshimaker.global.cases;

import java.util.logging.Level;
import java.util.logging.Logger;
import org.jbox2d.dynamics.BodyType;
import org.newdawn.slick.*;
import yoshimaker.global.Entity;
import yoshimaker.map.Block;
import yoshimaker.physics.Physics;

/**
 *
 * @author gaetane
 */
public abstract class Case extends Entity {
    public Block block;
    private Image test;
    protected static SpriteSheet SPRITESHEET;
    protected static int WIDTH = 64, HEIGHT = 64;
   
    public Case(Image... files) throws SlickException {
        //Initialiastion
        super(files);
        //Corps statique
        physics.define(BodyType.STATIC);
        //Type de block
        block = Block.NOTHING;
    }
    
    static {
        //Initalisation
        try {
            SPRITESHEET = new SpriteSheet("./assets/tileset1.png", 64, 64, 1);
        } catch (Exception ignore) { }
    }

/*
    
    
    public Case(int x, int y, Block block){
        this(x, y);
        this.block = block; 
        
            if (block == Block.BRICK) {

                test = ;
            }
            physics.define(BodyType.STATIC).create();
        
    }
*/
}
