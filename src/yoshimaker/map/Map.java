/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package yoshimaker.map;

import yoshimaker.global.cases.Case;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import yoshimaker.global.cases.Brick;
import yoshimaker.global.cases.Empty;
import yoshimaker.global.cases.Ice;
import yoshimaker.global.cases.Spring;
import yoshimaker.global.cases.Type;

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

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
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
    
    public Map setCase(int x, int y, Type type) {
        try {
            map[y][x].destroy();
            
            switch (type) {
                case ICE: map[y][x] = new Ice(x, y);break;
                case SPRING: map[y][x] = new Spring(x, y);break;
            }
            
        } catch (Exception ignore) {
            Logger.getLogger(Map.class.getName()).log(Level.SEVERE, null, ignore);
        }
        return this;
    }
    
    public void createMap() {
        map = new Case[getY()][getX()];
        for (int i = 0; i < getX(); i++) {
            for (int j = 0; j < getY(); j++) {
                try {
                    if (j == getY()-1) {
                        map[j][i] = new Brick(i, j);
                    }else {
                        map[j][i] = new Empty(i, j);
                    }
                } catch (Exception e) {}
            }
        }
    }

   /* public void draw(GameContainer container, Graphics g) {
        for (int i = 0; i < getX(); i++) {
            for (int j = 0; j < getY(); j++) {
               map[j][i].draw(container, g);
            }
        }
    }*/
    // sauvegarder une partie
    public void save() throws IOException {
        // Fichier dans lequel on va écrire;
        File fichier = new File("test1.yoshiMaker");
        // Ouverture du flux du fichier 
        ObjectOutputStream fluxFichier = new ObjectOutputStream(new FileOutputStream(fichier));
        // Serialisation de l'objet
        fluxFichier.writeInt(getX());
        fluxFichier.writeInt(getY());
        for (int j = 0; j < getY(); j++) {
            for (int i = 0; i < getX(); i++) {
                map[j][i].writeObject(fluxFichier);
            }
        }
        System.out.println(" Sérialisation ok");
    }

    //récuperer un niveau ou reprendre une partie
    public void load() throws IOException, ClassNotFoundException {
        File fichier = new File("test1.yoshiMaker");
        // Ouverture du flux fichier pour récuperer
        ObjectInputStream fluxRentrant = new ObjectInputStream(new FileInputStream(fichier));
        System.out.println(" 2 ");
        setX(fluxRentrant.readInt());
        setY(fluxRentrant.readInt());
        for (int j = 0; j < getY(); j++) {
            for (int i = 0; i < getX(); i++) {
                map[j][i].readObject(fluxRentrant);
            }
        }
        System.out.println(" Normalement déserializé ");
    }    
    
}
