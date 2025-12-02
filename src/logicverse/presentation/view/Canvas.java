/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package logicverse.presentation.view;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Logger;
import javax.swing.JPanel;
import logicverse.domain.gates.controller.AND;
import logicverse.domain.gates.controller.Circuit;
import logicverse.domain.gates.controller.NOT;
import logicverse.domain.gates.controller.OR;
import logicverse.domain.gates.controller.Probe;
import logicverse.domain.gates.controller.Switch;
import logicverse.presentation.gates.view.JEAND;
import logicverse.presentation.gates.view.JENOT;
import logicverse.presentation.gates.view.JEOR;
import logicverse.presentation.gates.view.JEProbe;
import logicverse.presentation.gates.view.JESwitch;
import logicverse.domain.controller.AbstractComponent;
import logicverse.domain.controller.Component;
import logicverse.domain.controller.Connection;
import logicverse.domain.controller.Point;
import logicverse.domain.gates.model.JEComponent;
import logicverse.domain.model.Position;

/**
 *
 * @author Admin
 */
public class Canvas extends JPanel {
    private static final Logger logger = Logger.getLogger(Canvas.class.getName());
    Circuit circuit;
//    List<Connection> connections; 
    public Canvas(Circuit c) {
        circuit = c;
        setLayout(null);
    }
    
    public void recreate(boolean hadComponents, List<Component> newComponents) {
        removeAll();
        List<Component> comp = circuit.getComponents();
        for(Component c: comp) {
            if(c instanceof AND) {
                add(new JEAND(c));
                logger.info("Creating new JEAND");
            }
            else if (c instanceof OR) {
                add(new JEOR(c));
                logger.info("Creating new JEOR");
            }
            else if(c instanceof NOT) {
                add(new JENOT(c));
                logger.info("Creating new JENOT");
            }
            else if(c instanceof Switch) {
                add(new JESwitch(c));
                logger.info("Creating new JESwitch");
            }
            else if(c instanceof Probe) {
                add(new JEProbe(c));
                logger.info("Creating new JEProbe");
            }
            else {
                logger.info("Error, Invalid Component Found!!!");
            }
        }
        if(true) {
            for(Component c: newComponents) {
                c.reset();
                c.getContainer().repaint();
            }
            circuit.reset();
        }
//        List<Component> newComponentList = Arrays.stream(getComponents()).map(it -> ((JEComponent)it).component).toList(); 
//        System.out.println("Component Count = " + newComponentList);
//        circuit.setComponents(newComponentList);
        revalidate();
        repaint();
    }
    
    public void changeCircuit(Circuit c) {
        circuit = c;
        //recreate();
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
