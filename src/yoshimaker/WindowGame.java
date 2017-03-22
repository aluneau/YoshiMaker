package yoshimaker;

import org.newdawn.slick.*;

public class WindowGame extends BasicGame {
    private GameContainer container;
    private TitleScreen view;
    
    public WindowGame() {
        super("Lesson 1 :: WindowGame");
    }

    @Override
    public void init(GameContainer container) throws SlickException {
        this.container = container;
        view = new TitleScreen();
        view.init();
    }

    @Override
    public void render(GameContainer container, Graphics g) throws SlickException {
        view.render();
    }

    @Override
    public void update(GameContainer container, int delta) throws SlickException {
        view.update(delta);
    }

    @Override
    public void keyReleased(int key, char c) {
        if (Input.KEY_ESCAPE == key) {
            container.exit();
        }
    }
}
