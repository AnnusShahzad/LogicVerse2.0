/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package logicverse;

import logicverse.Gates.AND;
import logicverse.Gates.NOT;
import logicverse.Gates.OR;
import logicverse.Gates.Switch;

/**
 *
 * @author Admin
 */
public class LogicVerse {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
//      MainFrame frame = new MainFrame();
//      frame.setVisible(true);
        AND and = new AND();
        OR or = new OR();
        NOT not = new NOT();
        Switch s1 = new Switch();
        Switch s2 = new Switch();
        
//        s1.click();
//        s2.click();
        
        s1.addConnection(and.Inputs.get(0));
        s2.addConnection(and.Inputs.get(1));
        
        s2.addConnection(or.Inputs.get(1));
        
        and.addConnection(or.Inputs.get(0));
        
        or.addConnection(not.Inputs.get(0));
        
        s1.execute();
        s2.execute();
        
        System.out.println(String.format("AND INPUTS %s, %s OUPUTS %s", and.Inputs.get(0), and.Inputs.get(1), and.Outputs.get(0)));
        System.out.println(String.format("OR INPUTS %s, %s OUPUTS %s", or.Inputs.get(0), or.Inputs.get(1), or.Outputs.get(0)));
        
//        System.out.println()
//        and.execute();
//        or.execute();
//        not.execute();
        
//        System.out.println(String.format("Switch 1 Input: %s", s1.Outputs.get(0).get()));
//        System.out.println(String.format("Switch 2 Input: %s", s2.Outputs.get(0).get()));
        
        System.out.println(String.format("Final Output %s", not.Outputs.get(0).get()));
//        not.addConnection(p);
    }
    
}
