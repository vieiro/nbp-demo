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
package org.nbdemo.introspection.nodes.dependencies;

import java.awt.Image;
import java.util.List;
import org.nbdemo.introspection.ModuleDependencyType;
import org.openide.modules.Dependency;
import org.openide.modules.ModuleInfo;
import org.openide.nodes.AbstractNode;
import org.openide.nodes.Children;
import org.openide.nodes.Node;
import org.openide.util.ImageUtilities;
import org.openide.util.lookup.AbstractLookup;
import org.openide.util.lookup.InstanceContent;

/**
 *
 */
public class NBModuleDependenciesByTypeNode extends AbstractNode {

    private static class NBModuleDependenciesByTypeChildren extends Children.Keys<Dependency> {

        private List<Dependency> dependencies;

        private NBModuleDependenciesByTypeChildren(List<Dependency> dependencies) {
            this.dependencies = dependencies;
            this.dependencies.sort( (d1, d2) -> { return d1.getName().compareTo(d2.getName()); });
        }

        @Override
        protected void addNotify() {
            super.addNotify();
            setKeys(dependencies);
        }

        @Override
        protected Node[] createNodes(Dependency dependency) {
            return new Node[]{new NBModuleDependencyEntry(dependency)};
        }

    }

    private List<Dependency> dependencies;
    private ModuleDependencyType dependencyType;

    public NBModuleDependenciesByTypeNode(ModuleDependencyType dependencyType, List<Dependency> dependencies) {
        this(dependencyType, dependencies, new InstanceContent());
    }

    private NBModuleDependenciesByTypeNode(ModuleDependencyType dependencyType, List<Dependency> dependencies, InstanceContent instanceContent) {
        super(new NBModuleDependenciesByTypeChildren(dependencies), new AbstractLookup(instanceContent));
        this.dependencyType = dependencyType;
        this.dependencies = dependencies;
    }

    @Override
    public String getDisplayName() {
        return dependencyType.getDisplayName();
    }

    @Override
    public String getShortDescription() {
        return dependencyType.getShortDescription();
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
