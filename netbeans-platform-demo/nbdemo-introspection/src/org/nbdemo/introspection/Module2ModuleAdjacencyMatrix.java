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
 */package org.nbdemo.introspection;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import org.openide.modules.Dependency;
import org.openide.modules.ModuleInfo;

/**
 * Maintains an adjacency matrix between modules (adjacencyMatrix of type
 * Dependency.TYPE_MODULE).
 *
 * @see Dependency
 */
class Module2ModuleAdjacencyMatrix {

    /**
     * Maps module-code-name-base to a graph's node index.
     */
    private HashMap<String, Integer> codeNameBase2NodeIndex;

    /**
     * Maps a node index to a ModuleInfo
     */
    private List<ModuleInfo> graphNodes;

    /**
     * Dependency matrix.
     */
    private ModuleModuleDependency[][] adjacencyMatrix;

    /**
     * Constructs an adjacency matrix
     *
     * @param modules
     */
    public Module2ModuleAdjacencyMatrix(List<ModuleInfo> modules) {

        codeNameBase2NodeIndex = new HashMap<>();
        graphNodes = new ArrayList<>(modules);
        int nmodules = modules.size();
        adjacencyMatrix = new ModuleModuleDependency[nmodules][nmodules];

        for (int nodeIndex = 0; nodeIndex < nmodules; nodeIndex++) {
            ModuleInfo moduleInfo = modules.get(nodeIndex);
            codeNameBase2NodeIndex.put(moduleInfo.getCodeNameBase(), nodeIndex);
        }

        for (int nodeIndex = 0; nodeIndex < nmodules; nodeIndex++) {
            ModuleInfo moduleInfo = modules.get(nodeIndex);
            List<Dependency> moduleDependencies = moduleInfo.getDependencies().stream().filter((d) -> {
                return d.getType() == Dependency.TYPE_MODULE;
            }).collect(Collectors.toList());
            for (Dependency moduleDependency : moduleDependencies) {
                String otherModuleCodeNameBase = moduleDependency.getName();
                // "otherModuleCodeNameBase" is a string of the form "org.netbeans.api.progress/1".
                // we just want the codename base "org.netbeans.api.progress", and not the rest.
                int i = otherModuleCodeNameBase.indexOf('/');
                if (i != -1) {
                    otherModuleCodeNameBase = otherModuleCodeNameBase.substring(0, i);
                }
                Integer otherModuleNodeIndex = codeNameBase2NodeIndex.get(otherModuleCodeNameBase);
                if (otherModuleNodeIndex == null) {
                    Logger.getLogger(getClass().getName()).log(Level.SEVERE, moduleInfo.getCodeNameBase() + " depends on '" + otherModuleCodeNameBase + "' but this module could not be found.");
                } else {
                    ModuleInfo otherModule = graphNodes.get(otherModuleNodeIndex);
                    adjacencyMatrix[nodeIndex][otherModuleNodeIndex] = new ModuleModuleDependency(moduleInfo, moduleDependency, otherModule);
                }
            }
        }
    }

    /**
     * Returns all modules on which module depends.
     *
     * @param module The module.
     * @return A list (sorted by display name) on which module depends.
     */
    public List<ModuleModuleDependency> dependenciesOf(ModuleInfo module) {
        Integer index = codeNameBase2NodeIndex.get(module.getCodeNameBase());
        if (index == null) {
            return Collections.<ModuleModuleDependency>emptyList();
        }
        ArrayList<ModuleModuleDependency> dependencyModules = new ArrayList<>();
        for (int i = 0; i < this.adjacencyMatrix.length; i++) {
            if (adjacencyMatrix[index][i] != null) {
                dependencyModules.add(adjacencyMatrix[index][i]);
            }
        }
        dependencyModules.sort( (d1, d2) -> {
            int n1 = d1.getTargets().size();
            int n2 = d2.getTargets().size();
            int c = Integer.compare(n1, n2);
            if (c == 0 && n1 > 0) {
                return d1.getTargets().get(0).getDisplayName().compareTo(d2.getTargets().get(0).getDisplayName());
            }
            return c;
        });
        
        return dependencyModules;
    }

}
