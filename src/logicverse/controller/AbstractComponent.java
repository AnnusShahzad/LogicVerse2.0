/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package logicverse.controller;

import java.util.ArrayList;

/**
 *
 * @author Admin
 */
public abstract class AbstractComponent implements Component {
    protected ArrayList<Point> Inputs;    
    protected ArrayList<Point> Outputs;
    protected ArrayList<Connection> Connections;
    /**
     * Name of the Component
     */
    String name;
    public void execute() {
        
    }
}
