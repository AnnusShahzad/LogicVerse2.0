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
public class NOT extends AbstractComponent {
    public void execute() {
        // Just because Zohaib Hussain & Ali Ahmad said
//        boolean inputBit = Inputs.get(0).get();
//        Outputs.get(0).set(!inputBit);
        
        boolean result = true;
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
    }
    public void draw() {
        
    }
    public void addConnection() {
        
    }
}
