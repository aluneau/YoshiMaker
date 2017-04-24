/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package yoshimaker.views;

import javax.swing.JLabel;
import yoshimaker.maker.Constructor;
import yoshimaker.map.Block;
import static yoshimaker.map.Block.NOTHING;

/**
 *
 * @author gaetane
 */
public class MakerCase extends JLabel{
    int x;
    int y;
    int styleType;
    Block type;
    
    public MakerCase(){
        type = NOTHING;
    }

    public MakerCase( int c){
        styleType = c;
    }
}
