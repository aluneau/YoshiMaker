/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package yoshimaker.maker;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import yoshimaker.map.Block;
import yoshimaker.map.Map;
import yoshimaker.views.Button;
import yoshimaker.views.ViewMaker;

/**
 *
 * @author gaetane
 */
public class Constructor implements MouseListener {
    public Map makerMap;
    private Block blockSelect;
    public ViewMaker view;
    
    public Constructor(){
        System.out.println(" Début du maker ");
        makerMap = new Map(15,15);
        view = new ViewMaker(this);
        makerMap.addObserver(view);
        makerMap.update();
    }
    

    @Override
    public void mouseClicked(MouseEvent e) { // selectionner ou disposé un block particulier
        Button source = (Button) e.getSource();
        if(blockSelect ==  Block.NOTHING ){
            blockSelect = source.object;
            System.out.println(blockSelect);
        }
        else if(blockSelect != Block.NOTHING){
            makerMap.changeCase(source.x,source.y, source.object);
        }
    }

    @Override
    public void mousePressed(MouseEvent e) { // pas utilisé
    }

    @Override
    public void mouseReleased(MouseEvent e) { // pas utilisé
    }

    @Override
    public void mouseEntered(MouseEvent e) { // pas utilisé
    }

    @Override
    public void mouseExited(MouseEvent e) { // déplacement de l'image si possible 
    }
    
    
    
}
