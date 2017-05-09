/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package yoshimaker.map;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import yoshimaker.global.cases.Case;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.LineNumberReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Observable;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import yoshimaker.global.Entity;
import yoshimaker.global.cases.Brick;
import yoshimaker.global.cases.DoorBrick;
import yoshimaker.global.cases.Empty;
import yoshimaker.global.cases.Ice;
import yoshimaker.global.cases.Lava;
import yoshimaker.global.cases.Spring;
import yoshimaker.global.cases.Type;
import yoshimaker.global.characters.ennemies.Boo;
import yoshimaker.global.characters.ennemies.Goomba;
import yoshimaker.global.characters.ennemies.Koopa;
import yoshimaker.global.characters.ennemies.ParaGoomba;
import yoshimaker.global.characters.ennemies.Thwomp;
import yoshimaker.global.characters.players.Yoshi;
import yoshimaker.global.characters.players.Yoshi2;
import yoshimaker.global.items.Star;

/**
 *
 * @author gaetane
 */
public class Map extends Observable {

    private Case[][] map;
    private int x, y;
    public final static int WIDTH = 64, HEIGHT = 64;
    public Yoshi y1;
    public Yoshi2 y2;
    public static Map CURRENT;

    public Map(int x, int y) {
        this.x = x;
        this.y = y;
        createMap();
        CURRENT = this;
    }
    
    public Map(String fileName){
        loadText(fileName);
    }
    
    public Type whatIs(int x, int y) {
        x = Math.max(0, Math.min(getX()-1, Math.floorDiv(x, WIDTH)));
        y = Math.max(0, Math.min(getY()-1, Math.floorDiv(y, HEIGHT)));
        Case tmp = getCase(x, y);
        return tmp == null ? Type.EMPTY : tmp.type;
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
    }
    
    public void readLevel(String lvlname){
        //Fonction qui permettrait de lire un fichier serialiser et de le prendre comme map
    }

     public Map setCase(int x, int y, Type type) {
        try {
            if(map[y][x] instanceof Case) map[y][x].destroy();
            switch (type) {
                case BRICK: map[y][x] = new Brick(x, y);break;
                case ICE: map[y][x] = new Ice(x, y);break;
                case SPRING: map[y][x] = new Spring(x, y);break;
                case LAVA: map[y][x] = new Lava(x, y);break;
                case DOORBRICK: map[y][x] = new DoorBrick(x, y); break;
                default: map[y][x] = null;
            }
            
        } catch (Exception ignore) { }
        return this;    
    }
     
    public void check(){
        System.out.println(whatIs(0, 0));
    }
    
    public void deleteCase(int x, int y){
        /***
        *** ALED : Ne supprime pas l'image : gros fail sur l'affichage.
        ***/
        if (y < getY()-1 && y > 0 && x < getX()-1 && x > 0) {
            if(map[y][x] != null && !map[y][x].equals(Type.EMPTY)){
                if(map[y][x] instanceof Case) map[y][x].destroy();
                map[y][x] = null;
                System.out.println("Block delete");
            }
        } /*catch (Exception ex) { 
        }*/
    }
    public void createMap() {
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
        String fileName = name+".txt";  // fichier 
        String[] saveBlock = null;      // récuperation de la ligne 
        String ligne;
        boolean firstLine = true;
        try {
            FileInputStream flux = new FileInputStream(fileName);
            InputStreamReader lecture = new InputStreamReader(flux);
            BufferedReader buff = new BufferedReader(lecture);
            y = knowSize(fileName);
            int l = 0;
            while((ligne = buff.readLine()) != null){
                saveBlock = ligne.split(" ");
                if(firstLine == true){
                    x = saveBlock.length;
                    map = new Case[y][x];
                    firstLine = false;
                }
                ligneMap(saveBlock, l++);
            }          
            Map.CURRENT = this;
        }catch( Exception e ){
            System.out.println(e.toString());    
        }   
    }
    
    public void saveText(String name) {
        String fileName = name+ ".txt";
        FileWriter fw;
        try {
            fw = new FileWriter(fileName,true);
            BufferedWriter output = new BufferedWriter(fw);
            for (int j=0; j<this.y ; j++){
                for(int i=0; i<this.x; i++){
                    if( map[j][i] == null){
                        output.write("_ ");
                    }else{
                        String titre = inversValue(map[j][i].type.toString());
                        output.write(titre);
                        output.flush();                        
                    }                  
                }
                output.newLine(); 
            }
            output.close();
        } catch (IOException ex) {
            System.out.println("oups");        
        }
        
    }
    
    
    private int knowSize(String nameFile) throws IOException{
        FileInputStream flux = new FileInputStream(nameFile);
        int count = 0;
        LineNumberReader l = new LineNumberReader(new BufferedReader(new InputStreamReader(flux)));
        String str;
        while ((str=l.readLine())!=null){
            count = l.getLineNumber();
        }
        return count;
    }
    
    private void ligneMap(String[] saveBlock, int hauteur ) throws SlickException{  
        for(int i = 0 ; i < saveBlock.length ; i++){
            switch( saveBlock[i] ){
                case "y2":
                    Yoshi2 y2 = new Yoshi2(90, 9*64);
                    break;
                case "b":
                    Boo boo = new Boo(64*i, 64*hauteur);
                    break;
                case "p":
                    ParaGoomba pg = new ParaGoomba(64*i,64* hauteur);
                    break;
                case "k":
                    Koopa k = new Koopa(64*i, 64*hauteur);
                    break;
                case "t":
                    Thwomp t = new Thwomp(64*i, 64*hauteur);
                    break;
                case "g":
                    Goomba g = new Goomba(64*i,64*hauteur);
                    break;
                case "s":
                    new Star(64*i,64*hauteur);
                    break;
                default:    
                    setCase(i,hauteur,valueBlock(saveBlock[i]));        
            }
        }
    }
    
    private Type valueBlock(String donne){
        switch (donne) {
            case "B": // brique
                return Type.BRICK;
            case "_": // vide
                return null;
            case "I":// glace
                return Type.ICE;
            case "P": // pic
                return Type.PICK;
            case "L": // lave
                return Type.LAVA;
            case "S":// ressort
                return Type.SPRING;
            default:
                return null;
        }
    }
    private String inversValue(String type){
        System.out.println( "inversValue : " + type );
        switch (type) {
            case "BRICK": // brique
                return "B ";
            case "EMPTY": // vide
                return "_ ";
            case "ICE" :// glace
                return "I ";
            case "PICK": // pic
                return "P ";
            case "LAVA": // lave
                return "L ";
            case "SPRING":// ressort
                return "S ";

            default:
                return "_ ";
        }          
    }
}    
