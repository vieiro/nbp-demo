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
package org.nbdemo.introspection;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.nbdemo.introspection.documentation.AllModulesDocumentation;
import org.nbdemo.introspection.documentation.ModuleDocumentationHandler;
import org.openide.modules.Dependency;
import org.openide.modules.ModuleInfo;
import org.openide.util.Lookup;

/**
 * InstalledModules keeps information about all instaled modules in the
 * platform.
 */
public class InstalledModules {

    private static InstalledModules instance;

    /**
     * The singleton antipattern, reloaded.
     *
     * @return The single instance of this class.
     */
    public static synchronized InstalledModules getInstance() {
        if (instance == null) {
            instance = new InstalledModules();
        }
        return instance;
    }

    /**
     * Modules sorted by display name.
     */
    private List<ModuleInfo> sortedByDisplayName;
    /**
     * Modules sorted by code name base.
     */
    private List<ModuleInfo> sortedByCodeNameBase;
    /**
     * Map code-name-base to ModuleInfo. Each list should contain only a single
     * module, of course.
     */
    private Map<String, List<ModuleInfo>> modulesByCodeNameBase;
    /**
     * Module documentation.
     */
    private AllModulesDocumentation modulesDocumentation;
    /**
     * Module-module dependency graph.
     */
    private Module2ModuleAdjacencyMatrix moduleDependencies;

    private InstalledModules() {
        Collection<? extends ModuleInfo> allModules = Lookup.getDefault().lookupAll(ModuleInfo.class);
        sortedByDisplayName = Collections.unmodifiableList(allModules.stream().sorted((a, b) -> {
            return a.getDisplayName().compareTo(b.getDisplayName());
        }).collect(Collectors.toList()));
        sortedByCodeNameBase = Collections.unmodifiableList(allModules.stream().sorted((a, b) -> {
            return a.getCodeNameBase().compareTo(b.getCodeNameBase());
        }).collect(Collectors.toList()));
        modulesByCodeNameBase = allModules.stream().collect(Collectors.groupingBy((m) -> {
            return m.getCodeNameBase();
        }));
        modulesDocumentation = ModuleDocumentationHandler.getDefault().getDocumentation();
        moduleDependencies = new Module2ModuleAdjacencyMatrix(sortedByDisplayName);
    }

    /**
     * All ModuleInfo sorted by displayName.
     *
     * @return ditto
     */
    public List<ModuleInfo> getSortedByDisplayName() {
        return sortedByDisplayName;
    }

    /**
     * All ModuleInfo sorted by code name base.
     *
     * @return ditto
     */
    public List<ModuleInfo> getSortedByCodeNameBase() {
        return sortedByCodeNameBase;
    }

    /**
     * Should return documentation links (tutorials?) for a given module.
     *
     * @param moduleInfo The module
     * @return ditto
     */
    public String[] documentationLinks(ModuleInfo moduleInfo) {
        // TODO: Add documentation links
        return new String[0];
    }

    /**
     * Finds a module by codename base.
     *
     * @param codeNameBase The code name base, such as "org.openide.lookup".
     * @return The module
     */
    public ModuleInfo getModuleByCodeNameBase(String codeNameBase) {
        List<ModuleInfo> modules = modulesByCodeNameBase.get(codeNameBase);
        return modules == null ? null : modules.size() > 0 ? modules.get(0) : null;
    }

    /**
     * Returns a list of dependencies for a given module.
     *
     * @param moduleInfo The module
     * @param moduleDependencyType The type of dependency
     * @return A list of dependencies.
     */
    public static final List<Dependency> dependenciesOfType(ModuleInfo moduleInfo, ModuleDependencyType moduleDependencyType) {
        return moduleInfo.getDependencies().stream().filter((dependency) -> {
            return dependency.getType() == moduleDependencyType.getType();
        }).collect(Collectors.toList());
    }

    /**
     * Given a module returns all modules the module depends on
     * (Dependency.TYPE_MODULE)
     *
     * @param moduleInfo The module
     * @return A list of modules 'moduleInfo' depends on.
     */
    public static final List<ModuleModuleDependency> moduleDependencies(ModuleInfo moduleInfo) {
        return getInstance().moduleDependencies.dependenciesOf(moduleInfo);
    }

    public static List<ModuleModuleDependency> invertedModuleDependencies(ModuleInfo moduleInfo) {
        return getInstance().moduleDependencies.invertedDepdendenciesOf(moduleInfo);
    }

}
