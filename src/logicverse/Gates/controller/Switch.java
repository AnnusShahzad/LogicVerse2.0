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
        internalName = "Binary Switch";
        shortName = "SW";
        name = shortName + getCount(this.getClass());
        Outputs.add(new Point("SWX1", this));
//        incrementCount(this.getClass());
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
