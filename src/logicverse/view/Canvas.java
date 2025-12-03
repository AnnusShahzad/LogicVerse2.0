/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package logicverse.view;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.List;
import javax.swing.JPanel;
import logicverse.Gates.controller.Circuit;
import logicverse.controller.AbstractComponent;
import logicverse.controller.Connection;
import logicverse.controller.Point;
import logicverse.model.Position;

/**
 *
 * @author Admin
 */
public class Canvas extends JPanel {
    Circuit circuit;
//    List<Connection> connections; 
    public Canvas(Circuit c) {
        circuit = c;
    }
    
    @Override
    public void paintComponent(Graphics obj) {
        super.paintComponent(obj);
        obj.setColor(Color.BLACK);
        draw((Graphics2D)obj);
    }
    
    public void draw(Graphics2D obj) {
        List<Connection> connections = circuit.getComponents().stream().map(it -> ((AbstractComponent)it).Connections).flatMap(List::stream).toList();

        // Looping through Every Connection
        for(Connection c: connections) {
            Point input = c.Input;
            Point output = c.Output;
            
            // Drawing Lines now
            java.awt.Point InputContPos = input.getParent().getContainer().getLocation();
            java.awt.Point OutputContPos = output.getParent().getContainer().getLocation();
            Position InputPos = input.getPosition();
            Position OutputPos = output.getPosition();
            
            obj.drawLine(InputContPos.x+InputPos.getX(), InputContPos.y+InputPos.getY(), OutputContPos.x+OutputPos.getX(), OutputContPos.y+OutputPos.getY());
        }
    }
}
