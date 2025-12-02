/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package logicverse.domain.model;

/**
 * Maintains the Position of the Components on the Canvas
 * @author Admin
 */
public class Position {
    /**
     * X-Axis Coordinate
     */
    private int x;
    /**
     * Y-Axis Coordinate
     */
    private int y;
    /**
     * Parameterized Constructor
     * Initializes the X and Y Axis Coordinates
     * @param x X-Axis Coordinates
     * @param y Y-Axis Coordinates
     */
    public Position(int x, int y) {
        this.x = x;
        this.y = y;
    }
    /**
     * Default Constructor
     * Initializes the coordinates to 0
     */
    public Position() {
        x = 0;
        y = 0;
    }

    /**
     * Sets the Position Attributes
     * @param x X-Axis Attributes
     * @param y Y-Axis Attributes
     */
    public void setPosition(int x, int y) {
        this.x = x;
        this.y = y;
    }
    
    /**
     * Gets the X-Axis Coordinates
     * @return X Axis 
     */
    public int getX() {
        return x;
    }

    /**
     * Sets the X-Axis Coordinates
     * @param x New X Axis Coordinates
     */
    public void setX(int x) {
        this.x = x;
    }

    /**
     * Gets the Y-Axis Coordinates
     * @return Y Axis Coordinates
     */
    public int getY() {
        return y;
    }

    /** Sets the Y-Axis Coordinates
     * @param y Y Axis Coordinates
     */
    public void setY(int y) {
        this.y = y;
    }
}
