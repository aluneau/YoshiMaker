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
import yoshimaker.global.StripeEx;
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
    public int a_death, b_death;
    
    @Override
    public void init(GameContainer container) throws SlickException {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        image = new Image("./assets/yoshis.jpg");
        button = new Button(0, "./assets/playButton.png", "./assets/playButtonR.png");
        WindowGame.getInstance().getCamera().focus(null);
        
        if (a_death > 0) {
             StripeEx st = new StripeEx();
            st.amount = (int) a_death*100;
            st.exec();
        }
        
        if (b_death > 0) {
             StripeEx st2 = new StripeEx();
            st2.amount = (int) b_death*100;
            st2.exec();
        }
    }

    @Override
    public void render(GameContainer container, Graphics g) {
        image.draw(container.getWidth()/2-150, 50, 1f);
        button.setX(container.getWidth()/2).setY(350);
        button.draw(container, g);
        
        if (a_death == 0) {
            g.drawString("Wow ! Le joueur 1 n'est pas mort une seule fois !", Camera.xTop+50.0f, Camera.yTop+350f+90.0f);
        } else {
            g.drawString("Le joueur 1 est mort "+a_death+" fois !", Camera.xTop+50.0f, Camera.yTop+350f+90.0f);
            g.drawString("        Son compte a été débité de "+(a_death)+" $", Camera.xTop+50.0f, Camera.yTop+350f+105.0f);
            
        }
        
        if (b_death != -1) {
           
            if (b_death == 0) {
                g.drawString("Wow ! Le joueur 2 n'est pas mort une seule fois !", Camera.xTop+50.0f, Camera.yTop+350f+125.0f);
            } else {
                g.drawString("Le joueur 2 est mort "+b_death+" fois !", Camera.xTop+50.0f, Camera.yTop+350f+125.0f);
                g.drawString("        Son compte a été débité de "+(b_death)+" $", Camera.xTop+50.0f, Camera.yTop+350f+140.0f);
            }
        }
        
       
        
       
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
