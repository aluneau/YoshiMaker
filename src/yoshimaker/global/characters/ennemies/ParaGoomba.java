/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package yoshimaker.global.characters.ennemies;

import org.newdawn.slick.Animation;
import org.newdawn.slick.SlickException;
import static yoshimaker.global.characters.ennemies.Goomba.WALKING;

/**
 *
 * @author punpun
 */
public class ParaGoomba extends Goomba {
    public ParaGoomba(int x, int y) throws SlickException {
       super(x, y);
       this.sprite = new Animation(WALKING, 2, 0, 4, 0, true, 1, true);
       this.sprite.setSpeed(0.01f);
    }
    
    static {
        RESTITUTION = 0.95f;
    }
}

     