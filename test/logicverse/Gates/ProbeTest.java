/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package logicverse.Gates;

import logicverse.Gates.controller.Probe;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import org.junit.Test;

/**
 *
 * @author Admin
 */
public class ProbeTest {
    @Test
    public void testProbe() {
        Probe p = new Probe();
        p.setInputBit(0, true);
        assertTrue(p.Inputs.get(0).get());
        p.setInputBit(0, false);
        assertFalse(p.Inputs.get(0).get());
    }
}
