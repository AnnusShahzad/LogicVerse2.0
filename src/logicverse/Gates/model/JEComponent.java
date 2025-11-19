/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package logicverse.Gates.model;

import java.awt.Graphics2D;
import java.awt.Point;
import javax.swing.JComponent;
import logicverse.controller.Component;
import logicverse.controller.Drawable;

/**
 *
 * @author Admin
 */
public abstract class JEComponent extends JComponent implements Drawable {
    protected Component component;
    
    public JEComponent(Component obj) {
        component = obj;
    }
    
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
