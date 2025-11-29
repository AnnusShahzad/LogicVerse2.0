/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package logicverse.Gates.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import logicverse.Gates.model.JEComponent;
import logicverse.controller.Component;
import logicverse.Gates.controller.AND;

/**
 *
 * @author Admin
 */
public class JEAND extends JEComponent {
    // --- Define Gate Size and Center ---
    int w = 70; // Gate width
    int h = 90; // Gate height

    public JEAND() {
        super(new AND());
    }

    @Override
    public Dimension getPreferredSize() {
        // Calculate total width needed: input space + gate width + output space
        int totalWidth = 60 + w + 30; // input_line(30) + w + output_line(30)
        int totalHeight = h + 10; // h + vertical padding
        return new Dimension(totalWidth, totalHeight);
    }

    @Override
    public Dimension getMinimumSize() {
        return getPreferredSize();
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

        // --- AND Gate Shape ---
        // Draw two lines: left vertical + curved right side
        // No top or bottom horizontal lines

        // Left vertical line only
        obj.drawLine(x, y, x, y + h);
        // upper horizontal line only
        obj.drawLine(x, y, x + w / 2, y);

        // lower horizontal line only
        obj.drawLine(x, y + h, x + w / 2, y + h);
        // Curved right side (semi-circle)
        int curveRadius = h / 2;
        obj.drawArc(x + w - curveRadius - 35, y, curveRadius * 2, h, -90, 180);

        // --- Input and Output Lines ---
        int line_length = 30;

        // Input line 1 (Top)
        obj.drawLine(x - line_length - 13, y + h / 4, x, y + h / 4);

        // Input line 2 (Bottom)
        obj.drawLine(x - line_length - 13, y + 3 * h / 4, x, y + 3 * h / 4);

        // Output line (from the center right)
        obj.drawLine(x + w + 13, y_center, x + w + line_length + 15, y_center);
    }
}