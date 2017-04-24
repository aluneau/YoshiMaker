/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package yoshimaker.views;

import javax.swing.JButton;
import yoshimaker.map.Block;
import static yoshimaker.map.Block.NOTHING;

/**
 *
 * @author gaetane
 */
public class Button extends JButton{
    public int x;
    public int y;
    public int type;
    public Block object;

    
    public Button(int i){
        type = i;
    }
    
    public Button(){
        object = NOTHING;
    }
    
}
