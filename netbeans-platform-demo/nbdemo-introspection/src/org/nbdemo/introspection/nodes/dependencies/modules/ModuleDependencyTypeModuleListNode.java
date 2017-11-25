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
package org.nbdemo.introspection.nodes.dependencies.modules;

import java.awt.Image;
import java.util.List;
import org.nbdemo.introspection.InstalledModules;
import org.nbdemo.introspection.ModuleModuleDependency;
import org.nbdemo.introspection.nodes.ModuleNode;
import org.openide.modules.Dependency;
import org.openide.modules.ModuleInfo;
import org.openide.nodes.AbstractNode;
import org.openide.nodes.ChildFactory;
import org.openide.nodes.Children;
import org.openide.nodes.Node;
import org.openide.util.ImageUtilities;
import org.openide.util.NbBundle;
import org.openide.util.lookup.AbstractLookup;
import org.openide.util.lookup.InstanceContent;

/**
 * ModuleDependencyTypeModuleListNode holds all the module dependencies for a
 * module.
 *
 * @see Dependency
 * @author Antonio Vieiro (antonio@vieiro.net)
 */
public class ModuleDependencyTypeModuleListNode extends AbstractNode {

    private static class NBModuleModuleDependenciesNodeChildren extends ChildFactory<ModuleModuleDependency> {

        private ModuleInfo moduleInfo;

        NBModuleModuleDependenciesNodeChildren(ModuleInfo moduleInfo) {
            this.moduleInfo = moduleInfo;
        }

        @Override
        protected boolean createKeys(List<ModuleModuleDependency> toPopulate) {
            toPopulate.addAll(InstalledModules.moduleDependencies(moduleInfo));
            return true;
        }

        @Override
        protected Node[] createNodesForKey(ModuleModuleDependency key) {
            int ntargets = key.getTargets().size();

            if (ntargets == 0) {
                // No targets, no nodes.
                return new Node[0];
            } else if (ntargets == 1) {
                // In case we have a single node then just add it
                return new Node[]{new ResolvedModuleModuleNode(moduleInfo, key.getDependency(), key.getTargets().get(0))};
            }
            return new Node[]{new ModuleDependencyTypeModuleNode(key)};
        }
    }

    private ModuleInfo moduleInfo;
    private InstanceContent instanceContent;

    public ModuleDependencyTypeModuleListNode(ModuleInfo moduleInfo) {
        this(moduleInfo, new InstanceContent());
    }

    private ModuleDependencyTypeModuleListNode(ModuleInfo moduleInfo, InstanceContent instanceContent) {
        super(Children.create(new NBModuleModuleDependenciesNodeChildren(moduleInfo), true), new AbstractLookup(instanceContent));
        this.moduleInfo = moduleInfo;
        this.instanceContent = instanceContent;
    }

    @Override
    public String getDisplayName() {
        return "Modules";
    }

    @Override
    public Image getOpenedIcon(int type) {
        return ImageUtilities.loadImage("org/nbdemo/introspection/resources/Hyperlink.png");
    }

    @Override
    public Image getIcon(int type) {
        return ImageUtilities.loadImage("org/nbdemo/introspection/resources/Hyperlink.png");
    }
}
