package yoshimaker.maker.view;

import org.newdawn.slick.*;
import yoshimaker.global.Entity;
import yoshimaker.global.cases.Type;
import yoshimaker.global.characters.players.Player;
import yoshimaker.global.characters.players.Yoshi;
import yoshimaker.map.Map;
import yoshimaker.physics.Physics;
import yoshimaker.views.View;

/**
 * Created by adrien on 12/04/2017.
 */
public class MakerView extends View {
    private Map map;
    private Image background;
    private Yoshi yoshi;
    Physics test;
    @Override
    public void init(GameContainer container) throws SlickException {
        Physics.world(0, 100f);

        map = new Map(20, 11);
        background = new Image("./resources/background_yoshi.png");
        map.createMap();
        map.setCase(2, 8, Type.ICE);
        map.setCase(3, 8, Type.ICE);
        map.setCase(11, 8, Type.SPRING);
        map.setCase(8, 4, Type.BRICK);
        map.setCase(8, 5, Type.BRICK);
        map.setCase(8, 6, Type.BRICK);
        map.setCase(8, 7, Type.BRICK);
        map.setCase(10, 8, Type.EMPTY);
        map.setCase(9, 8, Type.EMPTY);
        map.move(2, 3);
        yoshi = new Yoshi(64, 4*64);

    }

    @Override
    public void render(GameContainer container, Graphics g) {
        background.draw(0, 00, 1.1f);
        Entity.drawAll(container, g);

    }

    @Override
    public void update(GameContainer container, int delta) {
        Entity.updateAll();
        Physics.update();
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
