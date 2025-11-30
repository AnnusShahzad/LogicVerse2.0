/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package logicverse.Gates.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import logicverse.Gates.controller.Probe;
import logicverse.Gates.model.JEComponent;

/**
 *
 * @author Admin
 */
public class JEProbe extends JEComponent {
    final int iw = 50; // Internal Width
    final int s = 5;  // Space between Boxes
    final int l = 60;  // Length of Input
    
    public JEProbe() {
        super(new Probe());
    }
    
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.black);
        draw((Graphics2D)g);
    }
    
    public Dimension getPreferredSize() {
        return new Dimension(iw+s+1, iw+s+l);
    }
    
    public void draw(Graphics2D obj) {
        int w = getWidth();
        int h = getHeight();
        int cx = w/2;
        int cy = h/2;
        
        // Inner sq
        obj.drawRect(s, s, iw-s, iw-s);
        // Outer sq
        obj.drawRect(0, 0, iw+s, iw+s);
        // Input Point
        obj.drawLine((iw+s)/2, iw+s, (iw+s)/2, iw+s+l);
        
        // Bit
        if(component.toString() == "true")
            obj.drawString("1",(iw+s)/2,(iw+s)/2);
        else
            obj.drawString("0",(iw+s)/2-2.6f,(iw+s)/2+3.5f);
    }
}
