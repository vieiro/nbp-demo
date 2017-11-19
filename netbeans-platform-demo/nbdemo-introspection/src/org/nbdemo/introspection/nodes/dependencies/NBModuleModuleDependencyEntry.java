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
import java.lang.reflect.InvocationTargetException;
import org.nbdemo.introspection.InstalledModules;
import org.openide.modules.Dependency;
import org.openide.modules.ModuleInfo;
import org.openide.nodes.AbstractNode;
import org.openide.nodes.Children;
import org.openide.nodes.PropertySupport;
import org.openide.util.Exceptions;
import org.openide.util.ImageUtilities;
import org.openide.util.lookup.AbstractLookup;
import org.openide.util.lookup.InstanceContent;

/**
 *
 */
public class NBModuleModuleDependencyEntry extends AbstractNode {

    private Dependency dependency;
    private InstanceContent instanceContent;
    private ModuleInfo dependentModule;

    public NBModuleModuleDependencyEntry(Dependency dependency) {
        this(dependency, new InstanceContent());
    }

    private NBModuleModuleDependencyEntry(Dependency dependency, InstanceContent instanceContent) {
        super(Children.LEAF, new AbstractLookup(instanceContent));
        this.dependency = dependency;
        this.instanceContent = instanceContent;
        
        String codename = dependency.getName().replaceAll("/.*", "");
        dependentModule = InstalledModules.getInstance().getModuleByCodeNameBase(codename);

        if (dependentModule != null) {
            setModuleName(dependentModule.getDisplayName());
            setCodeNameBase(dependentModule.getCodeNameBase());
            setSpecificationVersion(dependentModule.getSpecificationVersion().toString());
            setImplementationVersion(dependentModule.getImplementationVersion());
            // TDOO: Add these to the dependency enum
            switch (dependency.getComparison()) {
                case Dependency.COMPARE_ANY:
                    setComparison("ANY");
                    break;
                case Dependency.COMPARE_IMPL:
                    setComparison("IMPL");
                    break;
                case Dependency.COMPARE_SPEC:
                    setComparison("SPEC");
                    break;
            }
        }
    }

    private String implementationVersion;

    public static final String PROP_IMPLEMENTATIONVERSION = "implementationVersion";

    public String getImplementationVersion() {
        return implementationVersion;
    }

    public void setImplementationVersion(String implementationVersion) {
        String oldImplementationVersion = this.implementationVersion;
        this.implementationVersion = implementationVersion;
        firePropertyChange(PROP_IMPLEMENTATIONVERSION, oldImplementationVersion, implementationVersion);
    }

    @Override
    public String getDisplayName() {
        return dependentModule == null
                ? dependency.getName() + " " + dependency.getVersion()
                : dependentModule.getDisplayName();
    }

    @Override
    public Image getOpenedIcon(int type) {
        return ImageUtilities.loadImage("org/nbdemo/introspection/resources/module.png");
    }

    @Override
    public Image getIcon(int type) {
        return ImageUtilities.loadImage("org/nbdemo/introspection/resources/module.png");
    }

    private String specificationVersion;

    public static final String PROP_SPECIFICATIONVERSION = "specificationVersion";

    public String getSpecificationVersion() {
        return specificationVersion;
    }

    public void setSpecificationVersion(String specificationVersion) {
        String oldSpecificationVersion = this.specificationVersion;
        this.specificationVersion = specificationVersion;
        firePropertyChange(PROP_SPECIFICATIONVERSION, oldSpecificationVersion, specificationVersion);
    }

    private String moduleName;

    public static final String PROP_MODULENAME = "moduleName";

    public String getModuleName() {
        return moduleName;
    }

    public void setModuleName(String moduleName) {
        String oldModuleName = this.moduleName;
        this.moduleName = moduleName;
        firePropertyChange(PROP_MODULENAME, oldModuleName, moduleName);
    }

    private String codeNameBase;

    public static final String PROP_CODENAMEBASE = "codeNameBase";

    public String getCodeNameBase() {
        return codeNameBase;
    }

    public void setCodeNameBase(String codeNameBase) {
        String oldCodeNameBase = this.codeNameBase;
        this.codeNameBase = codeNameBase;
        firePropertyChange(PROP_CODENAMEBASE, oldCodeNameBase, codeNameBase);
    }

    private String comparison;

    public static final String PROP_COMPARISON = "comparison";

    public String getComparison() {
        return comparison;
    }

    public void setComparison(String comparison) {
        String oldComparison = this.comparison;
        this.comparison = comparison;
        firePropertyChange(PROP_COMPARISON, oldComparison, comparison);
    }

    @Override
    public PropertySet[] getPropertySets() {
        
        try {
            Property name = new PropertySupport.Reflection(this, String.class, PROP_MODULENAME);
            Property codename = new PropertySupport.Reflection(this, String.class, PROP_CODENAMEBASE);
            Property apiVersion = new PropertySupport.Reflection(this, String.class, PROP_SPECIFICATIONVERSION);
            Property comparison = new PropertySupport.Reflection(this, String.class, PROP_COMPARISON);
            
            PropertySet ps = new PropertySet() {
                @Override
                public Property<?>[] getProperties() {
                    return new Property[] {
                        name, codename, apiVersion, comparison,
                    };
                }
            };
            
            return new PropertySet[] { ps };
            
        } catch (NoSuchMethodException ex) {
            Exceptions.printStackTrace(ex);
            return new PropertySet[0];
        }
        

    }

}
