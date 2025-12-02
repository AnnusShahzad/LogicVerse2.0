/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package logicverse.domain.controller;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.UUID;
import logicverse.data.IDAO;
import logicverse.data.PointDBDAO;
import logicverse.domain.model.Position;

/**
 * Points used by Electrical Components (Input/Output)
 * Responsible for Fetching and Sending Bits on Path
 * @author Admin
 */
public class Point {
    /**
     * Point Unique ID
     * Useful 
     */
    String Pid;
    /**
     * Point Name
     * Useful for Debugging
     */
    String name;
    /**
     * The Boolean bit it has
     */
    boolean bit;
    /**
     * Point Position on Container
     */
    Position position;
    /**
     * Number of Other Points Connected
     * 0, If the Point is free (Not Connected).
     */
    int degree;
    /**
     * DAO Responsible for Managing State of Point
     */
    private static final IDAO dao =  new PointDBDAO();
    /**
     * Component which has this Point
     */
    Component parent;
    /**
     * Parameterized Constructor with Custom PointID
     * @param PointId Point ID
     */
    public Point(String PointId) {
        Pid = PointId;
        load();
    }
    /**
     * 
     * @param Pid Unique Point ID
     * @param name Point Name
     * @param bit Point Bit Value
     * @param degree Count of Components Point is Connected with
     * @param parent Component having Point
     */
    public Point(String Pid, String name, boolean bit, int degree, Component parent, Position pos) {
        this.Pid = Pid;
        this.name = name;
        this.bit = bit;
        this.degree = degree;
        this.parent = parent;
        this.position = pos;
    }
    
    /**
     * Parameterized Constructor
     * @param name Point Name
     * @param parent Component having Point
     */
    public Point(String name, Component parent) {
        this.Pid = UUID.randomUUID().toString();
        this.name = name;
        this.parent = parent;
        this.position = new Position();
    }
    /**
     * Gets the Unique Point ID
     * @return Point ID
     */
    public String getPid() {
        return Pid;
    }
    
    public void reset() {
        this.Pid = UUID.randomUUID().toString();
    }
    /**
     * Gets the Boolean Value on the Point
     * @return Point Value
     */
    public boolean get() {
        return bit;
    }
    /**
     * Gets the Component having Point
     * @return Component Object
     */
    public Component getParent() {
        return parent;
    }
    /**
     * Sets the Point bit (Boolean)
     * @param state new bit
     */
    public void set(boolean state) {
        bit = state;
    }
    /**
     * Sets the Point bit from another Point
     * @param point Another Point to fetch bit from
     */
    public void set(Point point) {
        bit = point.get();
    }
    /**
     * Gets the Position of the Point
     * @return Point Position
     */
    public Position getPosition() {
        return this.position;
    }
    /**
     * Sets the Position of the Point
     * @param p Point Position
     */
    public void setPosition(Position p) {
        position.setX(p.getX());
        position.setY(p.getY());
    }
    /**
     * Sets the Position of the Point
     * @param x Point X-Axis Position
     * @param y Point Y-Axis Position
     */
    public void setPosition(int x, int y) {
        position.setPosition(x, y);
    }
    
    /**
     * Responsible for Adding the Point Degree
     */
    public void add() {
        degree++;
    }
    /**
     * Gets whether a Point is Connected or not
     * True, If connected. False otherwise
     * @return Point Connection State
     */
    public boolean isConnected() {
        return degree != 0;
    }
    /**
     * Default toString Method
     * Used for Accessibility
     * @return 
     */
    public String toString() {
//        return String.valueOf(bit);
        return parent.getName() + name;
    }
    
    // dao funictions
    /**
     * load a point attributes from db based on it Pid
     * @return Boolean True, If successfully Loaded, False Otherwise
     */
     public boolean load(){
        
        Hashtable<String,String> params = new Hashtable<>();
        params.put("Pid", this.Pid);
        Hashtable<String,String> data = dao.load(params);
        if (data == null || data.isEmpty()){
            return false;
        }
        this.name = data.get("Name");
        this.bit = (Integer.parseInt(data.get("bit")) == 1)? true:false;
        this.degree = Integer.parseInt(data.get("degree"));
        this.position = new Position(Integer.valueOf(data.get("PosX")),Integer.valueOf(data.get("PosY")));
        return true;
    }
    
    
    /**
     * Delete a Point
     * @return Boolean True, If deleted successfully. False Otherwise
     */
    public boolean delete(){
        Hashtable<String,String> params = new Hashtable<>();
        params.put("Pid", this.Pid);
        return dao.delete(params);
    }
    /**
     * Save a Point
     * @param type type of point of component (Input,Output)
     * @return Boolean True, If saved successfully. False Otherwise.
     */
    public boolean save(String type){
        Hashtable<String,String> data = new Hashtable<>();
        data.put("Pid",this.Pid);
        data.put("Name",this.name);
        data.put("degree",String.valueOf(this.degree));
        data.put("Cid",this.parent.getCid());
        data.put("type",type);
        data.put("bit", (this.bit)?"1":"0");
        data.put("PosX", String.valueOf(this.position.getX()));
        data.put("PosY", String.valueOf(this.position.getY()));
        return dao.save(data);
    }
    /**
     * Get all Point associated with a Component
     * @param parent Parent component
     * @param Type of points (Input or Output)
     * @return List of Associated Points
     */
    public static ArrayList<Point> loadAll(Component parent, String Type){
        ArrayList<Point> res = new ArrayList<>();
        Hashtable<String,String> params = new Hashtable<>();
        params.put("Cid", parent.getCid());
        params.put("type", Type);
        ArrayList<Hashtable<String,String>> data = dao.loadAll(params);
        for(Hashtable<String,String> d : data){
            Point p = new Point(
                d.get("Pid"),
                d.get("Name"),
                (Integer.valueOf(d.get("bit"))== 1)?true:false,
                Integer.valueOf(d.get("degree")),
                parent,
                new Position(Integer.valueOf(d.get("PosX")),Integer.valueOf(d.get("PosY")))
            );
            res.add(p);
        }
        return res;
    }
}
