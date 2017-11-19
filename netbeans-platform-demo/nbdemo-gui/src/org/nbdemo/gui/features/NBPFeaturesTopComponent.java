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

import java.awt.Point;
import java.awt.Rectangle;
import java.io.IOException;
import java.util.ArrayList;
import org.nbdemo.gui.features.model.NetBeansPlatformFeature;
import org.nbdemo.gui.features.model.NetBeansPlatformFeatureList;
import org.nbdemo.gui.features.model.NetBeansPlatformFeatureListIO;
import org.netbeans.api.settings.ConvertAsProperties;
import org.openide.awt.ActionID;
import org.openide.awt.ActionReference;
import org.openide.util.Exceptions;
import org.openide.windows.TopComponent;
import org.openide.util.NbBundle.Messages;

/**
 * Top component which displays something.
 */
@ConvertAsProperties(
        dtd = "-//org.nbdemo.gui.features//NBPFeatures//EN",
        autostore = false
)
@TopComponent.Description(
        preferredID = "NBPFeaturesTopComponent",
        //iconBase="SET/PATH/TO/ICON/HERE", 
        persistenceType = TopComponent.PERSISTENCE_ALWAYS
)
@TopComponent.Registration(mode = "editor", openAtStartup = true)
@ActionID(category = "Window", id = "org.nbdemo.gui.features.NBPFeaturesTopComponent")
@ActionReference(path = "Menu/Window" /*, position = 333 */)
@TopComponent.OpenActionRegistration(
        displayName = "#CTL_NBPFeaturesAction",
        preferredID = "NBPFeaturesTopComponent"
)
@Messages({
    "CTL_NBPFeaturesAction=NetBeans Platform Features",
    "CTL_NBPFeaturesTopComponent=NetBeans Platform Features",
    "HINT_NBPFeaturesTopComponent=Main features of the NetBeans Platform"
})
public final class NBPFeaturesTopComponent extends TopComponent {

    private NetBeansPlatformFeatureList featureList;
    private ArrayList<ModuleCardPanel> cards;

    public NBPFeaturesTopComponent() {
        initComponents();
        setName(Bundle.CTL_NBPFeaturesTopComponent());
        setToolTipText(Bundle.HINT_NBPFeaturesTopComponent());
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        scrollPane = new javax.swing.JScrollPane();
        cardContainer = new javax.swing.JPanel();

        setLayout(new java.awt.BorderLayout());

        scrollPane.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        scrollPane.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        cardContainer.setPreferredSize(new java.awt.Dimension(480, 1024));
        scrollPane.setViewportView(cardContainer);

        add(scrollPane, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel cardContainer;
    private javax.swing.JScrollPane scrollPane;
    // End of variables declaration//GEN-END:variables
    @Override
    public void componentOpened() {

        if (featureList == null) {
            try {
                featureList = NetBeansPlatformFeatureListIO.read();
            } catch (IOException ex) {
                Exceptions.printStackTrace(ex);
                featureList = new NetBeansPlatformFeatureList();
            }
            cards = new ArrayList<ModuleCardPanel>();
            for (NetBeansPlatformFeature feature : featureList.getFeatures()) {
                ModuleCardPanel card = new ModuleCardPanel();
                cards.add(card);
                card.setFeature(feature);
                cardContainer.add(card);
            }
        }
        scrollPane.scrollRectToVisible(new Rectangle(new Point(0, 0)));
    }

    @Override
    public void componentClosed() {
        // TODO add custom code on component closing
    }

    void writeProperties(java.util.Properties p) {
        // better to version settings since initial version as advocated at
        // http://wiki.apidesign.org/wiki/PropertyFiles
        p.setProperty("version", "1.0");
        // TODO store your settings
    }

    void readProperties(java.util.Properties p) {
        String version = p.getProperty("version");
        // TODO read your settings according to their version
    }
}
