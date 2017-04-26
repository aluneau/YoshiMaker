package yoshimaker.views.camera;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import yoshimaker.global.Entity;
import yoshimaker.global.cases.Case;
import yoshimaker.global.characters.players.Player;
import yoshimaker.map.Map;

/**
 * Created by adrien on 25/04/2017.
 */
public class Camera {
    public int x, y, ox, oy;
    private Entity follow;
    private Map map;

    public Camera() {
        x = 0; y = 0; ox = 0; oy = 0;
    }

    public void render(GameContainer container, Graphics g) {
        if ((follow != null)&&(map != null)) {
            int xs = (map.getX() * Case.WIDTH), ys = (map.getY() * Case.HEIGHT);

            y = -follow.getY() + ys + oy ;
            x = -follow.getX() + xs + ox ;
            g.translate(
                    x > container.getWidth() ? 0 : x > xs - container.getWidth() ? x - container.getWidth() : xs-2*container.getWidth() ,
                    y > container.getHeight() ? 0 : y > ys - container.getHeight() ? x - container.getHeight() : ys-2*container.getHeight()
            );
            //System.out.println(x+" "+(x - container.getWidth())+" "+xs+" "+container.getWidth()+" "+(xs - container.getWidth()));
        }
    }

    public Camera focus(Entity entity) {
        this.follow = entity ;
        ox = 0-entity.getX();
        oy = 0-entity.getY();
        return this;
    }

    public Camera on(Map map) {
        this.map = map;
        return this;
    }

}
