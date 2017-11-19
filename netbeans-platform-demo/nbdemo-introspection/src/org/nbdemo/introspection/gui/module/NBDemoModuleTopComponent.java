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
package org.nbdemo.introspection.gui.module;

import java.awt.BorderLayout;
import org.nbdemo.introspection.ModuleDependencyType;
import org.nbdemo.introspection.nodes.dependencies.NBModuleDependenciesByTypeNode;
import org.nbdemo.introspection.nodes.dependencies.NBModuleModuleDependencyEntry;
import org.netbeans.api.settings.ConvertAsProperties;
import org.openide.awt.ActionID;
import org.openide.awt.ActionReference;
import org.openide.explorer.ExplorerManager;
import org.openide.explorer.ExplorerUtils;
import org.openide.explorer.view.OutlineView;
import org.openide.modules.ModuleInfo;
import org.openide.nodes.Node;
import org.openide.util.Lookup;
import org.openide.windows.TopComponent;
import org.openide.util.NbBundle.Messages;
import org.openide.util.lookup.AbstractLookup;
import org.openide.util.lookup.InstanceContent;
import org.openide.util.lookup.ProxyLookup;

/**
 * Top component which displays something.
 */
@ConvertAsProperties(
        dtd = "-//org.nbdemo.introspection.gui.module//NBDemoModule//EN",
        autostore = false
)
@TopComponent.Description(
        preferredID = "NBDemoModuleTopComponent",
        //iconBase="SET/PATH/TO/ICON/HERE", 
        persistenceType = TopComponent.PERSISTENCE_NEVER
)
@TopComponent.Registration(mode = "editor", openAtStartup = false)
@ActionID(category = "Window", id = "org.nbdemo.introspection.gui.module.NBDemoModuleTopComponent")
@ActionReference(path = "Menu/Window" /*, position = 333 */)
@TopComponent.OpenActionRegistration(
        displayName = "#CTL_NBDemoModuleAction",
        preferredID = "NBDemoModuleTopComponent"
)
@Messages({
    "CTL_NBDemoModuleAction=NBDemoModule",
    "CTL_NBDemoModuleTopComponent=NBDemoModule Window",
    "HINT_NBDemoModuleTopComponent=Module detail"
})
public final class NBDemoModuleTopComponent extends TopComponent implements ExplorerManager.Provider {
    
    private ModuleInfoContainer moduleInfoContainer;
    private InstanceContent instanceContent;
    private Node moduleModuleDependenciesNode;
    private ExplorerManager explorerManager;
    private OutlineView outlineView;

    public NBDemoModuleTopComponent() {
        initComponents();
        setName(Bundle.CTL_NBDemoModuleTopComponent());
        setToolTipText(Bundle.HINT_NBDemoModuleTopComponent());
        explorerManager = new ExplorerManager();
        instanceContent = new InstanceContent();

        Lookup compoundLookup = new ProxyLookup(
                new AbstractLookup(instanceContent),
                ExplorerUtils.createLookup(explorerManager, getActionMap()));
        associateLookup(compoundLookup);
        outlineView = new OutlineView();
        pnlDependencies.add(outlineView, BorderLayout.CENTER);
        outlineView.getOutline().setRootVisible(false);
        outlineView.getOutline().setShowGrid(true);
        // @see NBModuleModuleDependencyEntry for a list of properties.
        outlineView.setPropertyColumns(
                NBModuleModuleDependencyEntry.PROP_MODULENAME, "Module", // TODO: I18N
                NBModuleModuleDependencyEntry.PROP_CODENAMEBASE, "Code Base Name", // TODO: I18N
                NBModuleModuleDependencyEntry.PROP_SPECIFICATIONVERSION, "API Version", // TODO: I18N
                NBModuleModuleDependencyEntry.PROP_COMPARISON, "Type" // TODO: I18N
        );
    }
    
    public void setModuleInfo(ModuleInfo moduleInfo) {
        if (this.moduleInfoContainer != null) {
            instanceContent.remove(this.moduleInfoContainer);
            this.moduleInfoContainer = null;
        }
        this.moduleInfoContainer = new ModuleInfoContainer(moduleInfo);
        
        if (this.moduleInfoContainer.getModuleInfo() == null) {
            txtCodeNameBase.setText("");
            txtDisplayName.setText("");
            txtVersion.setText("");
            txtImplementationVersion.setText("");
            setToolTipText("No module");
            setDisplayName("No module");
            this.moduleModuleDependenciesNode = Node.EMPTY;
        } else {
            txtDisplayName.setText(moduleInfo.getDisplayName());
            txtCodeNameBase.setText(moduleInfo.getCodeNameBase());
            txtVersion.setText(moduleInfo.getSpecificationVersion().toString());
            txtImplementationVersion.setText(moduleInfo.getImplementationVersion());
            setDisplayName(moduleInfo.getDisplayName());
            setToolTipText(moduleInfo.getDisplayName());
            instanceContent.add(moduleInfoContainer);
            this.moduleModuleDependenciesNode = new NBModuleDependenciesByTypeNode(moduleInfo, ModuleDependencyType.MODULE);
        }
        explorerManager.setRootContext(this.moduleModuleDependenciesNode);
    }

    @Override
    public ExplorerManager getExplorerManager() {
        return explorerManager;
    }

    
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        jPanel1 = new javax.swing.JPanel();
        lblDisplayName = new javax.swing.JLabel();
        txtDisplayName = new javax.swing.JTextField();
        lblCodeNameBase = new javax.swing.JLabel();
        txtCodeNameBase = new javax.swing.JTextField();
        lblVersion = new javax.swing.JLabel();
        txtVersion = new javax.swing.JTextField();
        lblImplementationVersion = new javax.swing.JLabel();
        txtImplementationVersion = new javax.swing.JTextField();
        pnlDependencies = new javax.swing.JPanel();

        setLayout(new java.awt.GridBagLayout());

        jPanel1.setLayout(new java.awt.GridBagLayout());

        org.openide.awt.Mnemonics.setLocalizedText(lblDisplayName, org.openide.util.NbBundle.getMessage(NBDemoModuleTopComponent.class, "NBDemoModuleTopComponent.lblDisplayName.text")); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.anchor = java.awt.GridBagConstraints.BASELINE_TRAILING;
        gridBagConstraints.insets = new java.awt.Insets(8, 8, 8, 8);
        jPanel1.add(lblDisplayName, gridBagConstraints);

        txtDisplayName.setEditable(false);
        txtDisplayName.setText(org.openide.util.NbBundle.getMessage(NBDemoModuleTopComponent.class, "NBDemoModuleTopComponent.txtDisplayName.text")); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 5;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.BASELINE_LEADING;
        gridBagConstraints.weightx = 10.0;
        gridBagConstraints.insets = new java.awt.Insets(8, 8, 8, 8);
        jPanel1.add(txtDisplayName, gridBagConstraints);

        org.openide.awt.Mnemonics.setLocalizedText(lblCodeNameBase, org.openide.util.NbBundle.getMessage(NBDemoModuleTopComponent.class, "NBDemoModuleTopComponent.lblCodeNameBase.text")); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.BASELINE_TRAILING;
        gridBagConstraints.insets = new java.awt.Insets(8, 8, 8, 8);
        jPanel1.add(lblCodeNameBase, gridBagConstraints);

        txtCodeNameBase.setEditable(false);
        txtCodeNameBase.setText(org.openide.util.NbBundle.getMessage(NBDemoModuleTopComponent.class, "NBDemoModuleTopComponent.txtCodeNameBase.text")); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.BASELINE_LEADING;
        gridBagConstraints.weightx = 5.0;
        gridBagConstraints.insets = new java.awt.Insets(8, 8, 8, 8);
        jPanel1.add(txtCodeNameBase, gridBagConstraints);

        org.openide.awt.Mnemonics.setLocalizedText(lblVersion, org.openide.util.NbBundle.getMessage(NBDemoModuleTopComponent.class, "NBDemoModuleTopComponent.lblVersion.text")); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.BASELINE_TRAILING;
        gridBagConstraints.insets = new java.awt.Insets(8, 8, 8, 8);
        jPanel1.add(lblVersion, gridBagConstraints);

        txtVersion.setEditable(false);
        txtVersion.setText(org.openide.util.NbBundle.getMessage(NBDemoModuleTopComponent.class, "NBDemoModuleTopComponent.txtVersion.text")); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.BASELINE_LEADING;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(8, 8, 8, 8);
        jPanel1.add(txtVersion, gridBagConstraints);

        org.openide.awt.Mnemonics.setLocalizedText(lblImplementationVersion, org.openide.util.NbBundle.getMessage(NBDemoModuleTopComponent.class, "NBDemoModuleTopComponent.lblImplementationVersion.text")); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.insets = new java.awt.Insets(8, 8, 8, 8);
        jPanel1.add(lblImplementationVersion, gridBagConstraints);

        txtImplementationVersion.setEditable(false);
        txtImplementationVersion.setText(org.openide.util.NbBundle.getMessage(NBDemoModuleTopComponent.class, "NBDemoModuleTopComponent.txtImplementationVersion.text")); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 5;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(8, 8, 8, 8);
        jPanel1.add(txtImplementationVersion, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.FIRST_LINE_START;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        add(jPanel1, gridBagConstraints);

        pnlDependencies.setLayout(new java.awt.BorderLayout());
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.FIRST_LINE_START;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 10.0;
        add(pnlDependencies, gridBagConstraints);
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel lblCodeNameBase;
    private javax.swing.JLabel lblDisplayName;
    private javax.swing.JLabel lblImplementationVersion;
    private javax.swing.JLabel lblVersion;
    private javax.swing.JPanel pnlDependencies;
    private javax.swing.JTextField txtCodeNameBase;
    private javax.swing.JTextField txtDisplayName;
    private javax.swing.JTextField txtImplementationVersion;
    private javax.swing.JTextField txtVersion;
    // End of variables declaration//GEN-END:variables
    @Override
    public void componentOpened() {
        // TODO add custom code on component opening
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
