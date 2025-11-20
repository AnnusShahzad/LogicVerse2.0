/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package logicverse.Gates;

import static org.junit.Assert.*;
import org.junit.Test;

/**
 *
 * @author Admin
 */
public class ANDTest {
    /**
     * Test of execute method, of class AND.
     */
    @Test
    public void testExecute() {
        AND instance = new AND();
        // 2 Input AND Gate
//        boolean d[2] = {true, false};
        for(int i = 0; i < 4; i++) {
            boolean bit1 = i/2 != 0;
            boolean bit2 = i%2 != 0;
            instance.setInputBit(0, bit1);
            instance.setInputBit(1, bit2);
            instance.execute();
            boolean out = instance.Outputs.get(0).get();
            String msg = String.format("Inputs %s, %s Got %s, Expected %s", bit1, bit2, out, bit1 & bit2);
            if(i == 3) {
                assertTrue(msg, out);
                continue;
            }
            assertFalse(msg, out);
        }
    }
}
