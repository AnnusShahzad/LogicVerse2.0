/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package logicverse.domain.gates.controller;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.UUID;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import logicverse.domain.controller.AbstractComponent;
import logicverse.domain.controller.Component;
import logicverse.domain.controller.Connection;
import logicverse.data.CircuitDBDAO;
import logicverse.data.IDAO;
import logicverse.domain.controller.Point;


/**
 * Circuit Class for managing Circuits
 * @author Admin
 */
public class Circuit {
    private static final Logger logger = Logger.getLogger(Circuit.class.getName());
    /**
     * List of Circuit Components
     */
    private ArrayList<Component> Components;
    /**
     * Circuit ID
     * USAGE: Used in Storing in Database and for O(1) Query
     */
    private String ID;
    
    /**
     * Circuit Name
     * USAGE: Helps the User know the name of the Circuit
     */
    private String Name;
    private static final IDAO dao = new CircuitDBDAO();

    /**
     * Default Constructor
     * Initializes the Circuit with Default Values and Initializes Arrays
     */
    public Circuit() {
        ID = UUID.randomUUID().toString();
        Name = "Untitled";
        Components = new ArrayList<>();
    }

    public Circuit(String ID, String Name) {
        this.ID = ID;
        this.Name = Name;
        Components = new ArrayList<>();
    }
    
    public Circuit(String ID){
        this.ID = ID;
        load();
    }
    
    public String toString() {
        return Name;
    }
    
    
    /**
     * Gets the List of Circuit Components
     * @return the Components
     */
    public List<Component> getComponents() {
        return Components;
    }
    public void setComponents(List<Component> list) {
        Components = new ArrayList<>(list);
    }
    
    public void reset() {
        ID = UUID.randomUUID().toString();
    }
    
    /**
     * Adds a Component to the List of Global Components
     * @param c 
     */
    public void addComponent(Component c) {
        Components.add(c);
    }
    
    /**
     * Gets the List of Components that have Output Points
     * @return Components with Output Points
     */
    public List<Component> getComponentWithOutputs() {
        return Components.stream().filter(it -> ((AbstractComponent)it).hasOutputs()).collect(Collectors.toList());
    }
    
    /**
     * Gets the List of Components that have Inputs Points that are free to use
     * @return Components with free Input Points
     */
    public List<Component> getComponentWithFreeInputs() {
        return Components.stream().filter(it -> ((AbstractComponent)it).hasFreeInputs()).collect(Collectors.toList());
    }

    /**
     * @return the Circuit ID
     */
    public String getID() {
        return ID;
    }

    /**
     * @param ID the Circuit ID to set
     */
    public void setID(String ID) {
        this.ID = ID;
    }

    /**
     * @return the Circuit Name
     */
    public String getName() {
        return Name;
    }

    /**
     * @param Name the Circuit Name to set
     */
    public void setName(String Name) {
        this.Name = Name;
    }
        // dao funcitons
    /**
     * load a Circuit Compoents from db based on its ID
     * @return Boolean
     */
     public boolean load(){
         logger.info("Inside load circuit");
        Hashtable<String,String> params = new Hashtable<>();
        params.put("CIRid", ID);
        Hashtable<String,String> data = dao.load(params);
        if (data == null || data.isEmpty()){
            return false;
        }
        this.Name = data.get("Name");
        Components = AbstractComponent.loadAll(ID);
        // load component associations
        AbstractComponent.loadAllCmpntsAssociation(Components);
        for(Component cmp: Components){
            logger.info("=================");
            logger.info(cmp.toString());
            ((AbstractComponent)cmp).Connections = Connection.loadAll(cmp);
            logger.info("=================");
        }
        logger.info("after loading associated componenets");
        return true;
    }
    /**
     * Delete a Circuit
     * @return Boolean
     */
    public boolean delete(){
        Hashtable<String,String> params = new Hashtable<>();
        params.put("CIRid", this.ID);
        return dao.delete(params);
    }
    /**
     * Save a Circuit
     * @return Boolean
     */
    public boolean save(){
        Hashtable<String,String> data = new Hashtable<>();
        data.put("CIRid",this.ID);
        data.put("Name",this.Name);
        return (dao.save(data) && saveComponents());
    }
    /**
     * save all components of circuit in database
     * @return Boolean (true if all saved)
     */
    public boolean saveComponents(){
        for(Component c : Components)
            if(!c.save(ID)) return false;
        return true;
    }
    /**
     * Get all Circuits
     * @return ArrayList of Circuits
     */
    public static ArrayList<Circuit> loadAll(){
        ArrayList<Circuit> res = new ArrayList<>();
        Hashtable<String,String> params = new Hashtable<>();
        ArrayList<Hashtable<String,String>> data = dao.loadAll(params);
        for(Hashtable<String,String> d : data){
            res.add(new Circuit(d.get("CIRid"),d.get("Name")));
        }
        return res;
    }
}
