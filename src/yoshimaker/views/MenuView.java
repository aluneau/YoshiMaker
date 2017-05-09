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
import yoshimaker.title.Button;

/**
 *
 * @author punpun
 */
public class MenuView extends View {

    private Image logo, background;
    private Image[] clouds;
    private double[] clouds_x, clouds_y;
    private Button[] buttons;
    
    public MenuView() {
        clouds = new Image[5];
        clouds_x = new double[5];
        clouds_y = new double[5];
        buttons = new Button[3];
        clouds_y[0] = 10; clouds_x[0] = 10;
        clouds_y[1] = 75; clouds_x[1] = 300;
        clouds_y[2] = 50; clouds_x[2] = 600;
        clouds_y[3] = 100; clouds_x[3] = 750;
        clouds_y[4] = 85; clouds_x[4] = 450;
    }
    
    @Override
    public void init(GameContainer container) throws SlickException {
        background = new Image("./resources/background_yoshi.png");
        logo = new Image("./resources/logo_yoshi.png");
        
        clouds[0] = new Image("./resources/cloud_yoshi.png");
        clouds[1] = new Image("./resources/cloud_yoshi.png");
        clouds[2] = new Image("./resources/cloud_yoshi.png");
        clouds[3] = new Image("./resources/cloud_yoshi.png");
        clouds[4] = new Image("./resources/cloud_yoshi.png");
        
        buttons[0] = new Button("./resources/cloud_yoshi.png");
        buttons[0].setX(50).setY(0).setWidth(20).setHeight(20);

    }

    @Override
    public void render(GameContainer container, Graphics g) {
        background.draw(0, 0, container.getWidth()/500f);
        
        for (int i = 0; i < clouds.length; i++) {
            clouds[i].draw((int)clouds_x[i], (int)clouds_y[i], 1.4f);
        }
        
        logo.draw(container.getWidth()/2-275, 50, 0.35f);
        for (int i = 0; i < buttons.length; i++) {
            if (buttons[i] == null) { continue ; }
            buttons[i].getSprite().draw(50, i*100);
        }
    }

    @Override
    public void update(GameContainer container, int delta) {
        for (int i = 0; i < clouds.length; i++) {
            clouds_x[i] += delta*0.05;
            if (clouds_x[i] > container.getWidth()+150) { clouds_x[i] = -150; }
        }
    }

    @Override
    public void controller(Input input) {
        input.addMouseListener(buttons[0]);
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void controller(int key, char c, boolean type) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
