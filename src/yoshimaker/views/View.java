/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package yoshimaker.views;

import org.newdawn.slick.*;
import yoshimaker.WindowGame;
import yoshimaker.views.camera.Camera;

/**
 *
 * @author punpun
 */
public abstract class View  {

    public static UnicodeFont fontTest = new UnicodeFont(new java.awt.Font("Arial", java.awt.Font.PLAIN, 20));

    /**
     * Crée les resources nécessaires à l'affichage de la vue
     * @param container
     * @throws org.newdawn.slick.SlickException
     */
    public abstract void init(GameContainer container) throws SlickException;

    /**
     * Redessine la vue
     * @param container
     * @param g
     */
    public abstract void render(GameContainer container, Graphics g);

    /**
     * Met à jour le contenu de la vue
     * @param container
     * @param delta
     */
    public abstract void update(GameContainer container, int delta);

    /**
     * Controller
     * @param input
     */
    public abstract void controller(Input input);
    
    /**
     * Controller
     * @param key
     * @param c 
     */
    public abstract void controller(int key, char c, boolean type);


    public Camera camera() { return WindowGame.getInstance().getCamera(); }

    public void destroy() {
        
    }
}
