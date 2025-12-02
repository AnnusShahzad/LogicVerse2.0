/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package logicverse.controller;

/**
 * Points used by Electrical Components (Input/Output)
 * Responsible for Fetching and Sending Bits on Path
 * @author Admin
 */
public class Point {
    /**
     * Point Name
     * Useful for Debugging
     */
    String name;
    /**
     * The Boolean bit it has
     */
    boolean bit;
    /**
     * Number of Other Points Connected
     * 0, If the Point is free (Not Connected).
     */
    int degree;
    /**
     * Component which has this Point
     */
    Component parent;
    /**
     * Default Constructor
     * Initializes attributes and default values
     */
    public Point() {
        name = "";
        parent = null;
        degree = 0;
    }
    /**
     * Parameterized Constructor
     * Initializes attributes by passed parameter values
     * @param name Point Name
     * @param parent Component having Point
     */
    public Point(String name, Component parent) {
        this.name = name;
        this.parent = parent;
    }
    /**
     * Gets the Point Bit which was set
     * @return Bit of Point
     */
    public boolean get() {
        return bit;
    }
    /**
     * Gets the Component having Point
     * @return Component Object
     */
    public Component getParent() {
        return parent;
    }
    /**
     * Sets the Point bit (Boolean)
     * @param state new bit
     */
    public void set(boolean state) {
        bit = state;
    }
    /**
     * Sets the Point bit from another Point
     * @param point Another Point to fetch bit from
     */
    public void set(Point point) {
        bit = point.get();
    }
    /**
     * Responsible for Adding the Point Degree
     */
    public void add() {
        degree++;
    }
    /**
     * Gets whether a Point is Connected or not
     * True, If connected. False otherwise
     * @return Point Connection State
     */
    public boolean isConnected() {
        return degree != 0;
    }
    /**
     * Default toString Method
     * Used for Accessibility
     * @return 
     */
    public String toString() {
        return name;
    }
}
