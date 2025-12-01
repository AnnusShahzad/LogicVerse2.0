/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package logicverse.controller;

import logicverse.Gates.model.JEComponent;
import logicverse.model.Position;

/**
 * Component is the Electrical Component on the Circuit Diagram
 * @author Admin
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
    /**
     * Performs the Click Event on Component Level, Changing Attributes in `onClick` method
     */
    public void click();
    /**
     * Gets the Clicked State of an Component
     * @return Returns True If Component is Clicked, False Otherwise
     */
    public boolean getState();
    
    /**
     * Sets the Location of the Component
     * @param x X-Axis Component
     * @param y Y-Axis Component
     */
    public void setLocation(int x, int y);
    /**
     * Returns the Internal Name of a Component (AND,OR,NOT,SWITCH,PROBE)
     * @return Internal Name of Component
     */
    public String getInternalName();
    /**
     * Returns the Name of the Component (AND1, OR1 etc)
     * @return Name of Component
     */
    public String getName();
    /**
     * Sets the Name of the Component passed by the parameter
     * @param name New Name of the Component
     */
    public void setName(String name);
    /**
     * Increments the Component Count
     * USAGE: Component Counts is used to generate Unique Short Names
     */
    public void incrementCount();
    /**
     * to Strings
     */
    public String toString();
    /**
     * Gets component id
     */
    public String getCid();
    // dao funcitons
    /**
     * Delete a Component
     * @return Boolean
     */
    public boolean delete();
    /**
     * Save a Component
     * @param CIRid id of circuit to which this component belong
     * @return Boolean (true if Component is saved correctly)
     */
    public boolean save(String CIRid);
    /**
     * Gets the Container of the Component that houses it
     * @return JEComponent that houses the Container
     */
    public JEComponent getContainer();
    /**
     * Sets the Component Container
     * @param c Component Container
     */
    public void setContainer(JEComponent c);
    /**
     * Gets the Position of the Component
     * @return Position Object
     */
    public Position getPosition();
}
