/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package logicverse.domain.controller;
import java.util.Hashtable;
import logicverse.domain.model.Position;

/**
 * An Clickable Implementation of AbstractComponent
 * @author Admin
 */
public abstract class ClickableComponent extends AbstractComponent {
    /**
     * Clicked State of the Component
     * By Default, It is set to false
     */
    protected boolean clicked = false;
    /**
     * Method responsible for handling operations on click event
     */
    protected abstract void onClick();
    /**
     * Gets the Clicked State of the Component
     * @return Component Clicked State
     */
    public boolean getState() {
        return clicked;
    }

    /**
     * Default Constructor.
     * Initializes a new clickable component with clicked state set to false.
     * Calls the parent AbstractComponent constructor.
     */
    public ClickableComponent() {
        super();
        clicked = false;
    }
    
    /**
     * Parameterized Constructor for loading existing clickable components.
     * Initializes component with location, ID, name, internal name, and clicked state.
     * Used when restoring components from database.
     * @param Location Component's position on the canvas
     * @param Cid Component unique identifier
     * @param name Component display name
     * @param internalName Component type identifier
     * @param clicked Initial clicked state of the component
     */
    public ClickableComponent( Position Location, String Cid, String name, String internalName , boolean clicked) {
        super( Location, Cid, name, internalName);
        this.clicked = clicked;        
    }
    
    
    
    /**
     * Fires the Component Click Event
     */
    public void click() {
        clicked = !clicked;
        onClick();
        execute();
    }
    
        /**
     * Save a clickable Component
     * @return Boolean (true if Component is saved correctly)
     */
    public boolean save(String CIRid){
        Hashtable<String,String> data = new Hashtable<>();
        data.put("Cid",this.Cid);
        data.put("CIRid", CIRid);
        data.put("Name",this.name);
        data.put("internalName",this.internalName);
        data.put("PosX",String.valueOf(this.Location.getX()));
        data.put("PosY",String.valueOf(this.Location.getY()));
        data.put("clicked", (clicked)?"1":"0");
        
        return (dao.save(data) && saveAttribues());
    }
}
