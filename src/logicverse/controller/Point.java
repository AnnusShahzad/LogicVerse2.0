/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package logicverse.controller;

/**
 *
 * @author Admin
 */
public class Point {
    String name;
    boolean bit;
    public boolean get() {
        return bit;
    }
    public void set(boolean state) {
        bit = state;
    }
    public void set(Point point) {
        bit = point.get();
    }
}
