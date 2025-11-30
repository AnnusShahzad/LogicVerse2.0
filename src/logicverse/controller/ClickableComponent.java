/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package logicverse.controller;

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
     * Fires the Component Click Event
     */
    public void click() {
        clicked = !clicked;
        onClick();
        execute();
    }
}