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
package org.nbdemo.introspection.gui.module;

import org.openide.modules.ModuleInfo;

/**
 * Objects of type "ModuleInfo" cannot be put in the Lookup, otherwise
 * NetBeans would get confused. 
 * This class wraps a "ModuleInfo" so that NetBeans is not confused.
 * "ModuleInfoContainer" is stored in the lookup of NBDemoModuleTopComponent
 * module windows, so that the same window is always opened for a given module.
 */
public class ModuleInfoContainer {
    
    private ModuleInfo moduleInfo;

    public ModuleInfoContainer(ModuleInfo moduleInfo) {
        this.moduleInfo = moduleInfo;
    }

    public ModuleInfo getModuleInfo() {
        return moduleInfo;
    }

    public void setModuleInfo(ModuleInfo moduleInfo) {
        this.moduleInfo = moduleInfo;
    }
    
}
