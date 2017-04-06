/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package yoshimaker.global.cases;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
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
    private boolean beCase;
   
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
    
    public void readObject(ObjectInputStream ois) throws IOException, ClassNotFoundException {
        // Lecture en esperant que c'est le bon ordre 
        this.x = ois.readInt();
        this.y = ois.readInt();
        this.block = (Block) ois.readObject();
        this.beCase = ois.readBoolean();
    }

    public void writeObject(ObjectOutputStream oos) throws IOException {
        oos.writeInt(x);
        oos.writeInt(y);
        oos.writeObject(block);
        oos.writeBoolean(beCase);
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
