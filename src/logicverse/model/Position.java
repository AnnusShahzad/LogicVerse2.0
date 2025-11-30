/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package logicverse.model;

/**
 *
 * @author Admin
 */
public class Position {
    private int x;
    private int y;
    public Position(int x, int y) {
        this.x = x;
        this.y = y;
    }
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
     * @return the X Axis Component
     */
    public int getX() {
        return x;
    }

    /**
     * @param x the X Axis Component
     */
    public void setX(int x) {
        this.x = x;
    }

    /**
     * @return the Y Axis Component
     */
    public int getY() {
        return y;
    }

    /**
     * @param y the Y Axis Component
     */
    public void setY(int y) {
        this.y = y;
    }
}
