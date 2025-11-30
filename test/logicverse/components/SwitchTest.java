/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package logicverse.components;

import logicverse.Gates.controller.Switch;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

/**
 * Expanded TDD-style tests for `Switch` (3-4 concise cases)
 * modeled after AND/OR/NOT tests style.
 */
public class SwitchTest {
    private Switch s;

    @Before
    public void setUp() {
        s = new Switch();
    }

    @Test
    public void clickTogglesState() {
        // Initial state should be false (OFF)
        assertFalse("initial state should be OFF", s.getState());
        s.click();
        assertTrue("after one click state should be ON", s.getState());
    }

    @Test
    public void repeatedClicksAlternateState() {
        boolean expected = s.getState();
        for (int i = 0; i < 4; i++) {
            s.click();
            expected = !expected;
            assertEquals(String.format("after %d clicks", i+1), expected, s.getState());
        }
    }

    @Test
    public void toStringContainsName() {
        String str = s.toString();
        assertNotNull("toString should not be null", str);
        assertTrue("toString should contain shortName or internal name", str.contains("SW") || str.contains(s.getInternalName()));
    }

    @Test
    public void outputsHaveOneBit() {
        assertNotNull("Outputs list should not be null", s.Outputs);
        assertEquals("Switch must have exactly one output", 1, s.Outputs.size());
    }
}
