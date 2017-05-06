package yoshimaker.views;

import yoshimaker.global.cases.Type;
import yoshimaker.map.Map;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;
import static yoshimaker.global.cases.Type.BRICK;

public class Ressources {
    private Image theme;
    private SpriteSheet tileset;
    public Image sprite;

    public Ressources(Map map, int x, int y) throws SlickException{
        Type block = map.getCase(x, y).block;
        switch(block){//on load le theme par rapport au theme du block
            case BRICK:
                theme = new Image("assets/images/maps/Castle.png");
                break;
            default:
                theme = new Image("assets/images/maps/Ground.png");
        }
        tileset = new SpriteSheet(theme,64,64,1);
        if((x<0 || x>=16 || y<0 || y>=16)){//image introuvable
            System.out.println("ups, soucis technique");
            sprite = tileset.getSubImage(10, 16);
            return;
        }
        sprite = tileset.getSubImage(x, y);
    }
    
    //retourne le sprite (x,y) de l'image theme
    public Ressources(Image theme, int x, int y) throws SlickException{ 
        tileset = new SpriteSheet(theme,64,64,1);
        if((x<0 || x>=16 || y<0 || y>=16)){//image introuvable
            System.out.println("ups, soucis technique");
            sprite = tileset.getSubImage(15, 15);
            return;
        }
        sprite = tileset.getSubImage(x, y);
    }
}
