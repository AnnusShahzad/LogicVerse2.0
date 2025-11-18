/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package logicverse.Gates;

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
        Outputs.add(new Point("Y1"));
    }
    @Override
    protected void onClick() {
        Outputs.get(0).set(clicked);
        execute();
    }

    @Override
    public void execute() {
        Outputs.get(0).set(clicked);
        for(Connection c: Connections) {
            c.refresh();
        }
    }

    @Override
    public void draw() {
        
    }

    @Override
    public void addConnection(Point ac) {
        Point out = Outputs.get(0);
        Connections.add(new Connection(out, ac));
    }    
}
