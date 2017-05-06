package yoshimaker.global.items;

import java.io.IOException;

import java.util.logging.Level;
import java.util.logging.Logger;
import org.newdawn.slick.Animation;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;
import yoshimaker.global.cases.Type;
import yoshimaker.map.Map;

public class Door{
    private Image theme;
    private SpriteSheet tileset;
    public Image sprite;
    
    public Door(int x, int y) throws SlickException{
        theme = new Image("./assets/images/test/doorB.png");
        tileset = new SpriteSheet(theme,78,156,1);
        sprite = tileset.getSubImage(1, 0);
        sprite.draw(x, y, 64, 100);
        
       Animation open = new Animation(new SpriteSheet("./assets/images/test/doorB.png", 78, 156,1),100);
       open.start();
    }
    
}
