/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package logicverse.domain.gates.controller;

import logicverse.domain.controller.AbstractComponent;
import logicverse.domain.controller.Connection;
import logicverse.domain.controller.Drawable;
import logicverse.domain.controller.Point;
import logicverse.domain.model.Position;

/**
 *
 * @author Admin
 */
public class Probe extends AbstractComponent {
    public Probe() {
        internalName = "Binary Probe";
        shortName = "PRB";
        name = shortName + getCount(this.getClass());
        Inputs.add(new Point("X1", this));
//        incrementCount(this.getClass());
    }

    public Probe(Position Location, String Cid, String name, String internalName) {
        super(Location, Cid, name, internalName);
        shortName = "PRB";
    }
    
    
    
    public void onExecute() {
        // do Nothing
    }
    
    public String toString() {
//        Point in = Inputs.get(0);
        return name;
    }
}
