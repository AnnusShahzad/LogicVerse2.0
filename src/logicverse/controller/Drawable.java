/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package logicverse.controller;

import java.awt.Graphics2D;

/**
 * Responsible for Drawing JEComponent on the Canvas (Panel)
 * Interface used in JEComponents
 * @author Admin
 */
public interface Drawable {
    /**
     * Implements how the Component is drawn
     * @param obj Graphics2D Object of Panel (Canvas)
     */
    public void draw(Graphics2D obj);
}
