package yoshimaker;

import org.newdawn.slick.*;

public class WindowGame extends BasicGame {
    private GameContainer container;

    public WindowGame() {
        super("Yoshi Game");
    }

    @Override
    public void init(GameContainer container) throws SlickException {
        this.container = container;
    }

    @Override
    public void render(GameContainer container, Graphics g) throws SlickException {
    }

    @Override
    public void update(GameContainer container, int delta) throws SlickException {
    }

    @Override
    public void keyReleased(int key, char c) {
        if (Input.KEY_ESCAPE == key) {
            container.exit();
        }
    }
}
