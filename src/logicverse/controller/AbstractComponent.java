/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package logicverse.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import logicverse.model.Position;

/**
 *
 * @author Admin
 */
public abstract class AbstractComponent implements Component {
    public ArrayList<Point> Inputs;    
    public ArrayList<Point> Outputs;
    protected Position Location;
    protected ArrayList<Component> Components;
    protected ArrayList<Connection> Connections;
    protected static final Map<Class<?>, Integer> counters = new HashMap<>();
    /**
     * Name of the Component
     */
    protected String name;
    protected String shortName;
    protected String internalName;
    public AbstractComponent() {
        Inputs = new ArrayList<>();
        Outputs = new ArrayList<>();
        Location = new Position();
        Connections = new ArrayList<>();
        Components = new ArrayList<>();
    }
    public void incrementCount() {
        counters.put(this.getClass(), counters.getOrDefault(this.getClass(), 0) + 1);
    }
    protected static int getCount(Class<?> cls) {
        return counters.getOrDefault(cls, 0);
    }
    @Override
    public void setName(String name) {
        this.name = name;
    }
    @Override
    public String getName() {
        return name;
    }
    @Override
    public String getInternalName() {
        return internalName;
    }
    public List<Point> getFreeInputs() {
        List<Point> points = Inputs.stream().filter(it -> !it.isConnected()).collect(Collectors.toList());
        System.out.println("Free Inputs: " + points.size());
        return points;
    }
    /**
     * Returns true, If there is any Output Point of a Component
     * @return True, If has an Output Point. False, Otherwise
    */
    public boolean hasOutputs() {
        return Outputs.size() > 0;
    }
    
    public boolean hasFreeInputs() {
        return getFreeInputs().size() > 0;
    }
    public List<Point> getOutputs() {
        return this.Outputs;
    }
    
    /**
     * Sets the Component Location on the Screen
     * @param x X-Axis Position
     * @param y Y-Axis Position
     */
    public void setLocation(int x, int y) {
        Location.setPosition(x,y);
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
    
    public boolean getState() { return false ; }
    public void click() { return; }
    
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
