/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package logicverse.Gates.controller;

import java.util.ArrayList;
import logicverse.controller.Component;

/**
 *
 * @author Admin
 */
public class Circuit {
    private ArrayList<Component> Components;
    private String ID;
    private String Name;

    /**
     * @return the Components
     */
    public ArrayList<Component> getComponents() {
        return Components;
    }

    /**
     * @param Components the Components to set
     */
    public void setComponents(ArrayList<Component> Components) {
        this.Components = Components;
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
}
