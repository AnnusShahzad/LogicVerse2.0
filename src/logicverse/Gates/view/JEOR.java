package logicverse.Gates.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import logicverse.Gates.model.JEComponent;
import logicverse.controller.Component;
import logicverse.Gates.controller.OR;

/**
 *
 * @author Admin
 */
public class JEOR extends JEComponent {
    // --- Define Gate Size and Center ---
    int w = 70; // Gate width
    int h = 90; // Gate height

    public JEOR() {
        super(new OR());
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(135, 100);
    }

    @Override
    public Dimension getMinimumSize() {
        return new Dimension(135, 100);
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.RED);
        draw((Graphics2D) g);
    }

    public void draw(Graphics2D obj) {

        // Calculate center of the drawing area
        int x_center = getWidth() / 2;
        int y_center = getHeight() / 2;

        // Define the origin point (top-left corner) for the gate's bounding box
        int x = x_center - w / 2;
        int y = y_center - h / 2;

        // --- Gate Shape Arcs ---

        // 1. Arc for the **Curved Input Side**
        int input_curve_radius = 80;
        int input_curve_x = x - 85;
        int input_curve_y = y - 5;
        int input_curve_h = h + 10;

        // Draw the arc that forms the curved input side
        obj.drawArc(input_curve_x + 23, input_curve_y - 2, input_curve_radius, input_curve_h, -45, 90);

        // 2. Arc for the **Curved Output Side**
        int output_curve_radius = 50;
        int output_arc_diameter = output_curve_radius + 180;
        int output_curve_x = x - 75 * 2 - 40;
        int output_curve_y = y;

        // Draw the arc that forms the curved output side
        obj.drawArc(output_curve_x + 23, output_curve_y, output_arc_diameter, h - 5, 60, -120);

        // --- Input and Output Lines ---
        int input_line_length = 30;
        int output_line_length = 15; // *** Reduced line length for the output ***

        // Input line 1 (Top)
        // Starts at x - input_line_length, ends at x
        obj.drawLine(x - input_line_length, y + h / 4, x + 10, y + h / 4);

        // Input line 2 (Bottom)
        // Starts at x - input_line_length, ends at x
        obj.drawLine(x - input_line_length, y + 3 * h / 4, x + 10, y + 3 * h / 4);

        // Output line :
        // Start X: The right edge of the gate body (x + w)
        // End X: Start X plus the new, short output_line_length
        obj.drawLine(x + w - 5, y_center, x + w + output_line_length + 20, y_center);
    }
}