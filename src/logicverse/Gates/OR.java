/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package logicverse.Gates;

import logicverse.controller.AbstractComponent;
import logicverse.controller.Connection;
import logicverse.controller.Point;

/**
 *
 * @author Admin
 */
public class OR extends AbstractComponent {
    public OR() {
        Inputs.add(new Point("X1", this));
        Inputs.add(new Point("X2", this));
        Outputs.add(new Point("Y1", this));
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
    // COBOL, Pascal, Scala, Julia, FORTRAN, VBA, Go, 
    public void addConnection(Point p) {
        Point in = Outputs.get(0);
        Connections.add(new Connection(in, p));
        Components.add(p.getParent());
    }
}
