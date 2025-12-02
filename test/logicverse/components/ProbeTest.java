/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package logicverse.components;

import logicverse.domain.gates.controller.Probe;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

/**
 * Expanded TDD-style tests for `Probe` (3-4 concise cases)
 */
public class ProbeTest {
    private Probe p;

    @Before
    public void setUp() {
        p = new Probe();
    }

    @Test
    public void testObserveTrueAndFalse() {
        p.setInputBit(0, true);
        p.onExecute();
        assertTrue("Probe should observe true", p.Inputs.get(0).get());

        p.setInputBit(0, false);
        p.onExecute();
        assertFalse("Probe should observe false", p.Inputs.get(0).get());
    }

    @Test
    public void testDoesNotModifySignal() {
        p.setInputBit(0, true);
        boolean before = p.Inputs.get(0).get();
        p.onExecute();
        boolean after = p.Inputs.get(0).get();
        assertEquals("Probe should not modify observed signal", before, after);
    }

    @Test
    public void testToStringAndInputPresence() {
        String str = p.toString();
        assertNotNull("toString should not be null", str);
        assertTrue("toString should contain shortName or internal name", str.contains("PRB") || str.contains(p.getInternalName()));
        assertNotNull("Probe must have an input point", p.Inputs.get(0));
    }
}
