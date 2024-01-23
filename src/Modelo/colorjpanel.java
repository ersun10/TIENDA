/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;

/**
 *
 * @author luisj
 */

public class colorjpanel extends javax.swing.JPanel {

    
    @Override
    public void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        Color color1 = Color.GREEN;
        Color color2 = Color.BLUE;
        int steps = 50;
        int rectWidth = 10;
        int rectHeight = 150;

        for (int i = 0; i < steps; i++) {
            float ratio = (float) i / (float) steps;
            int red = (int) (color2.getRed() * ratio + color1.getRed() * (1 - ratio));
            int green = (int) (color2.getGreen() * ratio + color1.getGreen() * (1 - ratio));
            int blue = (int) (color2.getBlue() * ratio + color1.getBlue() * (1 - ratio));
            Color stepColor = new Color(red, green, blue);
            Rectangle2D rect2D = new Rectangle2D.Float(rectWidth * i, 0, rectWidth, rectHeight);
            g2.setPaint(stepColor);
            g2.fill(rect2D);
        }
      
    }
    
}