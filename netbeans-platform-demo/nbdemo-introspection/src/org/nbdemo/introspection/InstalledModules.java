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
package org.nbdemo.introspection;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import org.nbdemo.introspection.documentation.AllModulesDocumentation;
import org.nbdemo.introspection.documentation.ModuleDocumentationHandler;
import org.openide.modules.ModuleInfo;
import org.openide.util.Lookup;

/**
 *
 */
public class InstalledModules {

    private static InstalledModules instance;

    public static synchronized InstalledModules getInstance() {
        if (instance == null) {
            instance = new InstalledModules();
        }
        return instance;
    }

    private List<ModuleInfo> sortedByDisplayName;
    private List<ModuleInfo> sortedByCodeNameBase;
    private Map<String, List<ModuleInfo>> modulesByCodeNameBase;
    private AllModulesDocumentation modulesDocumentation;

    private InstalledModules() {
        Collection<? extends ModuleInfo> allModules = Lookup.getDefault().lookupAll(ModuleInfo.class);
        sortedByDisplayName = Collections.unmodifiableList(allModules.stream().sorted((a, b) -> {
            return a.getDisplayName().compareTo(b.getDisplayName());
        }).collect(Collectors.toList()));
        sortedByCodeNameBase = Collections.unmodifiableList(allModules.stream().sorted((a, b) -> {
            return a.getCodeNameBase().compareTo(b.getCodeNameBase());
        }).collect(Collectors.toList()));
        modulesByCodeNameBase = allModules.stream().collect(Collectors.groupingBy( (m) -> { return m.getCodeNameBase(); }));
        modulesDocumentation = ModuleDocumentationHandler.getDefault().getDocumentation();
        Logger.getLogger(InstalledModules.class.getName()).log(Level.INFO, "Modules: " + allModules.size());
    }

    public List<ModuleInfo> getSortedByDisplayName() {
        return sortedByDisplayName;
    }

    public List<ModuleInfo> getSortedByCodeNameBase() {
        return sortedByCodeNameBase;
    }

    public String[] documentationLinks(ModuleInfo moduleInfo) {
        // TODO: Add documentation links
        return new String[0];
    }
    
    public ModuleInfo getModuleByCodeNameBase(String codeNameBase) {
        List<ModuleInfo> modules = modulesByCodeNameBase.get(codeNameBase);
        return modules == null ? null : modules.size() > 0 ? modules.get(0) : null;
    }

}
