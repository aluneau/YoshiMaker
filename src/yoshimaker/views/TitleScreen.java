/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package yoshimaker.views;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

/**
 *
 * @author punpun
 */
public class TitleScreen extends View {
    private Image logo, background;
    private Image[] clouds;
    private double[] clouds_x, clouds_y;

    public TitleScreen() {
        clouds = new Image[5];
        clouds_x = new double[5];
        clouds_y = new double[5];
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
    }
    
    @Override
    public void render(GameContainer container, Graphics g) {
        background.draw(0, 00, 1.1f);
        for (int i = 0; i < clouds.length; i++) {
            clouds[i].draw((int)clouds_x[i], (int)clouds_y[i], 1.4f);
        }
        
        logo.draw(30, 50, 0.35f);
    }
    
    @Override
    public void update(GameContainer container, int delta) {
        for (int i = 0; i < clouds.length; i++) {
            clouds_x[i] += delta*0.05;
            if (clouds_x[i] > 850) { clouds_x[i] = -150; }
        }
    }
}
