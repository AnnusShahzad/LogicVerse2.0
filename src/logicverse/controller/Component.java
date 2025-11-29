/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package logicverse.controller;

/**
 *
 * @author Admin
 * Component is the Electrical Component on the Circuit Diagram
 * 
 */
public interface Component {
    /**
     * Execute will be modified by the Children
     * This will process the Inputs and Set the Outputs
    */
    public void onExecute();
    public void execute();
    /**
     * Adds Connection to the Component
     * Has 1 Input, 1 Output
     */
    public void addConnection(Point c);
}
