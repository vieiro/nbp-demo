/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
package org.nbdemo.gui.features;

import javax.swing.ImageIcon;
import org.nbdemo.gui.features.model.NetBeansPlatformFeature;
import org.openide.util.ImageUtilities;

/**
 *
 * @author antonio
 */
public class ModuleCardPanel extends javax.swing.JPanel {

    private NetBeansPlatformFeature feature;
    
    /**
     * Creates new form FeatureCard
     */
    public ModuleCardPanel() {
        initComponents();
        cardContent.setBorder(CardShadowBorder.BORDER);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        cardContent = new javax.swing.JPanel();
        cardImage = new javax.swing.JLabel();
        txtTitle = new javax.swing.JTextArea();
        txtSubtitle = new javax.swing.JTextArea();
        txtDescription = new javax.swing.JTextArea();

        setLayout(new javax.swing.BoxLayout(this, javax.swing.BoxLayout.LINE_AXIS));

        cardContent.setBackground(new java.awt.Color(254, 254, 254));
        cardContent.setLayout(new java.awt.GridBagLayout());

        cardImage.setBackground(new java.awt.Color(0, 0, 0));
        org.openide.awt.Mnemonics.setLocalizedText(cardImage, org.openide.util.NbBundle.getMessage(ModuleCardPanel.class, "ModuleCardPanel.cardImage.text")); // NOI18N
        cardImage.setMinimumSize(new java.awt.Dimension(480, 270));
        cardImage.setPreferredSize(new java.awt.Dimension(480, 270));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 0.1;
        gridBagConstraints.weighty = 0.1;
        cardContent.add(cardImage, gridBagConstraints);

        txtTitle.setEditable(false);
        txtTitle.setColumns(20);
        txtTitle.setFont(txtTitle.getFont().deriveFont(txtTitle.getFont().getSize()+8f));
        txtTitle.setLineWrap(true);
        txtTitle.setRows(1);
        txtTitle.setText(org.openide.util.NbBundle.getMessage(ModuleCardPanel.class, "ModuleCardPanel.txtTitle.text")); // NOI18N
        txtTitle.setWrapStyleWord(true);
        txtTitle.setAlignmentX(0.0F);
        txtTitle.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(24, 12, 16, 12);
        cardContent.add(txtTitle, gridBagConstraints);

        txtSubtitle.setEditable(false);
        txtSubtitle.setColumns(20);
        txtSubtitle.setFont(txtSubtitle.getFont().deriveFont(txtSubtitle.getFont().getSize()+4f));
        txtSubtitle.setForeground(new java.awt.Color(80, 80, 80));
        txtSubtitle.setLineWrap(true);
        txtSubtitle.setRows(1);
        txtSubtitle.setText(org.openide.util.NbBundle.getMessage(ModuleCardPanel.class, "ModuleCardPanel.txtSubtitle.text")); // NOI18N
        txtSubtitle.setToolTipText(org.openide.util.NbBundle.getMessage(ModuleCardPanel.class, "ModuleCardPanel.txtSubtitle.toolTipText")); // NOI18N
        txtSubtitle.setWrapStyleWord(true);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(0, 12, 16, 12);
        cardContent.add(txtSubtitle, gridBagConstraints);

        txtDescription.setEditable(false);
        txtDescription.setColumns(20);
        txtDescription.setForeground(new java.awt.Color(80, 80, 80));
        txtDescription.setLineWrap(true);
        txtDescription.setRows(1);
        txtDescription.setText(org.openide.util.NbBundle.getMessage(ModuleCardPanel.class, "ModuleCardPanel.txtDescription.text")); // NOI18N
        txtDescription.setToolTipText(org.openide.util.NbBundle.getMessage(ModuleCardPanel.class, "ModuleCardPanel.txtDescription.toolTipText")); // NOI18N
        txtDescription.setWrapStyleWord(true);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(0, 12, 16, 12);
        cardContent.add(txtDescription, gridBagConstraints);

        add(cardContent);
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel cardContent;
    private javax.swing.JLabel cardImage;
    private javax.swing.JTextArea txtDescription;
    private javax.swing.JTextArea txtSubtitle;
    private javax.swing.JTextArea txtTitle;
    // End of variables declaration//GEN-END:variables

    void setFeature(NetBeansPlatformFeature feature) {
        this.feature = feature;
        if (feature.getImage() != null) {
            ImageIcon featureImageIcon = ImageUtilities.loadImageIcon(feature.getImage(), false);
            cardImage.setIcon(featureImageIcon);
        }
        txtTitle.setText(feature.getTitle());
        txtSubtitle.setText(feature.getSubtitle());
        txtDescription.setText(feature.getDescription());
    }
}