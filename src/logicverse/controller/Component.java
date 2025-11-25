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
     * The Function that should be override by Children
     * This is by Default, Called in execute
    */
    public void onExecute();
    /**
     * The Actual Execute Function forming composite pattern
     * DON'T OVERRIDE IT, UNLESS ASKED!
     */
    public void execute();
    /**
     * Adds a New Connection to the component
     * @param i The Input of the Next Component which is connected to Output of this Component
     */
    public void addConnection(Point i);
    /**
     * Adds a New Connection to the component
     * @param i The Input of the Next Component to Connect to
     * @param o The Output of this Component to send from
     */
    public void addConnection(Point i, Point o);
}
