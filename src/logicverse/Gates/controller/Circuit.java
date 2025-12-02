/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package logicverse.Gates.controller;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import logicverse.controller.AbstractComponent;
import logicverse.controller.Component;
import logicverse.controller.Point;
import logicverse.data.CircuitDBDAO;
import logicverse.data.IDAO;


/**
 * Circuit Class for managing Circuits
 * @author Admin
 */
public class Circuit {
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
        Components = new ArrayList<>();
    }

    public Circuit(String ID, String Name) {
        this.ID = ID;
        this.Name = Name;
        Components = new ArrayList<>();
    }
    
    
    
    /**
     * Gets the List of Circuit Components
     * @return the Components
     */
    public List<Component> getComponents() {
        return Components;
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
        
        
        return dao.save(data);
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
