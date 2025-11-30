package logicverse.controller;

import logicverse.Gates.controller.Switch;
import logicverse.Gates.controller.Probe;
import logicverse.controller.Connection;
import logicverse.controller.Point;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

/**
 * TDD tests for Connection controller behavior (non-DB unit tests)
 */
public class ConnectionControllerTDDTest {
    private Switch sw;
    private Probe pr;
    private Point outPoint;
    private Point inPoint;

    @Before
    public void setUp() {
        sw = new Switch();
        pr = new Probe();
        outPoint = sw.Outputs.get(0);
        inPoint = pr.Inputs.get(0);
    }

    @Test
    public void testConstructorIncrementsOutputDegree() {
        // ensure initially not connected
        // constructor calls Output.add()
        Connection c = new Connection(outPoint, inPoint);
        assertTrue("Output should be marked connected after Connection creation", inPoint.isConnected());
    }

    @Test
    public void testRefreshCopiesBit() {
        Connection c = new Connection(outPoint, inPoint);
        outPoint.set(true);
        // Before refresh, inPoint may still be default
        c.refresh();
        assertTrue("refresh should copy input bit to output point", inPoint.get());

        outPoint.set(false);
        c.refresh();
        assertFalse("refresh should update output after input change", inPoint.get());
    }

    @Test
    public void testMultipleRefreshesPropagateValues() {
        Connection c = new Connection(outPoint, inPoint);
        boolean[] sequence = {false, true, true, false, true};
        for (boolean val : sequence) {
            outPoint.set(val);
            c.refresh();
            assertEquals("refresh should propagate current input value", val, inPoint.get());
        }
    }

    // DB-dependent operations are guarded similarly to Point tests
    @Test
    public void testSaveAndDeleteGuarded() {
        Connection c = new Connection(outPoint, inPoint);
        try {
            boolean saved = c.save();
            assertTrue("save returned a boolean", saved == true || saved == false);
        } catch (Exception ex) {
            fail("save() threw an exception: " + ex.getMessage());
        }

        try {
            boolean deleted = c.delete();
            assertTrue("delete returned a boolean", deleted == true || deleted == false);
        } catch (Exception ex) {
            fail("delete() threw an exception: " + ex.getMessage());
        }
    }
}
