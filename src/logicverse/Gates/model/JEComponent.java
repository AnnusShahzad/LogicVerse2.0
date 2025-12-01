/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package logicverse.Gates.model;

import java.awt.Graphics2D;
import javax.swing.JComponent;
import logicverse.controller.Component;
import logicverse.controller.Drawable;
import logicverse.model.Position;

/**
 * The Parent Class of all the View Components
 * Used by Clickable and Draggable Components like Switches and Gates
 * Can be Extended by multiple Components with new features
 * @author Admin
 */
public abstract class JEComponent extends JComponent implements Drawable {
    /**
     * Main Component for driving the Logic
     */
    public Component component;
    
    /**
     * Default Constructor
     * Associates the Component passed via Parameter
     * @param obj Component Object, Usually a Component Child
     */
    public JEComponent(Component obj) {
        component = obj;
        component.setContainer(this);
        logicverse.model.Position p = ((Position)component.getPosition());
        setLocation(p.getX(), p.getY());
        setSize(getPreferredSize());
    }
    
    /**
     * Responsible for Drawing the Component on Panel
     * Includes the Implementation on Way to Draw Component on Panel
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
