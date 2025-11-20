/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package logicverse.controller;

/**
 *
 * @author Admin
 */
public class Connection {
    Point Input;
    Point Output;
    public Connection(Point a, Point b) {
        Input = a;
        Output = b;
        Output.add();
    }
    public void refresh() {
        Output.set(Input);
    }
}
