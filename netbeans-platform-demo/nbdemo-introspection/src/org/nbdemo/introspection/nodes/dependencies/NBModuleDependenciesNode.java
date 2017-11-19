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
 *
 * @author Antonio vieiro@apache.org
 */
public class NBModuleDependenciesNode extends AbstractNode {

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
            List<Dependency> dependencies = moduleInfo.getDependencies().stream().filter((p) -> {
                return p.getType() == dependencyType.getType();
            }).collect(Collectors.toList());

            return dependencies.isEmpty() ? new Node[0]
                    : new Node[]{new NBModuleDependenciesByTypeNode(dependencyType, dependencies)};
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
