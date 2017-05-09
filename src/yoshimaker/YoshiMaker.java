/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package yoshimaker;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import yoshimaker.map.Map;
import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.SlickException;
import yoshimaker.global.cases.Type;
import yoshimaker.physics.Physics;

/**
 *
 * @author adrien
 */



public class YoshiMaker {

    /**
     * @param args the command line arguments
     */

    public static void main (String[] args) throws SlickException, IOException {
        //new AppGameContainer(new WindowGame(), 1280, 720, false).start();
        //new AppGameContainer(new WindowGame(), 1280, 720, false).start();
        new AppGameContainer(new WindowGame(), 800, 600, false).start();
    }

}
