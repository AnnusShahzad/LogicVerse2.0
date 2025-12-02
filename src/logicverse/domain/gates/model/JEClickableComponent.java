/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package logicverse.domain.gates.model;

import java.awt.Cursor;
import java.awt.Graphics2D;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JButton;
import javax.swing.JComponent;
import logicverse.domain.controller.Component;
import logicverse.domain.controller.Drawable;

/**
 * Clickable extension of JEComponent
 * Includes Clickable as well as Draggable features
 * @author Admin
 */
public abstract class JEClickableComponent extends JEDraggableComponent {
   /**
    * Default Constructor
    * Initializes related attributes and components
    * @param obj Component Object to associate
    */
    public JEClickableComponent(Component obj) {
        super(obj);
        this.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                component.click();
                repaint();
            }
        });
        this.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
    }
    /**
     * Responsible for drawing the component
     * @param obj Graphics2D Object
     */
    public abstract void draw(Graphics2D obj);
    
//    @Override
//    public Point getLocation() {
//        Point p = super.getLocation();
//        p.x += super.getWidth()/2;
//        p.y += super.getHeight()/2;
////        System.out.println(p.x + " " + p.y + " " + super.getWidth() + " " + super.getHeight());
//        return p;
//    }
}
