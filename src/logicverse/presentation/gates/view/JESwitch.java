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
import logicverse.domain.gates.controller.Switch;
import logicverse.domain.gates.model.JEClickableComponent;
import logicverse.domain.controller.AbstractComponent;
import logicverse.domain.controller.Component;

/**
 *
 * @author Admin
 */
public class JESwitch extends JEClickableComponent {
    final int i = 10;
    final int d = 10;
    final int output = 20;
    
    public JESwitch() {
        super(new Switch());
    }
    public JESwitch(Component c) {
        super(c);
    }
    
    public Dimension getPreferredSize() {
        return new Dimension(60, 50);
    }
    
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.red);
        draw((Graphics2D)g);
    }
    
    public void draw(Graphics2D obj) {
        int x = getWidth()/2;
        int y = getHeight()/2;
        
        // Drawing Output One
        obj.drawLine(x, y, x+output, y);
        // 1
        obj.drawLine(i, y/2, i+i, y/2);
        // 0
        obj.drawLine(i, 3*y/2, i+i, 3*y/2);
        if(component.getState()) { // Clicked 
            obj.drawLine(i+i,3*y/2,x,y);
        } else {
            obj.drawLine(i+i,y/2,x,y);
        }
        
        // Writing 1 and 0
        obj.drawString("0", 1, 3*y/4-1.5f);
        obj.drawString("1", 1, 7*y/4-1.5f);
        
        ((AbstractComponent)component).Outputs.get(0).setPosition(x+output, y);
        
        obj.setFont(new Font("Arial", Font.PLAIN, 9));
        obj.drawString(component.getName(), x, y-2);
    }
}
