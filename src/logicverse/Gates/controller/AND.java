/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package logicverse.Gates.controller;

import logicverse.Gates.model.JEComponent;
import logicverse.controller.AbstractComponent;
import logicverse.controller.Connection;
import logicverse.controller.Point;

/**
 *
 * @author Admin
 */
public class AND extends AbstractComponent {
    public AND() {
        internalName = "AND";
        shortName = internalName;
        name = shortName + getCount(this.getClass());
        Inputs.add(new Point("X1", this));
        Inputs.add(new Point("X2", this));
        Outputs.add(new Point("Y1", this));
//        this.container = container;
//        incrementCount(this.getClass());
    }
    public void onExecute() {
        boolean result = true;
        for(Point p: Inputs) {
            result &= p.get();
        }
        for(Point p: Outputs) {
            p.set(result);
        }
    }
    
    public String toString() {
//        Point in = Outputs.get(0);
        return name;
    }
}
