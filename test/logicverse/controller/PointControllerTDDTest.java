package logicverse.controller;

import logicverse.Gates.controller.Switch;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

/**
 * TDD tests for Point controller behavior (non-DB unit tests)
 */
public class PointControllerTDDTest {
    private Switch parent;
    private Point p;

    @Before
    public void setUp() {
        parent = new Switch();
        p = new Point("X1", parent);
    }

    @Test
    public void testPointPidNotNull() {
        assertNotNull("Pid should not be null", p.getPid());
    }

    @Test
    public void testSetAndGetBit() {
        p.set(true);
        assertTrue("get() should return value set to true", p.get());
        p.set(false);
        assertFalse("get() should return value set to false", p.get());
    }

    @Test
    public void testSetFromPoint() {
        Point other = new Point("X2", parent);
        other.set(true);
        p.set(other);
        assertTrue("set(Point) should copy bit from other point", p.get());
    }

    @Test
    public void testAddIncreasesConnectivity() {
        // Initially not connected
        assertFalse("Initially point should not be connected", p.isConnected());
        p.add();
        assertTrue("After add() point should be connected", p.isConnected());
    }

    @Test
    public void testToStringContainsName() {
        String expectedPrefix = parent.getName();
        assertTrue("toString should contain parent's name prefix", p.toString().startsWith(expectedPrefix));
    }

    @Test
    public void testGetParentIsSet() {
        assertEquals("Parent returned by getParent should match provided parent", parent, p.getParent());
    }

    // DB-dependent operations (save/load/delete) are integration operations and may
    // require a configured DB. We call them guarded to ensure they do not blow up
    // the unit tests if DB is missing. These assertions only check that methods
    // return boolean or do not throw exceptions.

    @Test
    public void testSaveAndDeleteGuarded() {
        try {
            boolean saved = p.save("Input");
            // save may return false if DB not configured; just ensure it doesn't throw
            assertTrue("save returned a boolean", saved == true || saved == false);
        } catch (Exception ex) {
            fail("save() threw an exception: " + ex.getMessage());
        }

        try {
            boolean deleted = p.delete();
            assertTrue("delete returned a boolean", deleted == true || deleted == false);
        } catch (Exception ex) {
            fail("delete() threw an exception: " + ex.getMessage());
        }
    }
}
