package yoshimaker;

import org.jbox2d.dynamics.BodyType;
import yoshimaker.views.TitleScreen;
import org.newdawn.slick.*;
import yoshimaker.physics.Physics;

public class WindowGame extends BasicGame {
    private GameContainer container;
    private TitleScreen view;
    private Physics test, test2;
    
    public WindowGame() {
        super("Yoshi Game");
       
    }

    @Override
    public void init(GameContainer container) throws SlickException {
        this.container = container;
        view = new TitleScreen();
        view.init(container);
        
        
        Physics.world(0, -10);
        test = new Physics();
        test2 = new Physics();
        test2.define(BodyType.STATIC).at(0f, -10f).hitbox(500f, 10f).fixtures(0f, 0f, 0f).create();
        test.define(BodyType.DYNAMIC).at(0f, 4f).hitbox(1f, 1f).fixtures(10f, 0f, 0f).create();
    }

    @Override
    public void render(GameContainer container, Graphics g) throws SlickException {
        view.render(container, g);
    }

    @Override
    public void update(GameContainer container, int delta) throws SlickException {
        view.update(container, delta);
        
        test.impulse(+0.1f, 0);
        Physics.update();
        System.out.printf("{x:%4.2f, y:%4.2f, r:%4.2f}\n", test.x(), test.y(), test.angle());
    }

    @Override
    public void keyReleased(int key, char c) {
        if (Input.KEY_ESCAPE == key) {
            container.exit();
        }
    }
}
