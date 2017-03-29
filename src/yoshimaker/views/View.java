/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package yoshimaker.views;

import org.newdawn.slick.BasicGame;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

/**
 *
 * @author punpun
 */
public abstract class View  {
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
}
