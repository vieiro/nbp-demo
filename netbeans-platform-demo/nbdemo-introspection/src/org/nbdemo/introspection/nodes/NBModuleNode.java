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
package org.nbdemo.introspection.nodes;

import org.nbdemo.introspection.gui.module.ModuleInfoContainer;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.util.Set;
import javax.swing.AbstractAction;
import javax.swing.Action;
import org.nbdemo.introspection.gui.module.NBDemoModuleTopComponent;
import org.nbdemo.introspection.nodes.documentation.NBModuleDocumentationNode;
import org.nbdemo.introspection.nodes.dependencies.NBModuleDependenciesNode;
import org.openide.cookies.OpenCookie;
import org.openide.modules.ModuleInfo;
import org.openide.nodes.AbstractNode;
import org.openide.nodes.Children;
import org.openide.nodes.Node;
import org.openide.util.ImageUtilities;
import org.openide.util.actions.SystemAction;
import org.openide.util.lookup.AbstractLookup;
import org.openide.util.lookup.InstanceContent;
import org.openide.windows.TopComponent;
import org.openide.windows.WindowManager;

/**
 * A Node that represents a NetBeans Module.
 *
 * @author Antonio vieiro@apache.org
 */
public class NBModuleNode extends AbstractNode {

    private static enum NBModuleChildrenTypes {
        DEPENDENCIES,
        DOCUMENTATION,
    }

    private static class NBModuleNodeChildren extends Children.Keys<NBModuleChildrenTypes> {

        private ModuleInfo info;

        NBModuleNodeChildren(ModuleInfo info) {
            super();
            this.info = info;
        }

        @Override
        protected void addNotify() {
            super.addNotify();
            setKeys(NBModuleChildrenTypes.values());
        }

        @Override
        protected void removeNotify() {
            super.removeNotify();
            setKeys(new NBModuleChildrenTypes[0]);
        }

        @Override
        protected Node[] createNodes(NBModuleChildrenTypes t) {
            switch (t) {
                case DEPENDENCIES:
                    return new Node[]{new NBModuleDependenciesNode(info)};
                case DOCUMENTATION:
                    return new Node[]{new NBModuleDocumentationNode(info)};
            }
            return new Node[0];
        }

    }
    

    private ModuleInfo moduleInfo;
    private InstanceContent instanceContent;
    private AbstractAction openAction;

    public NBModuleNode(ModuleInfo moduleInfo) {
        this(moduleInfo, new InstanceContent());
    }

    private NBModuleNode(ModuleInfo info, InstanceContent ic) {
        super(new NBModuleNodeChildren(info), new AbstractLookup(ic));
        this.moduleInfo = info;
        this.instanceContent = ic;

        OpenCookie openCookie = new OpenCookie() {
            @Override
            public void open() {
                NBModuleNode.this.open();
            }
        };
        
        instanceContent.add(openCookie);
        
        this.openAction = new AbstractAction("Open") // TODO: I18N
        {
            @Override
            public void actionPerformed(ActionEvent ae) {
                open();
            }
        };
    }
    
    private void open() {
        
        TopComponent alreadyOpenTopComponent = null;
        Set<TopComponent> openTopComponents = WindowManager.getDefault().getRegistry().getOpened();
        for (TopComponent tc : openTopComponents) {
            ModuleInfoContainer container = tc.getLookup().lookup(ModuleInfoContainer.class);
            if (container != null && container.getModuleInfo() == moduleInfo) {
                alreadyOpenTopComponent = tc;
                break;
            }
        }
        
        if (alreadyOpenTopComponent != null) {
            alreadyOpenTopComponent.requestActive();
        } else {
            NBDemoModuleTopComponent newTopComponent = new NBDemoModuleTopComponent();
            newTopComponent.setModuleInfo(moduleInfo);
            newTopComponent.open();
            newTopComponent.requestActive();
        }
    }

    @Override
    public Action getPreferredAction() {
        return openAction;
    }

    @Override
    public Action[] getActions(boolean context) {
        return new Action[] { openAction };
    }

    @Override
    public String getDisplayName() {
        return moduleInfo.getDisplayName();
    }

    
    @Override
    public Image getOpenedIcon(int type) {
        return ImageUtilities.loadImage("org/nbdemo/introspection/resources/module.png");
    }

    @Override
    public Image getIcon(int type) {
        return ImageUtilities.loadImage("org/nbdemo/introspection/resources/module.png");
    }

}
