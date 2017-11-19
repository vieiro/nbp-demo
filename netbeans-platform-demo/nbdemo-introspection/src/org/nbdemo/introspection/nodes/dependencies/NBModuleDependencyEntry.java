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
import org.openide.modules.Dependency;
import org.openide.nodes.AbstractNode;
import org.openide.nodes.Children;
import org.openide.util.ImageUtilities;
import org.openide.util.lookup.AbstractLookup;
import org.openide.util.lookup.InstanceContent;

/**
 *
 */
public class NBModuleDependencyEntry extends AbstractNode {

    private Dependency dependency;
    private InstanceContent instanceContent;
    
    public NBModuleDependencyEntry(Dependency dependency) {
        this(dependency, new InstanceContent());
    }
    
    private NBModuleDependencyEntry(Dependency dependency, InstanceContent instanceContent) {
        super(Children.LEAF, new AbstractLookup(instanceContent));
        this.dependency = dependency;
        this.instanceContent = instanceContent;
    }

    @Override
    public String getDisplayName() {
        return dependency.getName() + " " + dependency.getVersion();
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
