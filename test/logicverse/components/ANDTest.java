/*
 * Comprehensive TDD Unit Tests for AND Gate Controller
 * Tests all truth table combinations and edge cases
 */
package logicverse.components;

import logicverse.domain.gates.controller.AND;
import static org.junit.Assert.*;

import java.beans.Transient;

import org.junit.Test;
import org.junit.Before;

/**
 * TDD Test Suite for AND Gate (2-input)
 * Truth Table:
 * 0 0 -> 0
 * 0 1 -> 0
 * 1 0 -> 0
 * 1 1 -> 1
 */
public class ANDTest {
    private AND gate;

    @Before
    public void setUp() {
        gate = new AND();
        gate.setName("AND-Component");
    }
    // === Truth Table Tests ===    
    @Test
    public void testCompleteAndTruthTable() {
        boolean[][] truthTable = {
            {false, false, false},  // 0 AND 0 = 0
            {false, true, false},   // 0 AND 1 = 0
            {true, false, false},   // 1 AND 0 = 0
            {true, true, true}      // 1 AND 1 = 1
        };

        for (boolean[] row : truthTable) {
            gate.setInputBit(0, row[0]);
            gate.setInputBit(1, row[1]);
            gate.onExecute();
            boolean result = gate.Outputs.get(0).get();
            String msg = String.format("AND(%s, %s) should be %s", row[0], row[1], row[2]);
            assertEquals(msg, row[2], result);
        }
    }

    @Test
    public void testAND_InternalNameIsAND() {
        assertEquals("Internal name must be 'AND'", "AND", gate.getInternalName());
    }
    @Test
    public void testToString() {
        String str = gate.toString();
        assertNotNull("toString should not return null", str);
        assertEquals("toString should contain 'AND'", "AND-Component", str);
    }  

    // === Sequential Execution Tests ===
    
    @Test
    public void testAND_SequentialExecutions() {
        // First execution: 1 AND 1
        gate.setInputBit(0, true);
        gate.setInputBit(1, true);
        gate.onExecute();
        assertTrue("First execution: AND(1,1) should be 1", gate.Outputs.get(0).get());

        // Second execution: 0 AND 1
        gate.setInputBit(0, false);
        gate.setInputBit(1, true);
        gate.onExecute();
        assertFalse("Second execution: AND(0,1) should be 0", gate.Outputs.get(0).get());

        // Third execution: 1 AND 1
        gate.setInputBit(0, true);
        gate.setInputBit(1, true);
        gate.onExecute();
        assertTrue("Third execution: AND(1,1) should be 1", gate.Outputs.get(0).get());
    }
    
    // === Commutative Property Tests ===
    
    public void testAND_IsCommutative() {
        // AND(0, 1)
        gate.setInputBit(0, false);
        gate.setInputBit(1, true);
        gate.onExecute();
        boolean result1 = gate.Outputs.get(0).get();

        // AND(1, 0)
        gate.setInputBit(0, true);
        gate.setInputBit(1, false);
        gate.onExecute();
        boolean result2 = gate.Outputs.get(0).get();

        assertEquals("AND should be commutative: AND(0,1) == AND(1,0)", result1, result2);
    }


}
