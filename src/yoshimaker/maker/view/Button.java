package yoshimaker.maker.view;

import java.util.HashSet;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.MouseListener;
import org.newdawn.slick.SlickException;
import yoshimaker.WindowGame;
import yoshimaker.global.Entity;
import yoshimaker.global.cases.Type;
import yoshimaker.map.Map;
import yoshimaker.views.MenuView;
import yoshimaker.views.WinView;
import yoshimaker.views.camera.Camera;

public class Button extends Entity implements  MouseListener{
    private Image image;
    private GameContainer container;
    protected final static HashSet<Button> BUTTONS = new HashSet();

    private Type type;
    public int action;
    static public Input LISTENER ;
    static private boolean onSelection = false;
    static private Type selectedType;
    static private Button selectedButton = null;
    static private int lastX = 0;
    static private int lastY = 0;
    
    protected int
        POSITION_X,
        POSITION_Y,
        SELECT_POSITION_ADD_X = 50,
        SELECT_POSITION_ADD_Y = 0;
        
    public Button(GameContainer container, int x, int y, int height, int width, Type type, String... files) throws SlickException {
        //Button(container,x,y,height,width,type,new Image(files);
        /*ptite modif de sioux*/

        //Initialiastion
        super(files);
        this.setX(x);
        this.setY(y);
        POSITION_X = x;
        POSITION_Y = y;
        this.setHeight(height);
        this.setWidth(width);
        this.container = container;
        this.action = 0;

        //Référencement
        BUTTONS.add(this);
        LISTENER.addMouseListener(this);
    }
    
    public Button(GameContainer container, int x, int y, int height, int width, Type type, Image... files) throws SlickException {
        //Initialiastion
        super(files);
        this.setX(x);
        this.setY(y);
        POSITION_X = x;
        POSITION_Y = y;
        this.setHeight(height);
        this.setWidth(width);
        this.container = container;
        this.image = files[0];
        this.action = 0;
        this.type = type;
        //Référencement
        BUTTONS.add(this);
        LISTENER.addMouseListener(this);
    }

    public Button(GameContainer container, int x, int y, int height, int width, int action, Image... files) throws SlickException {
        //Initialiastion
        super(files);
        this.setX(x);
        this.setY(y);
        POSITION_X = x;
        POSITION_Y = y;
        this.setHeight(height);
        this.setWidth(width);
        this.container = container;
        this.image = files[0];
        this.type = type;
        this.action = 1;
        //Référencement
        BUTTONS.add(this);
        LISTENER.addMouseListener(this);
    }
    public static boolean isOnSelection() {
        return onSelection;
    }

    public static void setOnSelection(boolean onSelection) {
        Button.onSelection = onSelection;
    }
    
    
    public Entity update(){
        return this;
    }

    public void draw(GameContainer container, Graphics g){
        sprite.draw(getX()+Camera.xTop, getY()+Camera.yTop, this.getHeight(), this.getWidth());
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
    // i = 0 : clic droit ; i = 1 : clic gauche
    //i1 = x ; i2 = y ; i3 = doubleclic (?)
    public void mouseClicked(int i, int i1, int i2, int i3){
        if(i == 1){ // EVENT STOP SELECTION
            if(onSelection){ 
                //System.out.println("Stop Selection : " + onSelection + " " + i);
                eventUnselect();
                return;
            }else{
                return;
            }
        }
        //PUT THE BLOCK SELECTED
        if(onSelection && i == 0 && (i2/64 < Map.CURRENT.getY()-1+Camera.yTop && i2/64 > 0 && i1/64 < Map.CURRENT.getX()+Camera.xTop && i1/64 > 0) && i1 < container.getWidth()-100){
            eventSetBlock(i1,i2);
            return;
        }
       
        if(this.container.getInput().getMouseX() < getX() || // STOP IF CLIC ISN'T ON THE BUTTON
           this.container.getInput().getMouseX() > getX()+this.getWidth() ||
           this.container.getInput().getMouseY() < getY() || 
           this.container.getInput().getMouseY() > getY()+this.getHeight())
        {
            //System.out.println("Pas sur un bouton : " + isOnSelection());
            return; 
        }
        //System.out.println("Selection");
        //SELECTION
        if(action == 0) eventSelect();
        if(action == 1) saveMap();
    }

    public void eventSelect(){
        eventUnselect();
        this.selectedButton = this;
        this.selectedType = this.type;
        setX(this.POSITION_X + this.SELECT_POSITION_ADD_X);
        setY(this.POSITION_Y + this.SELECT_POSITION_ADD_Y); 
        this.onSelection = true;
    }
    public static void eventUnselect(){
        if(selectedButton == null) return;
        selectedButton.setX(selectedButton.POSITION_X);
        selectedButton.setY(selectedButton.POSITION_Y);
        onSelection = false;
    }
    
    public static void saveMap(){
        Map.CURRENT.saveText("test");
        System.out.println("save");
        try {
            Entity.DESTROY();
            BUTTONS.clear();
            WindowGame.getInstance().view = new MenuView();
            WindowGame.getInstance().view.init(WindowGame.getInstance().container);
        } catch (SlickException ex) {  }
    }
    public static void eventSetBlock(int xMouse, int yMouse){
        int xMap = (xMouse+Camera.xTop+32)/64;
        int yMap = (yMouse+Camera.yTop+32)/64;
        if(selectedType != null && Map.CURRENT.getCase(xMap, yMap) != null && Map.CURRENT.getCase(xMap, yMap).type.compareTo(selectedType) == 0){
           System.out.println("ALREADY BLOCK");         
            return;
        }else{
            System.out.println(selectedType + " " + Map.CURRENT.getCase(xMap, yMap));
        }

        if(Map.CURRENT.getCase(xMap, yMap) != null){
            Map.CURRENT.deleteCase(xMap, yMap);
        }
        //System.out.println("Put Block !");
        Map.CURRENT.setCase(xMap, yMap, selectedType);

    }
    @Override
    public void mousePressed(int i, int i1, int i2) {
        
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
