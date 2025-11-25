/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package logicverse.controller;

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
    public void click() {
        clicked = !clicked;
        onClick();
        execute();
    }
}