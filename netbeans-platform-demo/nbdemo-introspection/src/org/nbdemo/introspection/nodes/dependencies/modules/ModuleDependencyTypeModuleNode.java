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

import java.util.Collections;
import org.nbdemo.introspection.ModuleModuleDependency;
import org.openide.modules.ModuleInfo;
import org.openide.nodes.AbstractNode;
import org.openide.nodes.Children;
import org.openide.nodes.Node;
import org.openide.util.lookup.AbstractLookup;
import org.openide.util.lookup.InstanceContent;

/**
 * ModuleDependencyTypeModuleNode represents the dependency between two modules.
 * This holds properties such as display name, code name base, version, etc.
 */
public final class ModuleDependencyTypeModuleNode extends AbstractNode {

    private static final class ModuleDepdendencyTypeModuleNodeChildren extends Children.Keys<ModuleInfo> {
        
        private ModuleModuleDependency dependency;
        private boolean direct;

        private ModuleDepdendencyTypeModuleNodeChildren(ModuleModuleDependency moduleModuleDependency, boolean direct) {
            this.dependency = moduleModuleDependency;
            this.direct = direct;
        }

        @Override
        protected void addNotify() {
            super.addNotify();
            setKeys(direct ? Collections.singletonList(dependency.getSource()) : dependency.getTargets());
        }

        @Override
        protected Node[] createNodes(ModuleInfo target) {
            return new Node[] { new ResolvedModuleModuleNode(dependency.getSource(), dependency.getDependency(), target, direct) };
        }
    }
    private ModuleModuleDependency moduleModuleDependency;
    private InstanceContent instanceContent;

    /**
     * Displays a dependency with multiple target nodes.
     * For instance, a "requires" dependency.
     * @param moduleModuleDependency The dependency.
     * @param direct true to show the dependency target node (direct), false to display the source node.
     */
    public ModuleDependencyTypeModuleNode(ModuleModuleDependency moduleModuleDependency, boolean direct) {
        this(moduleModuleDependency, direct, new InstanceContent());
    }

    private ModuleDependencyTypeModuleNode(ModuleModuleDependency moduleModuleDependency, boolean direct, InstanceContent instanceContent) {
        super(new ModuleDepdendencyTypeModuleNodeChildren(moduleModuleDependency, direct), new AbstractLookup(instanceContent));
        this.moduleModuleDependency = moduleModuleDependency;
        this.instanceContent = instanceContent;
    }

}
