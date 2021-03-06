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
package org.nbdemo.introspection.documentation;

import java.util.Map;
import java.util.TreeMap;

/**
 * Non API-Public class that maps strings (module code name base) to module documentation.
 * This is work in progress.
 * @see ModuleDocumentation
 */
public class AllModulesDocumentation {
    private Map<String, ModuleDocumentation> modules;

    public AllModulesDocumentation() {
        modules = new TreeMap<>();
    }
    public Map<String, ModuleDocumentation> getModules() {
        return modules;
    }

    public void setModules(Map<String, ModuleDocumentation> modules) {
        this.modules = modules;
    }
    
}
