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
package org.nbdemo.introspection.nodes.dependencies.java;

import java.awt.Image;
import org.openide.modules.Dependency;
import org.openide.nodes.AbstractNode;
import org.openide.nodes.Children;
import org.openide.util.ImageUtilities;
import org.openide.util.Lookup;

/**
 * Represents a Dependency.TYPE_JAVA
 * @author Antonio Vieiro (antonio@vieiro.net)
 */
public class ModuleDependencyTypeJavaNode extends AbstractNode {
    private Dependency java;
    
    public ModuleDependencyTypeJavaNode(Dependency javaDependency) {
        super(Children.LEAF, Lookup.EMPTY);
        this.java = javaDependency;
    }

    @Override
    public String getDisplayName() {
        return java.toString();
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
