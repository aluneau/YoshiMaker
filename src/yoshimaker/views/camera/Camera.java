package yoshimaker.views.camera;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import yoshimaker.global.Entity;
import yoshimaker.global.cases.Case;
import yoshimaker.global.characters.players.Player;
import yoshimaker.global.items.Box;
import yoshimaker.map.Map;

/**
 * Created by adrien on 25/04/2017.
 */
public class Camera {
    public int x, y, w, h, ox, oy, xs, ys, hw, hh;
    private Entity follow;
    private Map map;

    public Camera(GameContainer container) {
        x = 0; y = 0; ox = 0; oy = 0; ys = 0; xs = 0; ys = 0;
        w = container.getWidth(); hw = w/2;
        h = container.getHeight(); hh = h/2;
        
    }

    public void render(GameContainer container, Graphics g) {
        if ((follow != null)&&(map != null)) {
            
            y = -follow.getY() + ys + oy;
            x = -follow.getX() + xs + ox;
            g.translate(
                x > container.getWidth() ? 0 : x > xs - container.getWidth() ? x - container.getWidth() : xs-2*container.getWidth() ,
                y > container.getHeight() ? 0 : y > ys - container.getHeight() ? y - container.getHeight() : ys-2*container.getHeight()
            );
        }
    }
    
    public Camera focus(Entity entity) {
        this.follow = entity ;
        ox = 0-entity.getX();
        oy = entity.getY();
        return this;
    }

    public Camera on(Map map) {
        this.map = map;
        xs = (map.getX() * Case.WIDTH);
        ys = (map.getY() * Case.HEIGHT);        
        return this;
    }
    
    public boolean isVisible(Entity entity) {
        return 
            (entity.getX() >= Math.min(follow.getX(), xs-hw)-hw)&&
            (entity.getX() <= Math.max(follow.getX(), hw)+hw)&&
            (entity.getY()+entity.getHeight() >= Math.min(follow.getY(), ys-hh)-hh)&&
            (entity.getY() <= Math.max(follow.getY(), hh)+hh);
    }
}
