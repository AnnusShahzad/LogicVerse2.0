package logicverse.Gates.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Path2D;
import logicverse.Gates.controller.OR;
import logicverse.Gates.model.JEDraggableComponent;
import logicverse.controller.AbstractComponent;

/**
 *
 * @author Admin
 */
public class JEOR extends JEDraggableComponent {
    final int d = 25;
    final int l = 20;
    final int r = 10;
    final int disp_x = 5;
    final int disp_y = 0;

    public JEOR() {
        super(new OR());
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(111, 111);
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.RED);
        draw((Graphics2D) g);
    }

    public void draw(Graphics2D obj) {
        int w = getWidth();
        int h = getHeight();
        int cx = w/2;
        int cy = h/2;
        
//        obj.drawRect(0, 0, 110g, 105);
        
        Path2D.Double path = new Path2D.Double();
        path.moveTo(disp_x+l/2, 26);                                // Sets the Top Pointy Curve of Top Curve
        path.quadTo(disp_x+cx+l+7-15, cy-35, disp_x+cx+l+10, cy);   // Starts a Curve along the Right Most Point where OUput is
        path.moveTo(disp_x+l/2, 83.05f);                            // Sets the Bottom Pointy Curve of Bottom Curve
        path.quadTo(disp_x+cx+l+7-15, cy+35, disp_x+cx+l+10, cy);   // Starts a Curve along the Right Most Point where Output is
        obj.draw(path);
        obj.drawLine(disp_x, 2*h/5, disp_x+l, 2*h/5); // Input 1
        obj.drawLine(disp_x, 3*h/5, disp_x+l, 3*h/5); // Input 2
        obj.drawArc(disp_x, 5*h/20, l+4, l+35, -90, 180); // Bell Bottom Curve
        obj.drawLine(disp_x+cx+l+10, cy, disp_x+cx+l+30, cy);
    
        ((AbstractComponent)component).Inputs.get(0).setPosition(disp_x, 2*h/5);
        ((AbstractComponent)component).Inputs.get(1).setPosition(disp_x, 3*h/5);
        ((AbstractComponent)component).Outputs.get(0).setPosition(disp_x+cx+l+30, cy);
        
        obj.setFont(new Font("Arial", Font.PLAIN, 11));
        obj.drawString(component.getName(), cx-(component.getName().length()*4.5f), cy+3);
    }
}