/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package yoshimaker.views;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import yoshimaker.WindowGame;
import yoshimaker.global.characters.players.Player;
import yoshimaker.title.Button;
import yoshimaker.views.camera.Camera;

/**
 *
 * @author punpun
 */
public class WinView extends View {
    private Image image;
    private Button button;
    public int a_death;
    
    @Override
    public void init(GameContainer container) throws SlickException {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        image = new Image("./assets/yoshis.jpg");
        button = new Button(0, "./assets/playButton.png", "./assets/playButtonR.png");
        WindowGame.getInstance().getCamera().focus(null);
    }

    @Override
    public void render(GameContainer container, Graphics g) {
        image.draw(container.getWidth()/2-150, 50, 1f);
        button.setX(container.getWidth()/2).setY(350);
        button.draw(container, g);
        
        g.drawString("Il reste : "  + Integer.toString(a_death), Camera.xTop+50.0f, Camera.yTop+50.0f);
    }

    @Override
    public void update(GameContainer container, int delta) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void controller(Input input) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        input.removeAllMouseListeners();
        input.addMouseListener(button);
    }

    @Override
    public void controller(int key, char c, boolean type) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
