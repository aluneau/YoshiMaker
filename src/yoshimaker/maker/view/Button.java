package yoshimaker.maker.view;

import java.util.HashSet;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.MouseListener;
import org.newdawn.slick.SlickException;
import yoshimaker.global.Entity;

public class Button extends Entity implements  MouseListener{
    private Image image;
    private GameContainer container;
    protected final static HashSet<Button> BUTTONS = new HashSet();
    static public Input LISTENER ;
    
    public Button(GameContainer container, int x, int y, int height, int width, String... files) throws SlickException {
        //Initialiastion
        super(files);
        this.setX(x);
        this.setY(y);
        this.setHeight(height);
        this.setWidth(width);
        this.container = container;
        //Référencement
        BUTTONS.add(this);
        LISTENER.addMouseListener(this);
    }
    
    public Button(GameContainer container, int x, int y, int height, int width, Image... files) throws SlickException {
        //Initialiastion
        super(files);
        this.setX(x);
        this.setY(y);
        this.setHeight(height);
        this.setWidth(width);
        this.container = container;
        //Référencement
        BUTTONS.add(this);
        LISTENER.addMouseListener(this);
    }
    
    public Entity update(){
        return this;
    }

    public void draw(GameContainer container, Graphics g){
        sprite.draw(getX(), getY(), this.getHeight(), this.getWidth());
    }   
    
    
    public static void drawAll(GameContainer container, Graphics g) {
        //Dessine toutes les entités
        for (Button button : BUTTONS) { button.draw(container, g); }
    }
    
    @Override
    public void mouseWheelMoved(int i) {
       // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseClicked(int i, int i1, int i2, int i3) {
        if(this.container.getInput().getMouseX() < getX() || 
           this.container.getInput().getMouseX() > getX()+this.getWidth() ||
           this.container.getInput().getMouseY() < getY() || 
           this.container.getInput().getMouseY() > getY()+this.getHeight()) 
                return;
        
        eventDecale();
        
    }

    public void eventDecale(){
        if(getX() == 100 ) setX(200);
        else setX(100);    
    }
    @Override
    public void mousePressed(int i, int i1, int i2) {
       // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseReleased(int i, int i1, int i2) {
       // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseMoved(int i, int i1, int i2, int i3) {
      //  throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseDragged(int i, int i1, int i2, int i3) {
      //  throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setInput(Input input) {
      //  throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean isAcceptingInput() {
        return true;      
        //  throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void inputEnded() {
      //  throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void inputStarted() {
       //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    
    
}
