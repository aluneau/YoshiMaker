package yoshimaker;

import yoshimaker.views.TitleScreen;
import org.newdawn.slick.*;
import yoshimaker.global.Physics;

public class WindowGame extends BasicGame {
    private GameContainer container;
    private TitleScreen view;
    private Physics physics;
    
    public WindowGame() {
        super("Yoshi Game");
       
    }

    @Override
    public void init(GameContainer container) throws SlickException {
        this.container = container;
        view = new TitleScreen();
        view.init(container);
        
        physics = new Physics();
    }

    @Override
    public void render(GameContainer container, Graphics g) throws SlickException {
        view.render(container, g);
    }

    @Override
    public void update(GameContainer container, int delta) throws SlickException {
        view.update(container, delta);
        physics.update();
    }

    @Override
    public void keyReleased(int key, char c) {
        if (Input.KEY_ESCAPE == key) {
            container.exit();
        }
    }
}
