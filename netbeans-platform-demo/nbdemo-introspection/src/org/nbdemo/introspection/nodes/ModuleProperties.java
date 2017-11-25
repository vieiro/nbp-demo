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
 */package org.nbdemo.introspection.nodes;

import java.lang.reflect.InvocationTargetException;
import org.nbdemo.introspection.ModuleInfoProvider;
import org.openide.nodes.Node;
import org.openide.nodes.PropertySupport;

/**
 *
 * @author Antonio Vieiro (antonio@vieiro.net)
 */
public class ModuleProperties {

    public static final String PROP_CODENAMEBASE = "code-name-base";
    public static final String PROP_APIVERSION = "api-version";
    public static final String PROP_IMPLVERSION = "impl-version";
    
    private ModuleInfoProvider provider;
    
    public ModuleProperties(ModuleInfoProvider provider) {
        this.provider = provider;
    }

    public Node.PropertySet[] getPropertySets() {

        Node.Property code_name_base = new PropertySupport.ReadOnly<String>(PROP_CODENAMEBASE, String.class, "Code name base", "Code name base") {
            @Override
            public String getValue() throws IllegalAccessException, InvocationTargetException {
                return provider.getModuleInfo().getCodeNameBase();
            }
        };

        Node.Property api_version = new PropertySupport.ReadOnly<String>(PROP_APIVERSION, String.class, "API Version", "Specification version of the module.") {
            @Override
            public String getValue() throws IllegalAccessException, InvocationTargetException {
                return provider.getModuleInfo().getSpecificationVersion().toString();
            }
        };

        Node.Property impl_version = new PropertySupport.ReadOnly<String>(PROP_IMPLVERSION, String.class, "Impl. Version", "Implementation version of the module.") {
            @Override
            public String getValue() throws IllegalAccessException, InvocationTargetException {
                return provider.getModuleInfo().getImplementationVersion();
            }
        };
        Node.PropertySet set = new Node.PropertySet() {
            @Override
            public Node.Property<?>[] getProperties() {
                return new Node.Property<?>[]{code_name_base, api_version, impl_version};
            }
        };

        return new Node.PropertySet[]{set};
    }
}
