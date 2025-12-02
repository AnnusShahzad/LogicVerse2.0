/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package logicverse.domain.gates.controller;

import java.util.ArrayList;
import logicverse.domain.controller.AbstractComponent;
import logicverse.domain.controller.Connection;
import logicverse.domain.controller.Point;
import logicverse.domain.model.Position;

/**
 *
 * @author Admin
 */
public class OR extends AbstractComponent {
    public OR() {
        internalName = "OR";
        shortName = internalName;
        name = shortName + getCount(this.getClass());
        Inputs.add(new Point("X1", this));
        Inputs.add(new Point("X2", this));
        Outputs.add(new Point("Y1", this));
//        incrementCount(this.getClass());
    }

    public OR(Position Location, String Cid, String name, String internalName) {
        super(Location, Cid, name, internalName);
        shortName = internalName;
    }
    
    
    
    public void onExecute() {
        boolean result = false;
        for(Point p: Inputs) {
            result |= p.get();
        }
        for(Point p: Outputs) {
            p.set(result);
        }
    }
    public void draw() {
        
    }
    
    public String toString() {
//        Point in = Outputs.get(0);
        return name;
    }
}
