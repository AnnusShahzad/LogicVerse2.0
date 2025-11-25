/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package logicverse.Gates.model;

import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JComponent;
import logicverse.controller.Component;
import logicverse.controller.Drawable;

/**
 *
 * @author Admin
 */
public abstract class JEComponent extends JComponent implements Drawable {
    protected Component component;
    private Point scr;
    private Point rel;
    public JEComponent(Component obj) {
        component = obj;
        scr = new Point();
        rel = new Point();
        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
              scr.x = e.getXOnScreen();
              scr.y = e.getYOnScreen();

              rel.x = getX();
              rel.y = getY();
            }
        });
        addMouseMotionListener(new MouseAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
              int deltaX = e.getXOnScreen() - scr.x;
              int deltaY = e.getYOnScreen() - scr.y;
              setLocation(rel.x + deltaX, rel.y + deltaY);
              component.setLocation(rel.x + deltaX, rel.y + deltaY);
            }
        });
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
