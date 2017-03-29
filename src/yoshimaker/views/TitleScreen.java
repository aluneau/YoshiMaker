/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package yoshimaker.views;

import org.jbox2d.dynamics.BodyType;
import org.newdawn.slick.*;
import yoshimaker.global.Entity;
import yoshimaker.physics.Physics;

/**
 *
 * @author punpun
 */
public class TitleScreen extends View {
    private Image logo, background;
    private Image[] clouds;
    private double[] clouds_x, clouds_y;
    private Entity testEntity;
    private int test = 0;
    private float test2 = 0, test3 = 0, test4 = 0;

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

        testEntity = new Entity(50, 70, "./assets/image1.png");
        testEntity.setWidth(70);
        testEntity.setHeight(70);
        Physics.world(0, 9.8f);


        Physics test2;
        test2 = new Physics();
        test2.define(BodyType.STATIC).at(0f, 300f).hitbox(500f, 10f).fixtures(0.5f, 0.9f, 0f).create();

        testEntity.physics = new Physics();
        testEntity.physics.define(BodyType.DYNAMIC).at(0f, 300f).hitbox(0.25f, 0.85f).fixtures(0.5f, 1f, 0f).create();
    }
    
    @Override
    public void render(GameContainer container, Graphics g) {
        background.draw(0, 00, 1.1f);
        for (int i = 0; i < clouds.length; i++) {
            clouds[i].draw((int)clouds_x[i], (int)clouds_y[i], 1.4f);
        }
        
        logo.draw(30, 50, 0.35f);
        testEntity.draw();
    }
    
    @Override
    public void update(GameContainer container, int delta) {

        for (int i = 0; i < clouds.length; i++) {
            clouds_x[i] += delta*0.05;
            if (clouds_x[i] > 850) { clouds_x[i] = -150; }
        }
    }

    public void controller(Input input) {
        try {
            if (input.isKeyDown(Input.KEY_D)) { controller(Input.KEY_D, 'd'); }
            if (input.isKeyDown(Input.KEY_A)) { controller(Input.KEY_A, 'a'); }
            if (input.isKeyDown(Input.KEY_SPACE)) { controller(Input.KEY_SPACE, ' '); }
        } catch (Exception ignore) {}
    }

    public void controller(int key, char c) {
        switch (key) {
            case Input.KEY_D:
                testEntity.physics.translate(+25f, 0);
                break;
            case Input.KEY_A:
                testEntity.physics.translate(-25f, 0);
                break;
            case Input.KEY_SPACE:
                if (test < 10) {
                    testEntity.physics.impulse(0f, -2f);
                    System.out.print(test);
                    test++;
                }
                if (Float.compare(test2, testEntity.physics.y()) == 0) { test = 0; }
                test2 = testEntity.physics.y();


                break;
        }
    }
}

