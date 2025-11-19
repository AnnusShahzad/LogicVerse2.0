/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package logicverse.Gates.controller;

import logicverse.controller.AbstractComponent;
import logicverse.controller.ClickableComponent;
import logicverse.controller.Connection;
import logicverse.controller.Point;

/**
 *
 * @author Admin
 */
public class Switch extends ClickableComponent {
    public Switch() {
        Outputs.add(new Point("Switch X1", this));
    }
    @Override
    protected void onClick() {
        Outputs.get(0).set(clicked);
//        onExecute();
    }

    @Override
    public void onExecute() {
        Outputs.get(0).set(clicked);
    }

    @Override
    public void addConnection(Point p) {
        Point out = Outputs.get(0);
        Connections.add(new Connection(out, p));
        Components.add(p.getParent());
    }

    public String toString() {
        Point out = Outputs.get(0);
        return out.toString();
    }
}
