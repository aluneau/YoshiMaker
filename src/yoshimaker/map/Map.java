/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package yoshimaker.map;

import yoshimaker.global.cases.Case;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import yoshimaker.global.cases.Brick;
import yoshimaker.global.cases.Empty;

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
    public Case getCase(int x, int y){
        return map[x][y];
    }
    public void createMap() {
        Case[][] grid = new Case[getY()][getX()];
        for (int i = 0; i < getX(); i++) {
            for (int j = 0; j < getY(); j++) {
                try {
                    if (j == getY()-1) {
                        grid[j][i] = new Brick(i, j);
                    }else {
                        grid[j][i] = new Empty(i, j);
                    }
                } catch (Exception e) {}
            }
        }
        map = grid;
    }

    public void printToDelete() {
        for (int i = 0; i < getX(); i++) {
            for (int j = 0; j < getY(); j++) {
                System.out.print( map[j][i].block + "  ");
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
