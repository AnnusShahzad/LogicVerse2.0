/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package logicverse.Gates.view;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import logicverse.Gates.controller.NOT;
import logicverse.Gates.model.JEComponent;

/**
 *
 * @author Admin
 */
public class JENOT extends JEComponent {
    final int d = 25;
    final int l = 50;
    final int r = 5;
    
    public JENOT() {
        super(new NOT());
    }
    
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.red);
        draw((Graphics2D)g);
    }
    
    public void draw(Graphics2D obj) {
        int x = getWidth()/2;
        int y = getHeight()/2;
        System.out.println(x + " " + y);
        // Left Bar
        obj.drawLine(x-d, y-l/2, x-d, y+l/2);
        // Top Bar
        obj.drawLine(x-d, y-l/2, x+d, y);
        // Bottom Bar
        obj.drawLine(x-d, y+l/2, x+d, y);
        // Output Bar Circle haan aik minute
        obj.drawOval(x+d, y-r/2, r, r);
        // Output Bar
        obj.drawLine(x+d+r, y, x+d+2*r+l/2-l/5, y);
        // Input Bar
        obj.drawLine(x-d-l/2, y, x-d, y);
    }
}
