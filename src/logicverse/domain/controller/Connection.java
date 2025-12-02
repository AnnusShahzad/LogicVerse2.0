/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package logicverse.domain.controller;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.UUID;
import logicverse.data.ConnectionDBDAO;
import logicverse.data.IDAO;

/**
 * Class Responsible for managing Component Connections
 * @author Admin
 */
public class Connection {
    /**
     * Connection Input Point
     */
    public Point Input;
    /**
     * Connection Output Point
     */
    public Point Output;
    /**
     * Unique Connection ID
     * Used for retrieving and saving data between DAO
     */
    public String ConnectionId;
    /**
     * Data Access Object
     * Used for Flexible management of Data Objects
     */
    protected static final IDAO dao = new ConnectionDBDAO();
    /**
     * Parametrized Constructor
     * Creates a Connection between Point a to Point b
     * @param a Connection Starting Point (Output Point)
     * @param b Connection Ending Point (Input Point)(Next Component)
     */
    public Connection(Point a, Point b) {
        ConnectionId = UUID.randomUUID().toString();
        Input = a;  
        Output = b;
        Output.add();
    }
    /**
     * Parameterized Constructor with Custom Connection ID
     * Creates a Connection between Point a to Point b
     * @param connectionId Custom Connection ID
     * @param a Connection Starting Point (Output Point)
     * @param b Connection Ending Point (Input Point)(Next Component)
     */
    public Connection(String connectionId, Point a, Point b) {
        ConnectionId = connectionId;
        Input = a;  
        Output = b;
        Output.add();
    }
    /**
     * Refreshes the Bit Got from Output Point to Input Point
     */
    public void refresh() {
        Output.set(Input);
    }
    
    public void reset() {
        ConnectionId = UUID.randomUUID().toString();
    }
    
    // DAO funictions
    
    /**
     * Delete a Connection
     * @return Boolean True If Deleted, False Otherwise
     */
    public boolean delete(){
        Hashtable<String,String> params = new Hashtable<>();
        params.put("ConnectionId", this.ConnectionId);
        return dao.delete(params);
    }
    /**
     * Save a Connection
     * @return Boolean True If Saved, False Otherwise
     */
    public boolean save(){
        Hashtable<String,String> data = new Hashtable<>();
        data.put("ConnectionId",this.ConnectionId);
        data.put("inputPointId",this.Input.getPid());
        data.put("outputPointId",this.Output.getPid());
        data.put("Cid", this.Input.getParent().getCid());
       
        return dao.save(data);
    }
    /**
     * Gets all Connections linked with a Component
     * @param parent parent Component
     * @return Array List of Connection for provided Component
     */
    public static ArrayList<Connection> loadAll(Component parent){
        ArrayList<Connection> res = new ArrayList<>();
        Hashtable<String,String> params = new Hashtable<>();
        params.put("Cid",parent.getCid());
        ArrayList<Hashtable<String,String>> data = dao.loadAll(params);
        AbstractComponent ac = (AbstractComponent)parent;
        for(Hashtable<String,String> d : data){
            Point inPoint =  ac.getOutputPoint(d.get("inputPointId"));
            Point outPoint =  ac.getInputPointFromACs(d.get("outputPointId"));
            if(inPoint == null || outPoint == null) continue;
            Connection c = new Connection(d.get("ConnectionId"), inPoint, outPoint);
            res.add(c);
        }
        return res;
    }
    
}
