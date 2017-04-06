/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package yoshimaker.map;

import com.sun.xml.internal.ws.developer.Serialization;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.annotation.Annotation;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.jbox2d.dynamics.BodyType;
import org.newdawn.slick.*;
import yoshimaker.physics.Physics;

/**
 *
 * @author gaetane
 */
public class Case implements Serialization{

    private int x, y;
    private Block block;
    private Physics physics;
    private Image test;
    private static SpriteSheet spritesheet;

    public Case(int x, int y) {
        this.x = x;
        this.y = y;
        block = Block.NOTHING;
        physics = new Physics();

        try {
            spritesheet = new SpriteSheet("./assets/tileset1.png", 64, 64, 1);
        } catch (SlickException e) {
            e.printStackTrace();
        }

    }

    public Case(int x, int y, Block block) {
        this(x, y);
        this.block = block;

        if (block == Block.BRICK) {

            test = spritesheet.getSprite(5, 0);
        }
        physics.define(BodyType.STATIC).at(x * 64, y * 64).hitbox(64, 64).fixtures(1f, 1f, 0f).create();

    }

    public Block getBlock() {
        return block;
    }

    public void setBlock(Block block) {
        this.block = block;
    }

    public void draw(GameContainer container, Graphics g) {
        try {
            g.drawImage(test, physics.x(), physics.y());
        } catch (NullPointerException ignore) {
        }
    }

    public void readObject(ObjectInputStream ois) throws IOException, ClassNotFoundException {
        // Lecture en esperant que c'est le bon ordre 
        this.x = ois.readInt();
        this.y = ois.readInt();
        this.block = (Block) ois.readObject();
    }

    public void writeObject(ObjectOutputStream oos) throws IOException {
        oos.writeInt(x);
        oos.writeInt(y);
        oos.writeObject(block);
    }

    // Abstract to Sérialization
    @Override
    public String encoding() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Class<? extends Annotation> annotationType() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
