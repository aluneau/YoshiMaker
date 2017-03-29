/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package map;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;

/**
 *
 * @author gaetane
 */
public class Map {

    private Case[][] map;
    private int x, y;

    public Map(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void createMap() {
        Case[][] grid = new Case[getY()][getX()];
        for (int i = 0; i < getX(); i++) {
            for (int j = 0; j < getY(); j++) {
                if (j == getY()-1) {
                    grid[j][i] = new Case(i, j, Block.BRICK);
                }else {
                    grid[j][i] = new Case(i, j);
                }
            }
        }
        map = grid;
    }

    public void printToDelete() {
        for (int i = 0; i < getX(); i++) {
            for (int j = 0; j < getY(); j++) {
                System.out.print( map[j][i].getBlock() + "  ");
            }
            System.out.println( " ");
        }
    }
    
    public void draw(GameContainer container, Graphics g) {
        for (int i = 0; i < getX(); i++) {
            for (int j = 0; j < getY(); j++) {
               map[j][i].draw(container, g);
            }
        }
    }
}
