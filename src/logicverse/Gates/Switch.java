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
        onExecute();
    }

    @Override
    public void onExecute() {
        Outputs.get(0).set(clicked);
    }

    @Override
    public void draw() {
        
    }

    @Override
    public void addConnection(Point p) {
        Point out = Outputs.get(0);
        Connections.add(new Connection(out, p));
    }    
}
