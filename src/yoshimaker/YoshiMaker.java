/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package yoshimaker;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.SlickException;

/**
 *
 * @author adrien
 */



public class YoshiMaker {

    /**
     * @param args the command line arguments
     */
    public static void main (String[] args) throws SlickException {
        new AppGameContainer(new WindowGame(), 640, 480, false).start();
        //Map map = new Map(10,15);
        //map.createMap();
        //map.printToDelete();
    }

}
