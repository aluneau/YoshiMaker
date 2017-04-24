/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package yoshimaker.views;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.util.Observable;
import java.util.Observer;
import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import yoshimaker.maker.Constructor;
import yoshimaker.map.Map;
import yoshimaker.views.Button;

/**
 *
 * @author gaetane
 */
public class ViewMaker implements Observer {

    public JFrame createMaker = new JFrame();
    private JPanel map;
    private JPanel style;
    private final Constructor control;

    public ViewMaker(Constructor c) {
        this.control = c;
        createMaker.setTitle(" Maker Map ");
        createMaker.setSize(control.makerMap.getX()*7, control.makerMap.getY()*7);
        createMaker.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        createMaker.setLayout(new BorderLayout(5, 5));

        createMaker.add(typeBrick(), BorderLayout.NORTH); // les différents type de bric possible
        createMaker.add(makeMap(control.makerMap.getX(), control.makerMap.getY()), BorderLayout.CENTER); // la map
        System.out.println(" vu ");

        createMaker.setVisible(true);
        System.out.println(" vu ");

    }

    private JComponent makeMap(int x, int y) {
        map = new JPanel();
        System.out.println(x + " " + y);
        map.setLayout(new BorderLayout(1, 1));
        map.setLayout(new GridLayout(y, x, 1, 1));
        for (int j = 0; j < y; j++) {
            for (int i = 0; i < x; i++) {
                Button c = new Button();
                c.addMouseListener(control);
                map.add(c);
            }
        }
        return map;
    }

    private JComponent typeBrick() {
        style = new JPanel();
        style.setLayout(new BorderLayout(7, 7));
        style.setLayout(new GridLayout(1, 5, 7, 7));
        for (int j = 0; j < 1; j++) {
            for (int i = 0; i < 5; i++) {
                Button c = new Button();
                c.addMouseListener(control);
                style.add(c);
            }
        }
        return style;
    }

    @Override
    public void update(Observable o, Object arg) {
        if (o instanceof Map) {
            for (int j = 0; j < control.makerMap.getY(); j++) {
                for (int i = 0; i < control.makerMap.getX(); i++) {
                    visualCase(i, j);
                }
            }
        }
        for (int k = 0; k < 5; k++) {
            boxBlock(k);
        }
    }

    private void visualCase(int i, int j) {
        Button c = getMakerCase(i,j);
        c.object = control.makerMap.getCase(i, j).getBlock();
        switch (c.object) {
            case ICE:
                System.out.println(" ice ");
                addIcone(c, "assets/ice.png");
                break;
            case LAVA:
                System.out.println(" lave ");
                addIcone(c, "assets/feu.png");
                break;
            case BRICK:
                System.out.println(" brique ");
                addIcone(c, "assets/brick.png");
                break;
            case PICK:
                addIcone(c, "assets/picpic.png");
                break;
        }
    }

    private void boxBlock(int k) {
        Button c = new Button(k);
        switch (k) {
            case 0:
                addIcone(c, "assets/ice.png");
                break;
            case 1:
                addIcone(c, "assets/brick.png");
                break;
            case 2:
                addIcone(c, "assets/picpic.png");
                break;
            case 3:
                addIcone(c, "assets/feu.png");
                break;
            case 4:
                addIcone(c, "assets/nuage.png");
                break;                
        }
    }

    private void addIcone(Button c, String srcImage) {
        ImageIcon icon = new ImageIcon(srcImage);
        Image image = icon.getImage(); // l'image transforme
        System.out.println(c.getWidth());
        Image newimg = image.getScaledInstance(c.getWidth()+7, c.getHeight()+7, java.awt.Image.SCALE_SMOOTH); // scale it the smooth way  
        icon = new ImageIcon(newimg);  // transform it back 
        c.setText(null);
        c.setIcon(icon);
    }

    private void addIconeToLabel(MakerCase c, String srcImage) {
        ImageIcon icon = new ImageIcon(srcImage);
        Image image = icon.getImage(); // l'image transforme
        Image newimg = image.getScaledInstance(c.getWidth(), c.getHeight(), java.awt.Image.SCALE_SMOOTH); // scale it the smooth way  
        icon = new ImageIcon(newimg);  // transform it back 
        c.setText(null);
        c.setIcon(icon);
    }

    private Button getMakerCase(int i, int j) {
        return (Button) map.getComponent(j * control.makerMap.getX() + i);
    }

}
