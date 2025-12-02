/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package logicverse.domain.gates.model;

import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import logicverse.domain.controller.Component;

/**
 * Draggable Extension of the JEComponent
 * Allows the JEComponent to Dragged
 * @author Admin
 */
public abstract class JEDraggableComponent extends JEComponent {
    /**
     * Point relative to Screen
     */
    private Point scr;
    /**
     * Point relative to itself
     */
    private Point rel;
    
    /**
     * Default Constructor
     * Initializes relevant attributes and components
     * @param obj Component Object to associate
     */
    public JEDraggableComponent(Component obj) {
        super(obj);
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
}
