/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package yoshimaker.title;

import java.awt.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.jbox2d.dynamics.BodyType;
import org.newdawn.slick.Animation;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.MouseListener;
import org.newdawn.slick.SlickException;
import yoshimaker.WindowGame;
import yoshimaker.global.Entity;
import static yoshimaker.global.Entity.ENTITIES;
import yoshimaker.maker.view.MakerView;
import yoshimaker.views.GameViewDeluxe;
import yoshimaker.views.LevelLoadedView;
import yoshimaker.views.LevelView;
import yoshimaker.views.MenuView;
import yoshimaker.views.TitleScreen;
import yoshimaker.views.WinView;

/**
 *
 * @author punpun
 */
public class Button extends Entity implements MouseListener {
    
    private Animation iddle;
    private Animation hover;
    private int action;

    public Button(int i, String... files) throws SlickException {
        //Initialiastion
        super(files[0]);
        this.iddle = new Animation() ;
        this.iddle.addFrame(new Image(files[0]), 1000);
        this.hover = new Animation() ;
        this.hover.addFrame(new Image(files[1]), 1000);
        this.action = i ;
        
        this.sprite = this.iddle ;
        this.setWidth(200).setHeight(50);
    }
    
    @Override
    public void mouseWheelMoved(int change) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseClicked(int button, int x, int y, int clickCount) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        if ((x >= getX()-getWidth()/2)&&(x <= getX()+getWidth()/2)&&(y >= getY()-getHeight()/2)&&(y <= getY()+getHeight()/2)) {
            switch (action) {
                //Menu
                case 0:
                    WindowGame.getInstance().view = new MenuView();
                    try {
                        MenuView.music.stop();
                        WindowGame.getInstance().view.init(WindowGame.getInstance().container);
                    } catch (SlickException ex) { }
                    break;
                //Ecran de jeu ?
                case 1:
                    WindowGame.getInstance().view = new TitleScreen();
                    try {
                        MenuView.music.stop();
                        WindowGame.getInstance().view.init(WindowGame.getInstance().container);
                    } catch (SlickException ex) {  }
                    break;
                    
                //Editeur
                case 2:
            
                   try {
                        MenuView.music.stop();
                        WindowGame.getInstance().view = new GameViewDeluxe();
                        WindowGame.getInstance().view.init(WindowGame.getInstance().container);
                    } catch (SlickException ex) {
                        Logger.getLogger(Button.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    break;
                //Ecran de jeu ?
                case 3:
                        try {
                            MenuView.music.stop();
                            WindowGame.getInstance().view = new LevelView();
                            WindowGame.getInstance().view.init(WindowGame.getInstance().container);
                        } catch (SlickException ex) {
                            Logger.getLogger(Button.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    
                    break;
                    
                case 100:
                    try {

                        MenuView.music.stop();
                        LevelView.music.stop();
                        LevelLoadedView v = new LevelLoadedView();
                        v.level = "level1";
                        WindowGame.getInstance().view = v;
                        WindowGame.getInstance().view.init(WindowGame.getInstance().container);
                    } catch (SlickException ex) {
                        Logger.getLogger(Button.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    break;
                case 101:
                    try {
                        MenuView.music.stop();
                        LevelView.music.stop();
                        LevelLoadedView v = new LevelLoadedView();
                        v.level = "level2";
                        WindowGame.getInstance().view = v;
                        WindowGame.getInstance().view.init(WindowGame.getInstance().container);
                    } catch (SlickException ex) {
                        Logger.getLogger(Button.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    break;
                case 102:
                    try {
                        MenuView.music.stop();
                        LevelView.music.stop();
                        LevelLoadedView v = new LevelLoadedView();
                        v.level = "level3";
                        WindowGame.getInstance().view = v;
                        WindowGame.getInstance().view.init(WindowGame.getInstance().container);
                    } catch (SlickException ex) {
                        Logger.getLogger(Button.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    break;
                case 103:
                    try {
                        MenuView.music.stop();
                        LevelView.music.stop();
                        LevelLoadedView v = new LevelLoadedView();
                        v.level = "level5";
                        WindowGame.getInstance().view = v;
                        WindowGame.getInstance().view.init(WindowGame.getInstance().container);
                    } catch (SlickException ex) {
                        Logger.getLogger(Button.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    break;    
                case 104:
                    try {
                        MenuView.music.stop();
                        LevelView.music.stop();
                        LevelLoadedView v = new LevelLoadedView();
                        v.level = "level12";
                        WindowGame.getInstance().view = v;
                        WindowGame.getInstance().view.init(WindowGame.getInstance().container);
                    } catch (SlickException ex) {
                        Logger.getLogger(Button.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    break;
            } 
        }
    }

    @Override
    public void mousePressed(int button, int x, int y) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseReleased(int button, int x, int y) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseMoved(int oldx, int oldy, int newx, int newy) {
        if ((newx >= getX()-getWidth()/2)&&(newx <= getX()+getWidth()/2)&&(newy >= getY()-getHeight()/2)&&(newy <= getY()+getHeight()/2)) {
            this.sprite = this.hover;
        } else { this.sprite = this.iddle ; }
    }

    @Override
    public void mouseDragged(int oldx, int oldy, int newx, int newy) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setInput(Input input) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean isAcceptingInput() {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        return true;
    }

    @Override
    public void inputEnded() {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void inputStarted() {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
