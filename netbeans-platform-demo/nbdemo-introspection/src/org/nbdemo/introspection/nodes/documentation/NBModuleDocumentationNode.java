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
package org.nbdemo.introspection.nodes.documentation;

import java.awt.Image;
import org.nbdemo.introspection.InstalledModules;
import org.openide.modules.ModuleInfo;
import org.openide.nodes.AbstractNode;
import org.openide.nodes.Children;
import org.openide.nodes.Node;
import org.openide.util.ImageUtilities;
import org.openide.util.NbBundle;
import org.openide.util.lookup.AbstractLookup;
import org.openide.util.lookup.InstanceContent;

/**
 *
 */
public class NBModuleDocumentationNode extends AbstractNode {

    private static class NBModuleDocumentationChildren extends Children.Keys<String> {

        private ModuleInfo moduleInfo;

        public NBModuleDocumentationChildren(ModuleInfo info) {
            this.moduleInfo = info;
        }

        @Override
        protected void addNotify() {
            super.addNotify();
            setKeys(InstalledModules.getInstance().documentationLinks(moduleInfo));
        }

        @Override
        protected Node[] createNodes(String key) {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

    }

    private ModuleInfo moduleInfo;
    private InstanceContent instanceContent;

    public NBModuleDocumentationNode(ModuleInfo info) {
        this(info, new InstanceContent());
    }

    private NBModuleDocumentationNode(ModuleInfo info, InstanceContent instanceContent) {
        super(new NBModuleDocumentationChildren(info), new AbstractLookup(instanceContent));
        this.moduleInfo = info;
        this.instanceContent = instanceContent;
    }

    @Override
    public String getDisplayName() {
        return NbBundle.getMessage(getClass(), "DOCUMENTATION"); // NOI18N
    }
    @Override
    public Image getOpenedIcon(int type) {
        return ImageUtilities.loadImage("org/nbdemo/introspection/resources/javadoc_open.png");
    }

    @Override
    public Image getIcon(int type) {
        return ImageUtilities.loadImage("org/nbdemo/introspection/resources/javadoc_open.png");
    }    
    
}
