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
 *
 * @author Admin
 */
public class Circuit {
    private ArrayList<Component> Components;
    private String ID;
    private String Name;
    private static final IDAO dao = new CircuitDBDAO();

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
    
    public List<Component> getComponentWithOutputs() {
        return Components.stream().filter(it -> ((AbstractComponent)it).hasOutputs()).collect(Collectors.toList());
    }
    
    public List<Component> getComponentWithFreeInputs() {
        return Components.stream().filter(it -> ((AbstractComponent)it).hasFreeInputs()).collect(Collectors.toList());
    }

    /**
     * @return the ID
     */
    public String getID() {
        return ID;
    }

    /**
     * @param ID the ID to set
     */
    public void setID(String ID) {
        this.ID = ID;
    }

    /**
     * @return the Name
     */
    public String getName() {
        return Name;
    }

    /**
     * @param Name the Name to set
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
