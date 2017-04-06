/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package yoshimaker.views;

import java.util.logging.Level;
import java.util.logging.Logger;
import yoshimaker.map.Block;
import yoshimaker.map.Map;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

/**
 *
 * @author punpun et ba bravo Simon :o je suis outrée !! :o 
 
public class GameViewDeluxe extends View {
    //Map map = new Map(10,15);
    Image theme_ground;

    public GameViewDeluxe() throws SlickException {
    //    map.createMap();
        this.init();
    }
    
    public void init() throws SlickException {
    //    map.getCase(1, 1).setBlock(Block.LAVA);
        theme_ground = new Image("assets/images/maps/Ground.png");
    }
    
    @Override
    public void render(GameContainer container, Graphics g) {
        try {
            Ressources test = new Ressources(theme_ground,1,0);
            //Image sprite = theme.getSubImage(15, 15);

            //test.sprite.drawEmbedded(0, 0, 32, 32);
            test.sprite.drawCentered(60, 60);
        } catch (SlickException ex) {
            Logger.getLogger(GameViewDeluxe.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Override
    public void update(GameContainer container, int delta) {

    }

    @Override
    public void init(GameContainer container) throws SlickException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void controller(int key, char c) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
*/