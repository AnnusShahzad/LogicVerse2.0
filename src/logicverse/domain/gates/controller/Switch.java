/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package logicverse.domain.gates.controller;

import logicverse.domain.controller.ClickableComponent;
import logicverse.domain.controller.Point;
import logicverse.domain.model.Position;

/**
 *
 * @author Admin
 */
public class Switch extends ClickableComponent {
    public Switch() {
        internalName = "Binary Switch";
        shortName = "SW";
        name = shortName + getCount(this.getClass());
        Outputs.add(new Point("X1", this));
//        incrementCount(this.getClass());
    }

    public Switch( Position Location, String Cid, String name, String internalName, boolean clicked) {
        super( Location, Cid, name, internalName, clicked);
        shortName = "SW";
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

    public String toString() {
//        Point out = Outputs.get(0);
        return name;
    }
}
