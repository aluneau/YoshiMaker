package yoshimaker;

import org.jbox2d.dynamics.BodyType;
import yoshimaker.views.TitleScreen;
import org.newdawn.slick.*;
import yoshimaker.physics.Physics;

public class WindowGame extends BasicGame {
    private GameContainer container;
    private TitleScreen view;
    private Physics test, test2;
    private int test3 = 0;
    public Input input;
    public WindowGame() {
        super("Yoshi Game");
       
    }

    @Override
    public void init(GameContainer container) throws SlickException {

        this.container = container;


        view = new TitleScreen();
        view.init(container);

        input = container.getInput();
    }

    @Override
    public void render(GameContainer container, Graphics g) throws SlickException {
        view.render(container, g);
    }

    @Override
    public void update(GameContainer container, int delta) throws SlickException {
        view.controller(input) ;
        view.update(container, delta);
        Physics.update();
    }

    @Override
    public void keyPressed(int key, char c) {
        view.controller(key, c, true);
    }

    @Override
    public void keyReleased(int key, char c) {
        view.controller(key, c, false) ;
        if (Input.KEY_ESCAPE == key) {
            container.exit();
        }
    }
}
