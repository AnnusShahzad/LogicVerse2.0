/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package logicverse.Gates.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import logicverse.Gates.controller.AND;
import logicverse.Gates.model.JEDraggableComponent;
import logicverse.controller.AbstractComponent;
import logicverse.controller.Component;

/**
 *
 * @author Admin
 */
public class JEAND extends JEDraggableComponent {
    final int d = 25;
    final int l = 20;
    final int r = 10;
    final int disp_x = 6;
    final int disp_y = 0;
    public JEAND() {
        super(new AND());
    }
    public JEAND(Component c) {
        super(c);
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(111, 111);
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.RED);
        draw((Graphics2D) g);
    }

    public void draw(Graphics2D obj) {
        int w = getWidth();
        int h = getHeight();
        int cx = w/2;
        int cy = h/2;
        //obj.drawRect(0, 0, 110, 105);
        obj.drawLine(disp_x, 2*h/5, disp_x+l, 2*h/5); // Input 1
        obj.drawLine(disp_x, 3*h/5, disp_x+l, 3*h/5); // Input 2
        obj.drawLine(disp_x+l, 5*h/20, disp_x+l, 15*h/20); // Bottom of AND Bell
        obj.drawLine(disp_x+l, 5*h/20, disp_x+l+27, 5*h/20); // Top Bar 
        obj.drawLine(disp_x+l, 15*h/20, disp_x+l+27, 15*h/20); // Top Bar 
        obj.drawArc(disp_x+l, 5*h/20, l+35, l+35, -90, 180); // Bell TOp Curve
        obj.drawLine(disp_x+l+55, cy, disp_x+l+55+l, cy); // Bell Output
        
        ((AbstractComponent)component).Inputs.get(0).setPosition(disp_x, 2*h/5);
        ((AbstractComponent)component).Inputs.get(1).setPosition(disp_x, 3*h/5);
        ((AbstractComponent)component).Outputs.get(0).setPosition(disp_x+l+55+l, cy);
        
        obj.setFont(new Font("Arial", Font.PLAIN, 11));
        obj.drawString(component.getName(), cx-(component.getName().length()*4.5f), cy+3);
    }
}