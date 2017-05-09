/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package yoshimaker.views;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import yoshimaker.WindowGame;
import yoshimaker.title.Button;

/**
 *
 * @author punpun
 */
public class LevelView extends View {

    private Button[] buttons;
    
    @Override
    public void init(GameContainer container) throws SlickException {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        buttons = new Button[12];
        for (int i = 0; i < 12; i++) {
            buttons[i] = new Button(100+i, "./assets/"+(i+1)+".png", "./assets/"+(i+1)+"R.png");
        }
        WindowGame.getInstance().getCamera().focus(null);
    }

    @Override
    public void render(GameContainer container, Graphics g) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        for (int i = 0; i < 12; i++) {
            int x = (i - ((int) i/4));
            buttons[i].setX(container.getWidth()/2 + ((x < 2) ? -75*(2-x) : 75*(x-2))).setY(300 + 100*((int) i/4));
        }
    }

    @Override
    public void update(GameContainer container, int delta) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void controller(Input input) {
        input.removeAllMouseListeners();
        for (int i = 0; i < 12; i++) {
            input.addMouseListener(buttons[i]);
        }
        
//throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void controller(int key, char c, boolean type) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
