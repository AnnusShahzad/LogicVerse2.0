/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package logicverse.Gates;

import logicverse.controller.AbstractComponent;
import logicverse.controller.Point;

/**
 *
 * @author Admin
 */
public class AND extends AbstractComponent {
    public AND() {
        Inputs.add(new Point("X1"));
        Inputs.add(new Point("X2"));
        Outputs.add(new Point("Y1"));
    }
    public void execute() {
        boolean result = true;
        for(Point p: Inputs) {
            result &= p.get();
        }
        for(Point p: Outputs) {
            p.set(result);
        }
    }
    public void draw() {
        
    }
    public void addConnection() {
        
    }
}
