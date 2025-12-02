/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package logicverse.controller;
import logicverse.model.Position;

/**
 *
 * @author Admin
 */
public abstract class ClickableComponent extends AbstractComponent {
    protected boolean clicked = false;
    protected abstract void onClick();
    public boolean getState() {
        return clicked;
    }

    public ClickableComponent() {
        super();
        clicked = false;
    }
    
    public ClickableComponent( Position Location, String Cid, String name, String internalName , boolean clicked) {
        super( Location, Cid, name, internalName);
        this.clicked = clicked;        
    }
    
    
    
    public void click() {
        clicked = !clicked;
        onClick();
        execute();
    }
}