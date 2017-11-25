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
import org.openide.awt.ActionID;
import org.openide.modules.ModuleInfo;
import org.openide.util.Lookup;
import org.openide.windows.TopComponent;
import org.openide.util.NbBundle.Messages;
import org.openide.util.lookup.AbstractLookup;
import org.openide.util.lookup.InstanceContent;
import org.openide.util.lookup.ProxyLookup;

/**
 * Displays a module.
 * This contains basic information about the module as well
 * as a list of dependencies.
 */
@TopComponent.Description(
        preferredID = "NBDemoModuleTopComponent",
        //iconBase="SET/PATH/TO/ICON/HERE", 
        persistenceType = TopComponent.PERSISTENCE_NEVER
)
@TopComponent.Registration(mode = "editor", openAtStartup = false)
@ActionID(category = "Window", id = "org.nbdemo.introspection.gui.module.NBDemoModuleTopComponent")
// @ActionReference(path = "Menu/Window" /*, position = 333 */)
@TopComponent.OpenActionRegistration(
        displayName = "#CTL_NBDemoModuleAction",
        preferredID = "NBDemoModuleTopComponent"
)
@Messages({
    "CTL_NBDemoModuleAction=NBDemoModule",
    "CTL_NBDemoModuleTopComponent=NBDemoModule Window",
    "HINT_NBDemoModuleTopComponent=Module detail"
})
public final class NBDemoModuleTopComponent extends TopComponent {
    
    private ModuleInfoContainer moduleInfoContainer;
    private InstanceContent instanceContent;
    private DirectDependenciesPanel directDependenciesPanel;
    private InverseDependenciesPanel inverseDependenciesPanel;

    public NBDemoModuleTopComponent() {
        initComponents();
        setName(Bundle.CTL_NBDemoModuleTopComponent());
        setToolTipText(Bundle.HINT_NBDemoModuleTopComponent());
        instanceContent = new InstanceContent();
        
        
        directDependenciesPanel = new DirectDependenciesPanel();
        pnlDependencyTypeModule.add(directDependenciesPanel, BorderLayout.CENTER);
        
        inverseDependenciesPanel = new InverseDependenciesPanel();
        pnlDependencyTypeModuleInverted.add(inverseDependenciesPanel, BorderLayout.CENTER);
        
        Lookup lookup = new ProxyLookup(
                new AbstractLookup(instanceContent),
                directDependenciesPanel.getLookup(),
                inverseDependenciesPanel.getLookup()
        );
        associateLookup(lookup);
        
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
        } else {
            txtDisplayName.setText(moduleInfo.getDisplayName());
            txtCodeNameBase.setText(moduleInfo.getCodeNameBase());
            txtVersion.setText(moduleInfo.getSpecificationVersion().toString());
            txtImplementationVersion.setText(moduleInfo.getImplementationVersion());
            setDisplayName(moduleInfo.getDisplayName());
            setToolTipText(moduleInfo.getDisplayName());
            instanceContent.add(moduleInfoContainer);
        }
        directDependenciesPanel.setModuleInfo(moduleInfo);
        inverseDependenciesPanel.setModuleInfo(moduleInfo);
    }

    
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        pnlNames = new javax.swing.JPanel();
        lblDisplayName = new javax.swing.JLabel();
        txtDisplayName = new javax.swing.JTextField();
        lblCodeNameBase = new javax.swing.JLabel();
        txtCodeNameBase = new javax.swing.JTextField();
        pnlDetails = new javax.swing.JPanel();
        lblVersion = new javax.swing.JLabel();
        txtVersion = new javax.swing.JTextField();
        lblImplementationVersion = new javax.swing.JLabel();
        txtImplementationVersion = new javax.swing.JTextField();
        tabDependencies = new javax.swing.JTabbedPane();
        pnlDependencyTypeModule = new javax.swing.JPanel();
        pnlDependencyTypeModuleInverted = new javax.swing.JPanel();

        setLayout(new java.awt.GridBagLayout());

        pnlNames.setLayout(new java.awt.GridBagLayout());

        org.openide.awt.Mnemonics.setLocalizedText(lblDisplayName, org.openide.util.NbBundle.getMessage(NBDemoModuleTopComponent.class, "NBDemoModuleTopComponent.lblDisplayName.text")); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_END;
        gridBagConstraints.insets = new java.awt.Insets(8, 8, 8, 8);
        pnlNames.add(lblDisplayName, gridBagConstraints);

        txtDisplayName.setEditable(false);
        txtDisplayName.setText(org.openide.util.NbBundle.getMessage(NBDemoModuleTopComponent.class, "NBDemoModuleTopComponent.txtDisplayName.text")); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.weightx = 10.0;
        gridBagConstraints.insets = new java.awt.Insets(8, 8, 8, 8);
        pnlNames.add(txtDisplayName, gridBagConstraints);

        org.openide.awt.Mnemonics.setLocalizedText(lblCodeNameBase, org.openide.util.NbBundle.getMessage(NBDemoModuleTopComponent.class, "NBDemoModuleTopComponent.lblCodeNameBase.text")); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_END;
        gridBagConstraints.insets = new java.awt.Insets(8, 8, 8, 8);
        pnlNames.add(lblCodeNameBase, gridBagConstraints);

        txtCodeNameBase.setEditable(false);
        txtCodeNameBase.setText(org.openide.util.NbBundle.getMessage(NBDemoModuleTopComponent.class, "NBDemoModuleTopComponent.txtCodeNameBase.text")); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.weightx = 5.0;
        gridBagConstraints.insets = new java.awt.Insets(8, 8, 8, 8);
        pnlNames.add(txtCodeNameBase, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.FIRST_LINE_START;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        add(pnlNames, gridBagConstraints);

        pnlDetails.setLayout(new java.awt.GridBagLayout());

        org.openide.awt.Mnemonics.setLocalizedText(lblVersion, org.openide.util.NbBundle.getMessage(NBDemoModuleTopComponent.class, "NBDemoModuleTopComponent.lblVersion.text")); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_END;
        gridBagConstraints.insets = new java.awt.Insets(8, 8, 8, 8);
        pnlDetails.add(lblVersion, gridBagConstraints);

        txtVersion.setEditable(false);
        txtVersion.setText(org.openide.util.NbBundle.getMessage(NBDemoModuleTopComponent.class, "NBDemoModuleTopComponent.txtVersion.text")); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.BASELINE_LEADING;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(8, 8, 8, 8);
        pnlDetails.add(txtVersion, gridBagConstraints);

        org.openide.awt.Mnemonics.setLocalizedText(lblImplementationVersion, org.openide.util.NbBundle.getMessage(NBDemoModuleTopComponent.class, "NBDemoModuleTopComponent.lblImplementationVersion.text")); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_END;
        gridBagConstraints.insets = new java.awt.Insets(8, 8, 8, 8);
        pnlDetails.add(lblImplementationVersion, gridBagConstraints);

        txtImplementationVersion.setEditable(false);
        txtImplementationVersion.setText(org.openide.util.NbBundle.getMessage(NBDemoModuleTopComponent.class, "NBDemoModuleTopComponent.txtImplementationVersion.text")); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.BASELINE;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(8, 8, 8, 8);
        pnlDetails.add(txtImplementationVersion, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.FIRST_LINE_START;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        add(pnlDetails, gridBagConstraints);

        pnlDependencyTypeModule.setLayout(new java.awt.BorderLayout());
        tabDependencies.addTab(org.openide.util.NbBundle.getMessage(NBDemoModuleTopComponent.class, "NBDemoModuleTopComponent.pnlDependencyTypeModule.TabConstraints.tabTitle"), pnlDependencyTypeModule); // NOI18N

        pnlDependencyTypeModuleInverted.setLayout(new java.awt.BorderLayout());
        tabDependencies.addTab(org.openide.util.NbBundle.getMessage(NBDemoModuleTopComponent.class, "NBDemoModuleTopComponent.pnlDependencyTypeModuleInverted.TabConstraints.tabTitle"), pnlDependencyTypeModuleInverted); // NOI18N

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.FIRST_LINE_START;
        gridBagConstraints.weightx = 10.0;
        gridBagConstraints.weighty = 10.0;
        add(tabDependencies, gridBagConstraints);
        tabDependencies.getAccessibleContext().setAccessibleName(org.openide.util.NbBundle.getMessage(NBDemoModuleTopComponent.class, "NBDemoModuleTopComponent.tabDependencies.AccessibleContext.accessibleName")); // NOI18N
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel lblCodeNameBase;
    private javax.swing.JLabel lblDisplayName;
    private javax.swing.JLabel lblImplementationVersion;
    private javax.swing.JLabel lblVersion;
    private javax.swing.JPanel pnlDependencyTypeModule;
    private javax.swing.JPanel pnlDependencyTypeModuleInverted;
    private javax.swing.JPanel pnlDetails;
    private javax.swing.JPanel pnlNames;
    private javax.swing.JTabbedPane tabDependencies;
    private javax.swing.JTextField txtCodeNameBase;
    private javax.swing.JTextField txtDisplayName;
    private javax.swing.JTextField txtImplementationVersion;
    private javax.swing.JTextField txtVersion;
    // End of variables declaration//GEN-END:variables

}
