/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package logicverse.Gates.controller;

import java.util.ArrayList;
import logicverse.controller.AbstractComponent;
import logicverse.controller.Connection;
import logicverse.controller.Point;
import logicverse.model.Position;

/**
 *
 * @author Admin
 */
public class NOT extends AbstractComponent {
    public NOT() {
        internalName = "NOT";
        shortName = internalName;
        name = shortName + getCount(this.getClass());
        Inputs.add(new Point("X1", this));
        Outputs.add(new Point("Y1", this));
//        incrementCount(this.getClass());
    }

    public NOT(Position Location, String Cid, String name, String internalName) {
        super(Location, Cid, name, internalName);
        shortName = internalName;
    }
    
    
    
    public void onExecute() {
        // Just because Zohaib Hussain & Ali Ahmad said
        boolean inputBit = Inputs.get(0).get();
//         Outputs.get(0).set(!inputBit);
//        System.out.println("Hello");
        boolean result = inputBit;
        boolean out = true;
        for(Point p: Inputs) {
            result ^= p.get();
            out = p.get();
        }
        if (result)
            return;
        for(Point p: Outputs) {
            p.set(!out);
        }
//        System.out.println("Called with Input " + inputBit);
    }
    
    public String toString() {
//        Point in = Outputs.get(0);
        return name;
    }
}
