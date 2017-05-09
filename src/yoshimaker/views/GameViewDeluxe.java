/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package yoshimaker.views;

import java.util.logging.Level;
import java.util.logging.Logger;
import org.lwjgl.input.Mouse;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;
import yoshimaker.global.Entity;
import yoshimaker.global.cases.Type;
import yoshimaker.global.characters.players.Player;
import yoshimaker.global.characters.players.Yoshi;
import yoshimaker.global.items.Box;
import yoshimaker.global.items.Door;
import yoshimaker.maker.view.Button;
import yoshimaker.maker.view.MenuButtonMaker;

import yoshimaker.map.Map;
import yoshimaker.physics.Physics;

public final class GameViewDeluxe extends View {
    public Image logo, background, imageTest;
    private final Image[] clouds;
    private final double[] clouds_x;
    private final double[] clouds_y;
    private Yoshi testEntity;
    private final boolean test = false;
    private final float test2 = 0;
    private final float test3 = 0;
    private final float test4 = 0;
    private Map map ;
        private Button button,button1,button2;


    public GameViewDeluxe() throws SlickException {
   
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
        imageTest = new Image("./assets/images/test/arrow.jpg");
        Physics.world(0, 100f);
        testEntity = new Yoshi(64, 4*64);
        Box b = new Box(3*64, 4*64);
        map = new Map(25,13);
        map.setLevel1();
        Entity.setCamera(camera().focus(testEntity).on(map));

        System.out.println(container.getScreenWidth());
        MenuButtonMaker menuMaker = new MenuButtonMaker(container, container.getWidth()-100);
    }
    
    @Override
    public void render(GameContainer container, Graphics g) {
        if (background != null) { 
            background.draw(0, 00, 1.1f);  
        }
        try {
            Door d = new Door(5*64,6*64);
        } catch (SlickException ex) {
            Logger.getLogger(GameViewDeluxe.class.getName()).log(Level.SEVERE, null, ex);
        }

        //imageTest.draw(200, 00, 0.4f);        
        Entity.drawAll(container, g);
        Button.drawAll(container, g);
    }
    
    @Override
    public void update(GameContainer container, int delta) {
        int posX = Mouse.getX();
        int posY = Mouse.getY();
        Entity.updateAll();
        Physics.update();
        //if()
    }

    @Override
    public void controller(Input input) {
        Player.controller(input);
    }

    @Override
    public void controller(int key, char c, boolean type) {
        Player.controller(key, c, type);
    }
}

