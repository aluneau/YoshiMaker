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
import org.newdawn.slick.gui.TextField;
import yoshimaker.WindowGame;
import yoshimaker.global.Entity;
import yoshimaker.global.characters.players.Player;
import yoshimaker.global.characters.players.Yoshi;
import yoshimaker.global.characters.players.Yoshi2;
import yoshimaker.global.items.Box;
import yoshimaker.maker.view.Button;
import yoshimaker.maker.view.MenuButtonMaker;
import yoshimaker.map.Map;
import yoshimaker.physics.Physics;
import yoshimaker.physics.Timer;
import yoshimaker.views.camera.Camera;

/**
 *
 * @author punpun
 */
public class LevelLoadedView extends View {
    private Image logo, background;
    
    private Yoshi testEntity;
    private Yoshi2 testEntity2;
    private boolean test = false;
    private float test2 = 0, test3 = 0, test4 = 0;
    private Map map ;
    private boolean _init = false;

    private TextField counterText;
    public String level ;
    
    @Override
    public void init(GameContainer container) throws SlickException {
        background = new Image("./resources/background_yoshi.png");
        Physics.world(0, 100f);
        testEntity = new Yoshi(64, 9*64);
        Player.countStar = 3;

      
        map = new Map(level); 
        
       // MenuButtonMaker menuMaker = new MenuButtonMaker(container, container.getScreenWidth()-200);

        Entity.setCamera(camera().focus(testEntity).on(map));
        
        Thread tm = new Thread(new Timer());
        tm.start();
       _init = true ;
    }

    @Override
    public void render(GameContainer container, Graphics g) {
         background.draw(0, 00, 4f);
        Entity.drawCamera(container, g);
        Button.drawAll(container, g);

        g.drawString("Il reste : "  + Integer.toString(Player.countStar), Camera.xTop+50.0f, Camera.yTop+50.0f);

    
    }

    @Override
    public void update(GameContainer container, int delta) {
        Entity.updateAll();
        Physics.update();
        if ((_init)&&(Player.countStar <=0)){
            Player.countStar = 0;
            int a_death = Map.CURRENT.y1.death ;
            int b_death = Map.CURRENT.y2 == null ? -1 : Map.CURRENT.y2.death ;
            Entity.DESTROY();
            try {
                WinView v = new WinView();
                v.a_death = a_death;
                v.b_death = b_death;
                WindowGame.getInstance().view = v;
                WindowGame.getInstance().view.init(WindowGame.getInstance().container);
            } catch (SlickException ex) {  }
            
        }
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
