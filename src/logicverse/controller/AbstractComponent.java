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
import java.util.UUID;
import java.util.stream.Collectors;
import logicverse.Gates.controller.AND;
import logicverse.Gates.model.JEComponent;
import logicverse.data.ComponentDBDAO;
import logicverse.data.IDAO;
import logicverse.model.Position;

/**
 * Abstract Implementation of Component Interface
 * Overridden by Other Classes for further processing
 * @author Admin
 */
public abstract class AbstractComponent implements Component {
    /**
     * Component Input Points
     */
    public ArrayList<Point> Inputs;
    /**
     * Component Output Points
     */
    public ArrayList<Point> Outputs;
    /**
     * Connected Components
     */
    public ArrayList<Component> Components;
    /**
     * Component Connections with Other Components
     */
    public ArrayList<Connection> Connections;
    /**
     * Component Location on Container
     */
    protected Position Location;
    /**
     * Component Counter
     */
    protected static final Map<Class<?>, Integer> counters = new HashMap<>();
    /**
     * Data Access Object
     * Used for Managing related attributes
     */
    private static final IDAO dao = new ComponentDBDAO();
    /**
     * Component Unique ID
     * Used for Storing and Retrieving from DAO
     */
    protected String Cid;
    /**
     * Component Name
     * Used for Showing on the Screen
     */
    protected String name;
    /**
     * Component Short Name
     * Its actually a prefix and matches from internal Name
     */
    protected String shortName;
    /**
     * Component Internal Name
     * Mostly gonna be used by Developers
     */
    protected String internalName;
    /**
     * Container that houses the Component
     */
    protected JEComponent container;
    /**
     * Default Constructor
     * Initializes the Attributes and Methods to Default
     */
    public AbstractComponent() {
        Cid = UUID.randomUUID().toString();
        Inputs = new ArrayList<>();
        Outputs = new ArrayList<>();
        Location = new Position();
        Connections = new ArrayList<>();
        Components = new ArrayList<>();
        container = null;
    }
    /**
     * Parameterized Constructor with User Cid (Component ID)
     * @param Cid Component ID
     */
    public AbstractComponent(String Cid){
        this.Cid = Cid;
        Inputs = new ArrayList<>();
        Outputs = new ArrayList<>();
        Location = new Position();
        Connections = new ArrayList<>();
        Components = new ArrayList<>();
        container = null;
    }
    /**
     * Parameterized Constructor with Container provided
     * @param cont Container housing the Component
     */
    public AbstractComponent(JEComponent cont) {
        Cid = UUID.randomUUID().toString();
        Inputs = new ArrayList<>();
        Outputs = new ArrayList<>();
        Location = new Position();
        Connections = new ArrayList<>();
        Components = new ArrayList<>();
        container = cont;
    }
    /**
     * Parameterized Constructor with ComponentID, Container provided
     * @param Cid Component ID
     * @param cont Container housing the Component 
     */
    public AbstractComponent(String Cid, JEComponent cont){
        this.Cid = Cid;
        Inputs = new ArrayList<>();
        Outputs = new ArrayList<>();
        Location = new Position();
        Connections = new ArrayList<>();
        Components = new ArrayList<>();
        container = cont;
    }
    /**
     * Increments the Count of Component Used
     * Used for Generating Unique Names
     */
    public void incrementCount() {
        counters.put(this.getClass(), counters.getOrDefault(this.getClass(), 0) + 1);
    }
    /**
     * Gets the Component Unique ID
     * @return Component Unique ID
     */
    public String getCid() {
        return Cid;
    }
    /**
     * Gets Bit of the First Output as String
     * @return Boolean Value of First Output
     */
    public String toString() {
        return String.valueOf(Outputs.get(0).get());
//        return name;
    }
    /**
     * Gets the Number of Created Component
     * @param cls Component to count
     * @return Number of Component created
     */
    protected static int getCount(Class<?> cls) {
        return counters.getOrDefault(cls, 0);
    }
    /**
     * Changes the Name of the Component
     * @param name Component Name
     */
    @Override
    public void setName(String name) {
        this.name = name;
    }
    /**
     * Gets the Name of the Component
     * @return Component Name
     */
    @Override
    public String getName() {
        return name;
    }
    /**
     * Gets the Internal Name of the Component
     * @return Component Internal Name
     */
    @Override
    public String getInternalName() {
        return internalName;
    }
    /**
     * Gets the Input Points that are not connected
     * Utilizes Java 7+ Stream for fast retrieval and filtering
     * @return Input Points not connected
     */
    public List<Point> getFreeInputs() {
        List<Point> points = Inputs.stream().filter(it -> !it.isConnected()).collect(Collectors.toList());
        System.out.println("Free Inputs: " + points.size());
        return points;
    }
    /**
     * Checks If an Component has any Output Point
     * @return True, If has an Output Point. False, Otherwise
    */
    public boolean hasOutputs() {
        return Outputs.size() > 0;
    }
    /**
     * Checks If a Component has any Free Input Point
     * Relies on `getFreeInputs` method
     * @return True, If there's a Free Input Point. False otherwise
     */
    public boolean hasFreeInputs() {
        return getFreeInputs().size() > 0;
    }
    /**
     * Gets the List of Output Points of an Component
     * @return List of Output Points
     */
    public List<Point> getOutputs() {
        return this.Outputs;
    }
    /**
     * Gets the Container (JEComponent) that houses the Component
     * @return JEComponent: Container
     */
    public JEComponent getContainer() {
        return this.container;
    }
    /**
     * Sets the Container (JEComponent) that houses the Component
     * @param c JEComponent: Container
     */
    public void setContainer(JEComponent c) {
        container = c;
    }
    /**
     * Sets the Component Location on the Screen
     * @param x X-Axis Position
     * @param y Y-Axis Position
     */
    public void setLocation(int x, int y) {
        Location.setPosition(x,y);
    }
    
    /**
     * Responsible for Forwarding the Bits to other Connected Components
     * Uses Composite Design Pattern for Executing related Components execute statement as well
     * Repaints the Container after executing its main operation
     */
    public void execute() {
        Integer missPoint = isConnected();
        if(missPoint != -1) {
            System.out.println(internalName + ", Point " + missPoint + " Not Connected");
            return;
        }
        System.out.println("Called Execute of " + this.name);
        System.out.println("Connected Connections: " + this.Connections.size());
        System.out.println("Connected Components: " + this.Components.size() );
        onExecute();
        if(container != null)
            container.repaint();
        for(Connection ct: Connections) {
            ct.refresh();
        }
        for(Component cp: Components) {
            cp.execute();
        }
    }
    /**
     * Checks if All the Input Points are connected
     * @return True, If Connected. False otherwise
     */
    public int isConnected() {
        for(int i = 0; i < Inputs.size(); i++) {
            if (!Inputs.get(i).isConnected())
                return i;
        }
        return -1;
    }
    
    /**
     * Gets the Clicked State of a Component
     * Overridden by ClickableComponent Class for Switches
     * By Default, Returns False for other Components
     * @return Clicked State
     */
    public boolean getState() { return false ; }
    /**
     * Performs the click Event of an Component
     * Overridden by ClickableComponent Class for Switches
     * By Default, Does nothing for other Components
     */
    public void click() { return; }
    
    /**
     * Changes the Bit of an Output Point for an Component
     * SHOULD NOT BE USED IN PRODUCTION!
     * ONLY USE IT FOR TESTING PURPOSE!
     * @param o Output Point Index
     * @param bit New Bit
     */
    public void setOutputBit(int o, boolean bit) {
        if (o >= Outputs.size()) {
            throw new IndexOutOfBoundsException("There's no " + i + "th Output in the Array");
        }
        Outputs.get(i).set(bit);
    }
    
    /**
     * Changes the Bit of an Input Point for an Component
     * SHOULD NOT BE USED IN PRODUCTION!
     * ONLY USE IT FOR TESTING PURPOSE!
     * @param i Input Point Index
     * @param bit New Bit
     */
    public void setInputBit(int i, boolean bit) {
        if (i >= Inputs.size()) {
            throw new IndexOutOfBoundsException("There's no " + i + "th Input in the Array");
        }
        Inputs.get(i).set(bit);
    }
    /**
     * Connects 2 Components by connecting Points
     * @param i Input Point of the Next Component
     */
    public void addConnection(Point i) {
        Point in = Outputs.get(0);
        Connections.add(new Connection(in, i));
        Components.add(i.getParent());
    }
    
    /**
     * Connects 2 Components by connecting Points
     * @param i The Output Point of Current Component to Send From
     * @param o The Input Point of Next Component to Send to
     */
    public void addConnection(Point i, Point o) {
        Connections.add(new Connection(i, o));
        Components.add(o.getParent());
    }
    
    /**
     * Search for a Point in the output Points of component
     * @param pointId The PointID to search
     * @return Point Matching Point, Otherwise Null
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
     * Search for a Point in the input Points of component
     * @param pointId The PointID to search
     * @return Point Matching Point, Otherwise null
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
     * Searches for a point in the input Points of all associated components
     * @param pointId PointID to search
     * @return Point Matching Point, Otherwise Null
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
     * @return Boolean True If deleted, False otherwise
     */
    public boolean delete(){
        Hashtable<String,String> params = new Hashtable<>();
        params.put("Cid", this.Cid);
        return dao.delete(params);
    }
    /**
     * Saves a Componnet
     * @param type type of point of component (Input,Output)
     * @return Boolean True If Saved, False Otherwise
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
     * @param circuitId CircuitId of the Components
     * @return An ArrayList of Components
     */
    public static ArrayList<Component> loadAll(String circuitId){
        ArrayList<Component> res = new ArrayList<>();
        Hashtable<String,String> params = new Hashtable<>();
        params.put("Cid", circuitId);
        ArrayList<Hashtable<String,String>> data = dao.loadAll(params);
        for(Hashtable<String,String> d : data){
//            res.add(new AND());
        }
        return res;
    }
     
}
