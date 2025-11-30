/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package logicverse.controller;

import java.util.ArrayList;
import jdk.jshell.spi.ExecutionControl.NotImplementedException;

/**
 *
 * @author Admin
 */
public abstract class AbstractComponent implements Component {
    public ArrayList<Point> Inputs;    
    public ArrayList<Point> Outputs;
    protected ArrayList<Component> Components;
    protected ArrayList<Connection> Connections;
    /**
     * Name of the Component
     */
    String name;
    protected String internalName;
    public AbstractComponent() {
        Inputs = new ArrayList<>();
        Outputs = new ArrayList<>();
        Connections = new ArrayList<>();
        Components = new ArrayList<>();
    }
    
    public void execute() {
        Integer missPoint = isConnected();
        if(missPoint != -1) {
            System.out.println(internalName + ", Point " + missPoint + " Not Connected");
            return;
        }
        onExecute();
        for(Connection ct: Connections) {
            ct.refresh();
        }
        for(Component cp: Components) {
            cp.execute();
        }
    }
    
    public int isConnected() {
        for(int i = 0; i < Inputs.size(); i++) {
            if (!Inputs.get(i).isConnected())
                return i;
        }
        return -1;
    }
    
    // Just for Testing Purpose, Remove it in Production
    public void setOutputBit(int i, boolean bit) {
        if (i >= Outputs.size()) {
            throw new IndexOutOfBoundsException("There's no " + i + "th Output in the Array");
        }
        Outputs.get(i).set(bit);
    }
    
    public void setInputBit(int i, boolean bit) {
        if (i >= Inputs.size()) {
            throw new IndexOutOfBoundsException("There's no " + i + "th Input in the Array");
        }
        Inputs.get(i).set(bit);
    }
    
    public void addConnection(Point i) {
        Point in = Outputs.get(0);
        Connections.add(new Connection(in, i));
        Components.add(i.getParent());
    }
    
    public void addConnection(Point i, Point o) {
        Connections.add(new Connection(i, o));
        Components.add(o.getParent());
    }
}
