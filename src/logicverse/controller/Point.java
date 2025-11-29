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
    int degree;
    Component parent;
    public Point() {
        name = "";
        parent = null;
        degree = 0;
    }
    public Point(String name, Component parent) {
        this.name = name;
        this.parent = parent;
    }
    public boolean get() {
        return bit;
    }
    public Component getParent() {
        return parent;
    }
    public void set(boolean state) {
        bit = state;
    }
    public void set(Point point) {
        bit = point.get();
    }
    public void add() {
        degree++;
    }
    public boolean isConnected() {
        return degree != 0;
    }
    public String toString() {
        return name;
    }
}
