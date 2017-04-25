package yoshimaker;

import org.jbox2d.dynamics.BodyType;
import yoshimaker.maker.view.MakerView;
import yoshimaker.views.TitleScreen;
import org.newdawn.slick.*;
import yoshimaker.physics.Physics;
import yoshimaker.physics.PhysicsThread;
import yoshimaker.views.View;

public class WindowGame extends BasicGame {
    private GameContainer container;
    private View view;

    private Thread physicThread;

    private int test3 = 0;
    public Input input;
    public WindowGame() {
        super("Yoshi Game");
       
    }

    @Override
    public void init(GameContainer container) throws SlickException {

        this.container = container;


        view = new MakerView();
        view.init(container);

        input = container.getInput();

        //physicThread = new Thread(new PhysicsThread());
        //physicThread.start();

    }

    @Override
    public void render(GameContainer container, Graphics g) throws SlickException {
        view.render(container, g);
    }

    @Override
    public void update(GameContainer container, int delta) throws SlickException {
        view.controller(input) ;
        view.update(container, delta);
        //PhysicsThread.FPS = container.getFPS();
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
