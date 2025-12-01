/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package logicverse.Gates.controller;

import logicverse.controller.AbstractComponent;
import logicverse.controller.Connection;
import logicverse.controller.Drawable;
import logicverse.controller.Point;

/**
 *
 * @author Admin
 */
public class Probe extends AbstractComponent {
    public Probe() {
        internalName = "Binary Probe";
        Inputs.add(new Point("X1", this));
    }
    public void onExecute() {
        // do Nothing
    }
    
    public String toString() {
        Point in = Inputs.get(0);
        return in.toString();
    }
}
