/*
 * Copyright 2017 Antonio Vieiro (antonio@vieiro.net)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.nbdemo.gui.features;

import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Insets;
import java.awt.LayoutManager;
import java.util.ArrayList;

/**
 * A FlowLayout with a maximum width.
 *
 * @author Antonio Vieiro (antonio@vieiro.net)
 */
public class WidthFlowLayout implements LayoutManager {

    private Component widthComponent;

    public WidthFlowLayout(Component widthComponent) {
        this.widthComponent = widthComponent;
    }

    @Override
    public void addLayoutComponent(String string, Component component) {
    }

    @Override
    public void removeLayoutComponent(Component component) {
    }

    @Override
    public Dimension preferredLayoutSize(Container container) {
        int maxWidth = widthComponent.getWidth();
        Insets insets = container.getInsets();
        int x = insets.left;
        int y = insets.top;
        maxWidth -= insets.left + insets.right;
        int rowHeight = 0;
        
        int maxX = 0;
        int maxY = insets.top;

        for (int i = 0; i < container.getComponents().length; i++) {
            Component c = container.getComponent(i);
            Dimension cd = c.getPreferredSize();
            boolean moveToNextRow = cd.width < maxWidth && (x + cd.width) > maxWidth;
            if (moveToNextRow) {
                x = insets.left;
                y = y + rowHeight;
                maxY = Math.max(maxY, y + cd.height);
                rowHeight = cd.height;
            } else {
                maxX = Math.max(maxX, x + cd.width);
                maxY = Math.max(maxY, y + cd.height);
                x = x + cd.width;
                rowHeight = Math.max(rowHeight, cd.height);
            }
        }
        maxY += insets.bottom;
        return new Dimension(maxX, maxY);
    }

    @Override
    public Dimension minimumLayoutSize(Container container) {
        return preferredLayoutSize(container);
    }

    @Override
    public void layoutContainer(Container container) {
        if (container.getComponents().length == 0) {
            return;
        }

        int maxWidth = widthComponent.getWidth();
        Insets insets = container.getInsets();
        int x = insets.left;
        int y = insets.top;
        maxWidth -= insets.left + insets.right;
        int rowHeight = 0;
        
        for (int i = 0; i < container.getComponents().length; i++) {
            Component c = container.getComponent(i);
            Dimension cd = c.getPreferredSize();
            boolean moveToNextRow = cd.width < maxWidth && (x + cd.width) > maxWidth;
            if (moveToNextRow) {
                x = insets.left;
                y = y + rowHeight;
                c.setBounds(x, y, cd.width, cd.height);
                x += cd.width;
                rowHeight = cd.height;
            } else {
                c.setBounds(x, y, cd.width, cd.height);
                x = x + cd.width;
                rowHeight = Math.max(rowHeight, cd.height);
            }
        }
    }

}
