/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package map;

/**
 *
 * @author gaetane
 */
public class Case {
    private final int x,y;
    private Block block;
    
    public Case(int x, int y){
        this.x = x;
        this.y = y;
        block = Block.NOTHING;
    }
    
    public Case(int x, int y, Block block){
        this.x = x;
        this.y = y;
        this.block = block;        
    } 

    public Block getBlock() {
        return block;
    }

    public void setBlock(Block block) {
        this.block = block;
    }
    
    
    
}
