/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package logicverse.Gates;

import logicverse.Gates.controller.Switch;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import org.junit.Test;

/**
 *
 * @author Admin
 */
public class SwitchTest {
    @Test
    public void clickTest() {
        Switch s = new Switch();
        boolean flip = false;
        assertFalse(s.getState());
        s.click();
        assertTrue(s.getState());
    }
}
