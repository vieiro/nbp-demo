/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.nbdemo.gui.features;

import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Insets;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.geom.Area;
import java.awt.geom.Rectangle2D;
import java.awt.geom.RoundRectangle2D;
import javax.swing.border.Border;

/**
 *
 * @author antonio
 */
public class CardShadowBorder implements Border {

    private static final int SHADOW_SIZE = 4;
    private static final Insets SHADOW_INSETS = new Insets(SHADOW_SIZE, SHADOW_SIZE, SHADOW_SIZE, SHADOW_SIZE);
    private static final double CORNER_RADIUS = 4;
    private static final Color SHADOW_COLOR = new Color(0, 0, 0, 0.05f);

    public static final CardShadowBorder BORDER = new CardShadowBorder();

    @Override
    public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        Area inner = new Area(new RoundRectangle2D.Double(x + SHADOW_SIZE, y + SHADOW_SIZE, width - 2 * SHADOW_SIZE, height - 2 * SHADOW_SIZE, CORNER_RADIUS, CORNER_RADIUS));

        // Component background
        if (c.getParent() != null) {
            Area borderArea = new Area(new Rectangle2D.Double(x, y, width, height));
            borderArea.subtract(inner);
            g2.setColor(c.getParent().getBackground());
            g2.fill(borderArea);
        }

        // Increasingly darker smaller round rectangles
        for (int i = 0; i <= SHADOW_SIZE; i++) {
            Area outer = new Area(new RoundRectangle2D.Double(x + i, y + i, width - i - i, height - i - i, CORNER_RADIUS, CORNER_RADIUS));
            outer.subtract(inner);
            g2.setColor(SHADOW_COLOR);
            g2.fill(outer);
        }
    }

    @Override
    public Insets getBorderInsets(Component c) {
        return SHADOW_INSETS;
    }

    @Override
    public boolean isBorderOpaque() {
        return true;
    }
}
