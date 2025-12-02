/*
 * Comprehensive TDD Unit Tests for NOT Gate Controller
 * Tests truth table and edge cases for 1-input NOT gate
 */
package logicverse.components;

import logicverse.domain.gates.controller.NOT;
import static org.junit.Assert.*;
import org.junit.Test;
import org.junit.Before;

/**
 * TDD Test Suite for NOT Gate (1-input inverter)
 * Truth Table:
 * 0 -> 1
 * 1 -> 0
 */
public class NOTest {
    private NOT gate;

    @Before
    public void setUp() {
        gate = new NOT();
        gate.setName("NOT-Component");
    }

    // === Truth Table Tests ===
    
    @Test
    public void testNOT_Input0_Output1() {
        gate.setInputBit(0, false);
        gate.onExecute();
        assertTrue("NOT(0) must return 1", gate.Outputs.get(0).get());
    }

    @Test
    public void testNOT_Input1_Output0() {
        gate.setInputBit(0, true);
        gate.onExecute();
        assertFalse("NOT(1) must return 0", gate.Outputs.get(0).get());
    }

    // === Complete Truth Table ===
    
    @Test
    public void testCompleteNotTruthTable() {
        boolean[][] truthTable = {
            {false, true},   // NOT(0) = 1
            {true, false}    // NOT(1) = 0
        };

        for (boolean[] row : truthTable) {
            gate.setInputBit(0, row[0]);
            gate.onExecute();
            boolean result = gate.Outputs.get(0).get();
            String msg = String.format("NOT(%s) should be %s", row[0], row[1]);
            assertEquals(msg, row[1], result);
        }
    }
    @Test
    public void testToString() {
        String str = gate.toString();
        assertNotNull("toString should not return null", str);
        assertEquals("toString should contain 'NOT'", "NOT-Component", str);
    }


   
}
