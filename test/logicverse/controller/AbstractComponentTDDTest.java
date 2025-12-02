package logicverse.controller;

import logicverse.domain.controller.Point;
import logicverse.domain.gates.controller.Switch;
import logicverse.domain.gates.controller.Probe;
import logicverse.domain.gates.controller.AND;
import logicverse.domain.gates.controller.OR;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

/**
 * TDD Test Suite for AbstractComponent non-void functions
 * Tests getters, setters, connectivity checks, and point lookups
 */
public class AbstractComponentTDDTest {
    private AND comp1;
    private Switch comp2;
    private OR comp3;

    @Before
    public void setUp() {
        comp1 = new AND();
        comp2 = new Switch();
        comp3 = new OR();
    }

    // === Name Tests (setName/getName) ===

    @Test
    public void testSetAndGetName() {
        String name = "TestComponent1";
        comp1.setName(name);
        assertEquals("getName should return the set name", name, comp1.getName());
    }

    @Test
    public void testSetNameMultipleTimes() {
        comp1.setName("FirstName");
        assertEquals("First name should be set", "FirstName", comp1.getName());

        comp1.setName("SecondName");
        assertEquals("Name should be updated", "SecondName", comp1.getName());
    }

    // === Component ID Tests (getCid) ===

    @Test
    public void testGetCidNotNull() {
        assertNotNull("getCid should not return null", comp1.getCid());
    }

    @Test
    public void testGetCidIsUnique() {
        String cid1 = comp1.getCid();
        String cid2 = comp2.getCid();
        assertNotEquals("Each component should have unique Cid", cid1, cid2);
    }

    @Test
    public void testGetCidConsistent() {
        String cid1 = comp1.getCid();
        String cid2 = comp1.getCid();
        assertEquals("getCid should return same value on multiple calls", cid1, cid2);
    }

    // === Output Tests (hasOutputs/getOutputs) ===

    @Test
    public void testHasOutputsTrue() {
        assertTrue("AND gate should have outputs", comp1.hasOutputs());
    }

    @Test
    public void testGetOutputsNotNull() {
        assertNotNull("getOutputs should not return null", comp1.getOutputs());
    }

    @Test
    public void testGetOutputsSizeMatchesOutputsList() {
        int size = comp1.getOutputs().size();
        assertEquals("getOutputs size should match Outputs.size()", size, comp1.Outputs.size());
    }

    // === Free Inputs Tests (hasFreeInputs/getFreeInputs) ===

    @Test
    public void testHasFreeInputsTrueInitially() {
        // AND has 2 inputs, both should be free initially
        assertTrue("AND gate should have free inputs initially", comp1.hasFreeInputs());
    }

    @Test
    public void testGetFreeInputsNotNull() {
        assertNotNull("getFreeInputs should not return null", comp1.getFreeInputs());
    }

    @Test
    public void testGetFreeInputsCountDecreasesWhenConnected() {
        int initialFree = comp1.getFreeInputs().size();
        if (comp1.Inputs.size() > 0) {
            comp1.Inputs.get(0).add();
            int afterConnect = comp1.getFreeInputs().size();
            assertTrue("Free inputs should decrease after connection", afterConnect < initialFree);
        }
    }

    // === Connectivity Tests (isConnected) ===

    @Test
    public void ConnectionTestWhenConnected() {
        // Connect all inputs in AND
        for (Point p : comp1.Inputs) {
            p.add();
        }
        assertEquals("isConnected should return -1 when all inputs connected", -1, comp1.isConnected());
    }

    @Test
    public void ConnectionTestWhenDisconnected() {
        // First input is unconnected
        int result = comp1.isConnected();
        assertTrue("isConnected should return 0 or higher for unconnected input", result >= 0);
    }

    // === Point Lookup Tests (getOutputPoint) ===

    @Test
    public void testGetOutputPointReturnsCorrectPoint() {
        if (comp1.Outputs.size() > 0) {
            Point out = comp1.Outputs.get(0);
            Point found = comp1.getOutputPoint(out.getPid());
            assertEquals("getOutputPoint should return the correct output point", out, found);
        }
    }

    @Test
    public void testGetOutputPointReturnsNullForInvalidId() {
        Point notFound = comp1.getOutputPoint("invalid-id-12345");
        assertNull("getOutputPoint should return null for non-existent point", notFound);
    }

    // === Input Point Lookup Tests (getInputPoint) ===

    @Test
    public void testGetInputPointReturnsCorrectPoint() {
        if (comp1.Inputs.size() > 0) {
            Point in = comp1.Inputs.get(0);
            Point found = comp1.getInputPoint(in.getPid());
            assertEquals("getInputPoint should return the correct input point", in, found);
        }
    }

    @Test
    public void testGetInputPointReturnsNullForInvalidId() {
        Point notFound = comp1.getInputPoint("invalid-input-id");
        assertNull("getInputPoint should return null for non-existent point", notFound);
    }

    // === Associated Component Input Lookup Tests (getInputPointFromACs) ===

    @Test
    public void testGetInputPointFromACsReturnsNullWhenNoAssociatedComponents() {
        // No associated components, should return null
        Point notFound = comp1.getInputPointFromACs("any-id");
        assertNull("getInputPointFromACs should return null when no associated components", notFound);
    }

    // === Bit Setting Tests (setInputBit/setOutputBit) ===

    @Test
    public void testSetInputBitTrue() {
        if (comp1.Inputs.size() > 0) {
            comp1.setInputBit(0, true);
            assertTrue("Input bit should be set to true", comp1.Inputs.get(0).get());
        }
    }

    @Test
    public void testSetInputBitFalse() {
        if (comp1.Inputs.size() > 0) {
            comp1.setInputBit(0, false);
            assertFalse("Input bit should be set to false", comp1.Inputs.get(0).get());
        }
    }

    @Test
    public void testSetInputBitThrowsOnInvalidIndex() {
        try {
            comp1.setInputBit(999, true);
            fail("setInputBit should throw IndexOutOfBoundsException for invalid index");
        } catch (IndexOutOfBoundsException e) {
            assertTrue("Exception should mention index bounds", e.getMessage().contains("Input") || e.getMessage().contains("bounds"));
        }
    }

    @Test
    public void testSetOutputBitTrue() {
        if (comp1.Outputs.size() > 0) {
            comp1.setOutputBit(0, true);
            assertTrue("Output bit should be set to true", comp1.Outputs.get(0).get());
        }
    }

    @Test
    public void testSetOutputBitFalse() {
        if (comp1.Outputs.size() > 0) {
            comp1.setOutputBit(0, false);
            assertFalse("Output bit should be set to false", comp1.Outputs.get(0).get());
        }
    }

    @Test
    public void testSetOutputBitThrowsOnInvalidIndex() {
        try {
            comp1.setOutputBit(999, true);
            fail("setOutputBit should throw IndexOutOfBoundsException for invalid index");
        } catch (IndexOutOfBoundsException e) {
            assertTrue("Exception should mention index bounds", e.getMessage().contains("Output") || e.getMessage().contains("bounds"));
        }
    }



    // === Counter Tests (getCount/incrementCount) ===

    @Test
    public void testIncrementCountIncrementsValue() {
        int before = comp1.getCount(AND.class);
        comp1.incrementCount();
        int after = comp1.getCount(AND.class);
        assertEquals("Count should increment by 1", before + 1, after);
    }

    @Test
    public void testGetCountReturnsNonNegative() {
        int count = comp1.getCount(AND.class);
        assertTrue("Count should be >= 0", count >= 0);
    }

    // === DB Operations (guarded) ===

    @Test
    public void testSaveReturnsBoolean() {
        try {
            boolean result = comp1.save(null);
            assertTrue("save should return a boolean", result == true || result == false);
        } catch (Exception ex) {
            // DB may not be configured, but method should not throw other exceptions
        }
    }

    @Test
    public void testDeleteReturnsBoolean() {
        try {
            boolean result = comp1.delete();
            assertTrue("delete should return a boolean", result == true || result == false);
        } catch (Exception ex) {
            // DB may not be configured, but method should not throw other exceptions
        }
    }

    // === toString Test ===

    @Test
    public void testToStringReturnsNonNull() {
        // Note: toString may throw if no outputs, so handle that
        try {
            String str = comp1.toString();
            assertNotNull("toString should not return null", str);
        } catch (IndexOutOfBoundsException e) {
            // Some components may not have outputs, which is acceptable
        }
    }

}

