/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package yoshimaker;

import yoshimaker.map.Map;
import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.SlickException;
import yoshimaker.physics.Physics;

/**
 *
 * @author adrien
 */



public class YoshiMaker {

    /**
     * @param args the command line arguments
     */
    public static void main (String[] args) throws SlickException {
        //new AppGameContainer(new WindowGame(), 1280, 720, false).start();
        //new AppGameContainer(new WindowGame(), 1280, 720, false).start();
        Map m = new Map(10,10);
        m.saveText("test");
    }

}
