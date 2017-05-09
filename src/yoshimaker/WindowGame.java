package yoshimaker;

import org.jbox2d.dynamics.BodyType;
import yoshimaker.maker.view.MakerView;
import yoshimaker.views.TitleScreen;
import org.newdawn.slick.*;
import yoshimaker.maker.view.Button;
import yoshimaker.physics.Physics;
import yoshimaker.physics.PhysicsThread;
import yoshimaker.views.GameViewDeluxe;
import yoshimaker.views.View;
import yoshimaker.views.camera.Camera;

public class WindowGame extends BasicGame {
    private GameContainer container;
    private View view;
    private Camera camera;
    private Thread physicThread;

    static WindowGame instance;

    private int test3 = 0;
    public Input input;
    public WindowGame() {
        super("Yoshi Game");
        instance = this;
       
    }

    static public WindowGame getInstance() { return instance; }

    @Override
    public void init(GameContainer container) throws SlickException {
        this.container = container;
        container.setTargetFrameRate(60);
        input = container.getInput();
        camera = new Camera(container);
        Button.LISTENER = input ;   


        //view = new TitleScreen();
        //view = new GameViewDeluxe();
        view = new TitleScreen();
       // view = new GameViewDeluxe();
        //view = new TitleScreen();
        view.init(container);
        
        


        //physicThread = new Thread(new PhysicsThread());
        //physicThread.start();

    }

    public Camera getCamera() { return camera ; }

    @Override
    public void render(GameContainer container, Graphics g) throws SlickException {
        camera.render(container, g);
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
