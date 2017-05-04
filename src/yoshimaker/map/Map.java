/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package yoshimaker.map;

import java.io.BufferedReader;
import yoshimaker.global.cases.Case;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Observable;
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
public class Map extends Observable {

    private Case[][] map;
    private int x, y;
    public final static int WIDTH = 64, HEIGHT = 64;
    
    public static Map CURRENT;

    public Map(int x, int y) {
        this.x = x;
        this.y = y;
        createMap();
        CURRENT = this;
    }
    
    public Type whatIs(int x, int y) {
        x = Math.max(0, Math.min(getX()-1, Math.floorDiv(x, WIDTH)));
        y = Math.max(0, Math.min(getY()-1, Math.floorDiv(y, HEIGHT)));
        Case tmp = getCase(x, y);
        return tmp == null ? Type.EMPTY : tmp.type;
    }

    public Map(){
        
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

    public Case getCase(int x, int y) {
        return map[y][x];
    }
    
    public void setLevel1(){
        setCase(2, 8, Type.ICE);
        setCase(2, 8, Type.EMPTY);
        setCase(3, 8, Type.ICE);
        setCase(11, 8, Type.SPRING);
        setCase(8, 4, Type.BRICK);
        setCase(8, 5, Type.BRICK);
        setCase(8, 6, Type.BRICK);
        setCase(8, 7, Type.BRICK);
        setCase(8, 7, Type.ICE);

        setCase(10, 8, null);
        setCase(9, 8, null);
    }
    
    public void readLevel(String lvlname){
        //Fonction qui permettrait de lire un fichier serialiser et de le prendre comme map
    }

     public Map setCase(int x, int y, Type type) {
        try {
            if (map[y][x] != null) { map[y][x].destroy(); }
            
            switch (type) {
                case BRICK: map[y][x] = new Brick(x, y);break;
                case ICE: map[y][x] = new Ice(x, y);break;
                case SPRING: map[y][x] = new Spring(x, y);break;
                default: map[y][x] = null;
            }
            
        } catch (Exception ignore) { }
        return this;    
    }
    public void deleteCase(int x, int y){
        /***
        *** ALED : Ne supprime pas l'image : gros fail sur l'affichage.
        ***/
        if (y < getY()-1 && y > 0 && x < getX()-1 && x > 0) {
            if(map[y][x] != null && !map[y][x].equals(Type.EMPTY)){
                /* supprimer ici */
                map[y][x] = null;
                System.out.println("Block delete");
            }
        }
    }
    private void createMap() {
        map = new Case[getY()][getX()];
        for (int i = 0; i < getX(); i++) {
            for (int j = 0; j < getY(); j++) {
                try {
                    if (j == getY()-1 || j == 0 || i == getX()-1 || i== 0) { setCase(i, j, Type.BRICK); }
                } catch (Exception e) {}
            }
        }
    }

    public void move(int xOffset, int yOffset) {
        for (int i = 0; i < getX(); i++) {
            for (int j = 0; j < getY(); j++) {
                try {
                    System.out.println("X: " + map[j][i].getX() + ", Y: " + map[j][i].getY());
                    map[j][i].setX(map[j][i].getX() + xOffset);
                    map[j][i].setY(map[j][i].getY() + yOffset);
                    System.out.println("X: " + map[j][i].getX() + ", Y: " + map[j][i].getY());
                } catch (Exception ignore) {
                }
                if (j == getY()-1 || j == 0 || i == getX()-1 || i== 0) { setCase(i, j, Type.BRICK); }
            }
        }
    }

    public void draw(GameContainer container, Graphics g) {
        for (int i = 0; i < getX(); i++) {
            for (int j = 0; j < getY(); j++) {
               map[j][i].draw(container, g);
            }
        }
    }

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

    public void changeCase(int x, int y, Type etat) {
        map[y][x].setBlock(etat);
    }

    public void loadText(String name){
        String fileName = name + ".txt";// fichier 
        String[] saveBlock = null;      // récuperation de la ligne 
        String ligne;
        boolean firstLine = true;
        
        try {
            InputStream flux = new FileInputStream(fileName);
            InputStreamReader lecture = new InputStreamReader(flux);
            BufferedReader buff = new BufferedReader(lecture);
            this.y = knowSize(buff);
            while((ligne = buff.readLine()) != null){
                saveBlock = ligne.split(" ");
                if(firstLine == true){
                    this.x = saveBlock.length;
                    createMap();
                }
                ligneMap(saveBlock,saveBlock.length);
            }          
        }catch( Exception e ){
            System.out.println(e.toString());    
        }   
    }
    
    private int knowSize(BufferedReader nameFile){
        int y = 0;
        try {
            while((nameFile.readLine() !=null)){
                
                y ++;
            }
        } catch (IOException ex) {
        }
        return y;
    }
    
    private void ligneMap(String[] saveBlock, int hauteur ){
        for(int i = 0 ; i < saveBlock.length ; i++){
            setCase(i,hauteur,valueBlock(saveBlock[i]));
        }
    }
    
    private Type valueBlock(String donne){
        switch (donne) {
            case "B": // brique
                return Type.BRICK;
            case "_": // vide
                return Type.EMPTY;
            case "I":// glace
                return Type.ICE;
            case "P": // pic
                return Type.PICK;
            case "L": // lave
                return Type.LAVA;
            case "S":// ressort
                return Type.SPRING;
            default:
                return Type.EMPTY;
        }
    }
}    
