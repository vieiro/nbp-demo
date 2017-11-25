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
import java.lang.reflect.InvocationTargetException;
import org.nbdemo.introspection.ModuleInfoProvider;
import org.nbdemo.introspection.nodes.ModuleProperties;
import org.openide.modules.Dependency;
import org.openide.modules.ModuleInfo;
import org.openide.nodes.AbstractNode;
import org.openide.nodes.Children;
import org.openide.nodes.Node;
import org.openide.nodes.PropertySupport;
import org.openide.util.ImageUtilities;
import org.openide.util.lookup.AbstractLookup;
import org.openide.util.lookup.InstanceContent;

/**
 * ModuleDependencyTypeModuleNode represents the dependency between two modules.
 * This holds properties such as display name, code name base, version, etc.
 */
public final class ResolvedModuleModuleNode extends AbstractNode implements ModuleInfoProvider {

    private ModuleInfo source;
    private ModuleInfo target;
    private Dependency dependency;
    private InstanceContent instanceContent;
    private ModuleProperties properties;

    public ResolvedModuleModuleNode(ModuleInfo source, Dependency dependency, ModuleInfo target) {
        this(source, dependency, target, new InstanceContent());
    }

    private ResolvedModuleModuleNode(ModuleInfo source, Dependency dependency, ModuleInfo target, InstanceContent instanceContent) {
        super(Children.LEAF, new AbstractLookup(instanceContent));
        this.source = source;
        this.dependency = dependency;
        this.target = target;
        this.properties = new ModuleProperties(this);
    }

    @Override
    public ModuleInfo getModuleInfo() {
        return target;
    }

    @Override
    public String getDisplayName() {
        return target.getDisplayName();
    }

    @Override
    public Image getOpenedIcon(int type) {
        return ImageUtilities.loadImage("org/nbdemo/introspection/resources/module.png");
    }

    @Override
    public Image getIcon(int type) {
        return ImageUtilities.loadImage("org/nbdemo/introspection/resources/module.png");
    }

    @Override
    public Node.PropertySet[] getPropertySets() {
        return properties.getPropertySets();
    }

}
