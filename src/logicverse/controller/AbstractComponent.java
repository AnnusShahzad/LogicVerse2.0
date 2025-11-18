/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package logicverse.controller;

import java.util.ArrayList;

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
    public AbstractComponent() {
        Inputs = new ArrayList<>();
        Outputs = new ArrayList<>();
        Connections = new ArrayList<>();
        Components = new ArrayList<>();
    }
    
    public void execute() {
        if(!isConnected()) {
            System.out.println("Not Connected");
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
    
    public boolean isConnected() {
        for(Point i: Inputs) {
            if (!i.isConnected())
                return false;
        }
        return true;
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
}
