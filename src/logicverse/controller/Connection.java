/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package logicverse.controller;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.UUID;
import logicverse.data.ConnectionDBDAO;
import logicverse.data.IDAO;

/**
 *
 * @author Admin
 */
public class Connection {
    public Point Input;
    public Point Output;
    public String ConnectionId;
    protected static final IDAO dao = new ConnectionDBDAO();
    public Connection(Point a, Point b) {
        ConnectionId = UUID.randomUUID().toString();
        Input = a;  
        Output = b;
        Output.add();
    }
    public Connection(String connectionId, Point a, Point b) {
        ConnectionId = connectionId;
        Input = a;  
        Output = b;
    }
    public void refresh() {
        Output.set(Input);
    }
    
    // dao funictions
    
    /**
     * Delete a Connection
     * @return Boolean
     */
    public boolean delete(){
        Hashtable<String,String> params = new Hashtable<>();
        params.put("ConnectionId", this.ConnectionId);
        return dao.delete(params);
    }
    /**
     * Save a Connection
     * @return Boolean
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
     * get all Connections linked with a Component
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
