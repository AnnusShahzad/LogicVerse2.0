/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package logicverse.Gates;

import logicverse.Gates.controller.NOT;
import static org.junit.Assert.assertEquals;
import org.junit.Test;

/**
 *
 * @author Admin
 */
public class NOTTest {
    @Test
    public void testExecute() {
        NOT instance = new NOT();
        // 2 Input AND Gate
//        boolean d[2] = {true, false};
        for(int i = 0; i < 2; i++) {
            boolean bit = (i == 1);
            instance.setInputBit(0, bit);
            instance.onExecute();
            boolean out = instance.Outputs.get(0).get();
            String msg = String.format("Inputs %s", bit, out, !bit);
            assertEquals(msg, out, bit);
        }
    }   
}
