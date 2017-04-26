/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package yoshimaker.maker;

import org.newdawn.slick.Input;
import org.newdawn.slick.MouseListener;
import yoshimaker.global.cases.Type;
import yoshimaker.map.CaseOld;

/**
 *
 * @author gaetane
 */
public class CaseEditor extends CaseOld implements MouseListener {
    /* chaque case aura l'information que la souris à relacher un objet au dessus 
    * d'une case précise.
    */
    private CaseOld editCase;
    private Type blockSelect = null;

    public CaseEditor(CaseOld c) {
        super(c);
    }

    public void checkCase(int x, int y){ // vérifier si la case n'a pas déjà un attribut sinon il doit remove
        if( editCase.block != blockSelect){
            editCase = new CaseOld(x,y,blockSelect);
        }
    } 

    public Type getBlockSelect() {
        return blockSelect;
    }

    public void setBlockSelect(Type blockSelect) {
        this.blockSelect = blockSelect;
    }

    @Override
    public void mouseWheelMoved(int i) {
    }   

    @Override
    public void mouseClicked(int button, int x, int y, int clickCount) {
        if( editCase.isCase() && blockSelect != null ){
            checkCase(x,y);
        }else if( !editCase.isCase()){
            blockSelect =  editCase.block;
        } /*else if( editCase.isCase() && blockSelect == null){
            
        } */  
    }

    @Override
    public void mousePressed(int i, int i1, int i2) {
    }

    @Override
    public void mouseReleased(int i, int i1, int i2) {
    }

    @Override
    public void mouseMoved(int i, int i1, int i2, int i3) {
    }

    @Override
    public void mouseDragged(int oldX, int oldY, int newX, int newY) {
        if( blockSelect != null && editCase.isCase() ){
            checkCase(newX,newY);
        }
    }

    @Override
    public void setInput(Input input) {
    }

    @Override
    public boolean isAcceptingInput() {
        return true;
    }

    @Override
    public void inputEnded() {
    }

    @Override
    public void inputStarted() {
    }  
}