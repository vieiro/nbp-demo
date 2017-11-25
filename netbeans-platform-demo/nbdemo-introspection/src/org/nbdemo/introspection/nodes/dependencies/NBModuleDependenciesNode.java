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
package org.nbdemo.introspection.nodes.dependencies;

import java.awt.Image;
import java.util.List;
import java.util.stream.Collectors;
import org.nbdemo.introspection.ModuleDependencyType;
import org.nbdemo.introspection.nodes.dependencies.java.ModuleDependencyTypeJavaNode;
import org.nbdemo.introspection.nodes.dependencies.modules.ModuleDependencyTypeModuleNode;
import org.nbdemo.introspection.nodes.dependencies.modules.ModuleDependencyTypeModuleListNode;
import org.openide.modules.Dependency;
import org.openide.modules.ModuleInfo;
import org.openide.nodes.AbstractNode;
import org.openide.nodes.Children;
import org.openide.nodes.Node;
import org.openide.util.ImageUtilities;
import org.openide.util.NbBundle;
import org.openide.util.lookup.AbstractLookup;
import org.openide.util.lookup.InstanceContent;

/**
 * List dependencies of a given module.
 */
public class NBModuleDependenciesNode extends AbstractNode {

    /**
     * Dependencies grouped by dependency type.
     */
    private static class NBModuleDependenciesChildren extends Children.Keys<ModuleDependencyType> {

        private ModuleInfo moduleInfo;

        NBModuleDependenciesChildren(ModuleInfo info) {
            this.moduleInfo = info;
        }

        @Override
        protected void addNotify() {
            super.addNotify();
            setKeys(ModuleDependencyType.TYPES_SORTED_BY_NAME);
        }

        @Override
        protected Node[] createNodes(ModuleDependencyType dependencyType) {

            if (dependencyType == ModuleDependencyType.MODULE) {
                return new Node[]{new ModuleDependencyTypeModuleListNode(moduleInfo)};
            }

            if (dependencyType == ModuleDependencyType.JAVA) {
                List<ModuleDependencyTypeJavaNode> javaNodes = moduleInfo.getDependencies().stream().filter((p) -> {
                    return p.getType() == Dependency.TYPE_JAVA;
                }).sorted((d1, d2) -> {
                    return d1.getVersion().compareTo(d2.getVersion());
                }).map((d) -> {
                    return new ModuleDependencyTypeJavaNode(d);
                }).collect(Collectors.toList());
                return javaNodes.toArray(new ModuleDependencyTypeJavaNode[javaNodes.size()]);
            }
            return new Node[0];
        }
    }
    private ModuleInfo moduleInfo;
    private InstanceContent instanceContent;

    public NBModuleDependenciesNode(ModuleInfo info) {
        this(info, new InstanceContent());
    }

    private NBModuleDependenciesNode(ModuleInfo info, InstanceContent ic) {
        super(new NBModuleDependenciesChildren(info), new AbstractLookup(ic));
        this.moduleInfo = info;
        this.instanceContent = ic;
    }

    @Override
    public String getDisplayName() {
        return NbBundle.getMessage(getClass(), "DEPENDENCIES"); // NOI18N
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
