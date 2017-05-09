package yoshimaker.maker.view;

import java.util.HashSet;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.MouseListener;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;
import yoshimaker.global.Entity;
import yoshimaker.global.cases.Type;
import static yoshimaker.maker.view.Button.BUTTONS;
import static yoshimaker.maker.view.Button.LISTENER;
import yoshimaker.map.Map;

public class MenuButtonMaker extends Entity   implements  MouseListener {
    private Button button0, button1, button2, button3, button4;
    private GameContainer container;
    private int positionX;
    protected final static HashSet<Button> BUTTONS = new HashSet();

    public MenuButtonMaker(GameContainer container, int posX) throws SlickException{
        this.container = container;
        this.positionX = posX;
        Image buttonRessources = new Image("./assets/images/test/maker.png");
        SpriteSheet buttonImage = new SpriteSheet(buttonRessources,48,64,1,2);
        int yStep = 70;
        int y = 100;
        button0 = new Button(container, this.positionX, y+0*yStep, 50, 50, Type.BRICK, buttonImage.getSubImage(2, 2));
        button1 = new Button(container, this.positionX, y+1*yStep, 50, 50, Type.ICE, buttonImage.getSubImage(12, 4));
        button2 = new Button(container, this.positionX, y+2*yStep, 50, 50, Type.SPRING, buttonImage.getSubImage(8, 6));
        button3 = new Button(container, this.positionX, y+3*yStep, 50, 50, null, buttonImage.getSubImage(5, 0));
        button4 = new Button(container, this.positionX, y+4*yStep, 50, 50, 1, buttonImage.getSubImage(10, 8));

        LISTENER.addMouseListener(this);
       
    }

    @Override
    public void mouseWheelMoved(int i) {
    }

    @Override
    public void mouseClicked(int i, int i1, int i2, int i3) {
    }

    @Override
    public void mousePressed(int i, int i1, int i2) {
    }

    @Override
    public void mouseReleased(int i, int i1, int i2) {
    }

    @Override
    public void mouseMoved(int i, int i1, int i2, int i3) {
    }

    @Override
    public void mouseDragged(int i, int i1, int i2, int i3) {
    }

    @Override
    public void setInput(Input input) {
    }

    @Override
    public boolean isAcceptingInput() {
        return true;
    }

    @Override
    public void inputEnded() {
       // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void inputStarted() {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
