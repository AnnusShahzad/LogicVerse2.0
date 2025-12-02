/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package logicverse.presentation.gates.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.logging.Logger;
import logicverse.domain.gates.controller.NOT;
import logicverse.domain.gates.model.JEDraggableComponent;
import logicverse.domain.controller.AbstractComponent;
import logicverse.domain.controller.Component;

/**
 *
 * @author Admin
 */
public class JENOT extends JEDraggableComponent {
    private static final Logger logger = Logger.getLogger(JENOT.class.getName());
    final int d = 25;
    final int l = 50;
    final int r = 10;
    
    public JENOT() {
        super(new NOT());
    }
    public JENOT(Component c) {
        super(c);
    }
    
    @Override
    public Dimension getPreferredSize() {
        return new Dimension(111, 111);
    }
    
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.black);
        draw((Graphics2D)g);
    }
    
    public void draw(Graphics2D obj) {
        int x = getWidth()/2;
        int y = getHeight()/2;
        logger.info(x + " " + y);
        // Left Bar
        obj.drawLine(x-d, y-l/2, x-d, y+l/2);
        // Top Bar
        obj.drawLine(x-d, y-l/2, x+d, y);
        // Bottom Bar
        obj.drawLine(x-d, y+l/2, x+d, y);
        // Output Bar Circle haan aik minute
        obj.drawOval(x+d, y-r/2, r, r);
        // Output Bar
        obj.drawLine(x+d+r, y, x+d+2*r+l/12, y);
        // Input Bar
        obj.drawLine(x-d-l/2, y, x-d, y);
        
        ((AbstractComponent)component).Inputs.get(0).setPosition(x-d-l/2, y);
        ((AbstractComponent)component).Outputs.get(0).setPosition(x+d+2*r+l/12, y);
        
        obj.setFont(new Font("Arial", Font.PLAIN, 11));
        obj.drawString(component.getName(), x-(component.getName().length()*4.5f), y+3);
    }
}
