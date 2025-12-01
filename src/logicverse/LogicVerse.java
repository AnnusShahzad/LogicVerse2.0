/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package logicverse;

import logicverse.Gates.controller.AND;
import logicverse.Gates.controller.NOT;
import logicverse.Gates.controller.OR;
import logicverse.Gates.controller.Switch;
import logicverse.view.MainFrame;

/**
 *
 * @author Admin
 */
public class LogicVerse {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        MainFrame frame = new MainFrame();
        frame.setVisible(true);
        ArrayList<AND> ands = new ArrayList<>();
        ArrayList<NOT> nots = new ArrayList<>();
        ArrayList<Switch> switches = new ArrayList<>();
        
        // Creating Objects of AND
        for(int i = 0; i < 4; i++) { ands.add(new AND()); }
        for(int i = 0; i < 2; i++) { nots.add(new NOT()); }
        for(int i = 0; i < 2; i++) { switches.add(new Switch()); }
        
        // Now Connecting
        switches.get(0).addConnection(nots.get(0).Inputs.get(0));
        switches.get(0).addConnection(ands.get(1).Inputs.get(0));
        switches.get(0).addConnection(ands.get(3).Inputs.get(1));
        switches.get(1).addConnection(nots.get(1).Inputs.get(0));
        switches.get(1).addConnection(ands.get(2).Inputs.get(0));
        switches.get(1).addConnection(ands.get(3).Inputs.get(0));
        
        nots.get(0).addConnection(ands.get(0).Inputs.get(0));
        nots.get(0).addConnection(ands.get(2).Inputs.get(1));
        nots.get(1).addConnection(ands.get(0).Inputs.get(1));
        nots.get(1).addConnection(ands.get(1).Inputs.get(1));
        switches.get(0).execute();
        switches.get(1).execute();
        // Now Printing Truth Table
        for(int i = 0; i < 2; i++) {
            for(int j = 0; j < 2; j++) {
                String msg = String.format("%s | %s | %s | %s | %s | %s", switches.get(1), switches.get(0), ands.get(3), ands.get(2), ands.get(1), ands.get(0));
                System.out.println(msg);
                switches.get(0).click();
                switches.get(0).execute();
                switches.get(1).execute();
            }
            switches.get(1).click();
        }  
    }
    
}
