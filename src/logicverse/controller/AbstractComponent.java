/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package logicverse.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;
import logicverse.Gates.controller.AND;
import logicverse.Gates.controller.NOT;
import logicverse.Gates.controller.OR;
import logicverse.Gates.controller.Switch;
import logicverse.data.ComponentDBDAO;
import logicverse.data.IDAO;
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
    private static final IDAO dao = new ComponentDBDAO();
    /**
     * Name of the Component
     */
    protected String Cid;
    protected String name;
    protected String shortName;
    protected String internalName;
    public AbstractComponent() {
        Cid = UUID.randomUUID().toString();
        Inputs = new ArrayList<>();
        Outputs = new ArrayList<>();
        Location = new Position();
        Connections = new ArrayList<>();
        Components = new ArrayList<>();
    }
    public AbstractComponent(String Cid){
        Inputs = new ArrayList<>();
        Outputs = new ArrayList<>();
        Location = new Position();
        Connections = new ArrayList<>();
        Components = new ArrayList<>();
    }

    public AbstractComponent( Position Location, String Cid, String name, String internalName) {
        this.Inputs = Point.loadAll(this, "Input");
        this.Outputs = Point.loadAll(this, "Output");
        this.Location = Location;
        this.Cid = Cid;
        this.name = name;
        this.internalName = internalName;
    }
    
    public void incrementCount() {
        counters.put(this.getClass(), counters.getOrDefault(this.getClass(), 0) + 1);
    }

    public String getCid() {
        return Cid;
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
    /**
     * search for a Point in the output Points of component
     * @param pointId
     * @return Point
     */
    public Point getOutputPoint(String pointId){
        for(Point o: this.Outputs){
            if(o.getPid().equals(pointId)){
                return o;
            }
        }
        return null;
    }
    /**
     * search for a point in the input Points of Component
     * @param pointId
     * @return Point
     */
    public Point getInputPoint(String pointId){
        for(Point p: this.Inputs){
            if(p.getPid().equals(pointId)){
                return p;
            }
        }
        return null;
    }
     
    /**
     * search for a point in the input Points of all associated components
     * @param pointId
     * @return Point
     */
    public Point getInputPointFromACs(String pointId){
        for(Component c :  this.Components){
            AbstractComponent ac = (AbstractComponent)c;
            Point inputPoint = ac.getInputPoint(pointId);
            if(inputPoint != null)
                return inputPoint;
        }
        return null;
    }
    
    
    // dao funcitons
    /**
     * Delete a Component
     * @return Boolean
     */
    public boolean delete(){
        Hashtable<String,String> params = new Hashtable<>();
        params.put("Cid", this.Cid);
        return dao.delete(params);
    }
    /**
     * Save a Componnet
     * @param type type of point of component (Input,Output)
     * @return Boolean
     */
    public boolean save(){
        Hashtable<String,String> data = new Hashtable<>();
        data.put("Cid",this.Cid);
        data.put("Name",this.name);
        data.put("internalName",this.internalName);
        data.put("PosX",String.valueOf(this.Location.getX()));
        data.put("PosY",String.valueOf(this.Location.getY()));
        
        return dao.save(data);
    }
    /**
     * Get all Componnets inside a Circuit
     * @param circuitId CircuitId
     * @return ArrayList of p
     */
    public static ArrayList<Component> loadAll(String circuitId){
        ArrayList<Component> res = new ArrayList<>();
        Hashtable<String,String> params = new Hashtable<>();
        params.put("Cid", circuitId);
        ArrayList<Hashtable<String,String>> data = dao.loadAll(params);
        for(Hashtable<String,String> d : data){
            Component c;
            Position loc = new Position(Integer.valueOf(d.get("PosX")),Integer.valueOf(d.get("PosY")));
            switch(d.get("internalName")){
                case "AND":
                    // Position Location, String Cid, String name, String internalName
                    c =  new AND(loc,d.get("Cid"),d.get("Name"),d.get("internalName"));
                    break;
                case "OR":
                    c =  new OR(loc,d.get("Cid"),d.get("Name"),d.get("internalName"));
                    break;
                case "NOT":
                    c =  new NOT(loc,d.get("Cid"),d.get("Name"),d.get("internalName"));
                    break;
                case "Binary Switch":
                    c =  new Switch(loc,d.get("Cid"),d.get("Name"),d.get("internalName"),d.get("clicked").equals(1));
                    break;
                default:
                    c =  new AND(loc,d.get("Cid"),d.get("Name"),d.get("internalName"));
                    
            }
        }
        return res;
    }
     
}
