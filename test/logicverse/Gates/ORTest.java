/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package logicverse.Gates;

import logicverse.Gates.controller.OR;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import org.junit.Test;

/**
 *
 * @author Admin
 */
public class ORTest {
    @Test
    public void testExecute() {
        OR instance = new OR();
        // 2 Input OR Gate
//        boolean d[2] = {true, false};
        for(int i = 0; i < 4; i++) {
            boolean bit1 = i/2 != 0;
            boolean bit2 = i%2 != 0;
            instance.setInputBit(0, bit1);
            instance.setInputBit(1, bit2);
            instance.onExecute();
            boolean out = instance.Outputs.get(0).get();
            String msg = String.format("Inputs %s, %s Got %s, Expected %s", bit1, bit2, out, bit1 | bit2);
            if(i == 0) {
                assertFalse(msg, out);
                continue;
            }
            assertTrue(msg, out);
        }
    }   
}
