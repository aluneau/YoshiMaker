/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package yoshimaker.views;

/**
 *
 * @author punpun
 */
public abstract class View {

    /**
     * Crée les resources nécessaires à l'affichage de la vue
     */
    public abstract void init();

    /**
     * Redessine la vue
     */
    public abstract void render();

    /**
     * Met à jour le contenu de la vue
     * @param delta
     */
    public abstract void update(int delta);
}
