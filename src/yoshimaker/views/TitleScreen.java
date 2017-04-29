/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package yoshimaker.views;

import yoshimaker.map.Map;
import org.jbox2d.dynamics.BodyType;
import org.newdawn.slick.*;
import yoshimaker.global.Entity;
import yoshimaker.global.cases.Brick;
import yoshimaker.global.cases.Type;
import yoshimaker.global.characters.ennemies.Goomba;
import yoshimaker.global.characters.players.Player;
import yoshimaker.global.characters.players.Yoshi;
import yoshimaker.global.items.Box;
import yoshimaker.physics.Physics;
import yoshimaker.global.items.Item;

/**
 *
 * @author punpun
 */
public class TitleScreen extends View {
    private Image logo, background;
    private Image[] clouds;
    private double[] clouds_x, clouds_y;
    private Yoshi testEntity;
    private boolean test = false;
    private float test2 = 0, test3 = 0, test4 = 0;
    private Map map ;

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
        /*logo = new Image("./resources/logo_yoshi.png");
        clouds[0] = new Image("./resources/cloud_yoshi.png");
        clouds[1] = new Image("./resources/cloud_yoshi.png");
        clouds[2] = new Image("./resources/cloud_yoshi.png");
        clouds[3] = new Image("./resources/cloud_yoshi.png");
        clouds[4] = new Image("./resources/cloud_yoshi.png");
        */
        Physics.world(0, 100f);
        testEntity = new Yoshi(64, 4*64);
        
        Box b = new Box(3*64, 4*64);
        Goomba g = new Goomba(5*64, 4*64);
 
        //Item testItem = new Item("./resources/cloud_yoshi.png");

        //Physics test2 = new Physics();
        //test2.define(BodyType.STATIC).at(0f, 300f).hitbox(500f, 10f).fixtures(0.5f, 0.9f, 0f).create();
        
        map = new Map(30,14);
        

        map.setCase(2, 8, Type.ICE);
        map.setCase(3, 8, Type.ICE);
        map.setCase(11, 8, Type.SPRING);
        map.setCase(8, 4, Type.BRICK);
        map.setCase(8, 5, Type.BRICK);
        map.setCase(8, 6, Type.BRICK);
        map.setCase(8, 7, Type.BRICK);
        map.setCase(10, 8, Type.EMPTY);
        map.setCase(9, 8, Type.EMPTY);
        //map.move(100, 100);

        Entity.setCamera(camera().focus(testEntity).on(map));

    }
    
    @Override
    public void render(GameContainer container, Graphics g) {
        /*
        for (int i = 0; i < clouds.length; i++) {
            clouds[i].draw((int)clouds_x[i], (int)clouds_y[i], 1.4f);
        }
        
        
        
        logo.draw(30, 50, 0.35f);
        //testEntity.draw();
        
        */
        //map.draw(container, g);
        background.draw(0, 00, 4f);
        Entity.drawCamera(container, g);
        
    }
    
    @Override
    public void update(GameContainer container, int delta) {
        Entity.updateAll();
        Physics.update();

        
        /*
        for (int i = 0; i < clouds.length; i++) {
            clouds_x[i] += delta*0.05;
            if (clouds_x[i] > 850) { clouds_x[i] = -150; }
        }*/
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

