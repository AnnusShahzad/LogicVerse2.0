/*
 * Comprehensive TDD Unit Tests for OR Gate Controller
 * Tests all truth table combinations and edge cases
 */
package logicverse.components;

import logicverse.Gates.controller.OR;
import static org.junit.Assert.*;

import java.beans.Transient;

import org.junit.Test;
import org.junit.Before;

/**
 * TDD Test Suite for OR Gate (2-input)
 * Truth Table:
 * 0 0 -> 0
 * 0 1 -> 1
 * 1 0 -> 1
 * 1 1 -> 1
 */
public class ORTest {
    private OR gate;

    @Before
    public void setUp() {
        gate = new OR();
        gate.setName("OR-Component");
    }
    // === Complete Truth Table ===
    
    @Test
    public void testCompleteOrTruthTable() {
        boolean[][] truthTable = {
            {false, false, false},  // 0 OR 0 = 0
            {false, true, true},    // 0 OR 1 = 1
            {true, false, true},    // 1 OR 0 = 1
            {true, true, true}      // 1 OR 1 = 1
        };

        for (boolean[] row : truthTable) {
            gate.setInputBit(0, row[0]);
            gate.setInputBit(1, row[1]);
            gate.onExecute();
            boolean result = gate.Outputs.get(0).get();
            String msg = String.format("OR(%s, %s) should be %s", row[0], row[1], row[2]);
            assertEquals(msg, row[2], result);
        }
    }


    // === Sequential Execution Tests ===
    
    @Test
    public void testOR_SequentialExecutions() {
        // First execution: 0 OR 0
        gate.setInputBit(0, false);
        gate.setInputBit(1, false);
        gate.onExecute();
        assertFalse("First execution: OR(0,0) should be 0", gate.Outputs.get(0).get());

        // Second execution: 1 OR 0
        gate.setInputBit(0, true);
        gate.setInputBit(1, false);
        gate.onExecute();
        assertTrue("Second execution: OR(1,0) should be 1", gate.Outputs.get(0).get());

        // Third execution: 0 OR 0
        gate.setInputBit(0, false);
        gate.setInputBit(1, false);
        gate.onExecute();
        assertFalse("Third execution: OR(0,0) should be 0", gate.Outputs.get(0).get());
    }

    // === Commutative Property Tests ===
    
    @Test
    public void testOR_IsCommutative() {
        // OR(0, 1)
        gate.setInputBit(0, false);
        gate.setInputBit(1, true);
        gate.onExecute();
        boolean result1 = gate.Outputs.get(0).get();

        // OR(1, 0)
        gate.setInputBit(0, true);
        gate.setInputBit(1, false);
        gate.onExecute();
        boolean result2 = gate.Outputs.get(0).get();

        assertEquals("OR should be commutative: OR(0,1) == OR(1,0)", result1, result2);
    }
}
