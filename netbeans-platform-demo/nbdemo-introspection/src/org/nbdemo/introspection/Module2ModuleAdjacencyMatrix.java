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
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import org.openide.modules.Dependency;
import org.openide.modules.ModuleInfo;

/**
 * Maintains an adjacency matrix between modules (directDependencies of type
 * Dependency.TYPE_MODULE).
 *
 * @see Dependency
 */
class Module2ModuleAdjacencyMatrix {

    /**
     * Maps module-code-name-base to a graph's node sourceNodeIndex.
     */
    private HashMap<String, Integer> codeNameBase2NodeIndex;

    /**
     * Maps a node sourceNodeIndex to a ModuleInfo
     */
    private List<ModuleInfo> graphNodes;

    /**
     * Dependency matrix.
     */
    private List<ModuleModuleDependency>[][] directDependencies;

    /**
     * Inverted dependency matrix.
     */
    private List<ModuleModuleDependency>[][] invertedDependencies;

    /**
     * Constructs an adjacency matrix
     *
     * @param modules
     */
    public Module2ModuleAdjacencyMatrix(List<ModuleInfo> modules) {

        codeNameBase2NodeIndex = new HashMap<>();
        graphNodes = new ArrayList<>(modules);
        int nmodules = modules.size();
        directDependencies = new List[nmodules][nmodules];
        invertedDependencies = new List[nmodules][nmodules];

        for (int sourceNodeIndex = 0; sourceNodeIndex < nmodules; sourceNodeIndex++) {
            ModuleInfo sourceNode = modules.get(sourceNodeIndex);
            codeNameBase2NodeIndex.put(sourceNode.getCodeNameBase(), sourceNodeIndex);
        }

        for (int sourceNodeIndex = 0; sourceNodeIndex < nmodules; sourceNodeIndex++) {
            ModuleInfo sourceModule = modules.get(sourceNodeIndex);
            List<Dependency> moduleDependencies = sourceModule.getDependencies().stream().filter((d) -> {
                return d.getType() == Dependency.TYPE_MODULE;
            }).collect(Collectors.toList());
            for (Dependency dependency : moduleDependencies) {
                String targetModuleCodeNameBase = dependency.getName();
                // "targetModuleCodeNameBase" is a string of the form "org.netbeans.api.progress/1".
                // we just want the codename base "org.netbeans.api.progress", and not the rest.
                int i = targetModuleCodeNameBase.indexOf('/');
                if (i != -1) {
                    targetModuleCodeNameBase = targetModuleCodeNameBase.substring(0, i);
                }
                Integer targetModuleIndex = codeNameBase2NodeIndex.get(targetModuleCodeNameBase);
                if (targetModuleIndex == null) {
                    Logger.getLogger(getClass().getName()).log(Level.SEVERE, "{0} depends on ''{1}'' but this module could not be found.",
                            new Object[]{sourceModule.getCodeNameBase(), targetModuleCodeNameBase});
                } else {
                    ModuleInfo targetModule = graphNodes.get(targetModuleIndex);
                    List<ModuleModuleDependency> directDependency = directDependencies[sourceNodeIndex][targetModuleIndex];
                    if (directDependency == null) {
                        directDependency = new ArrayList<>();
                        directDependencies[sourceNodeIndex][targetModuleIndex] = directDependency;
                    }
                    directDependency.add(new ModuleModuleDependency(sourceModule, dependency, targetModule));

                    List<ModuleModuleDependency> inverseDependency = invertedDependencies[targetModuleIndex][sourceNodeIndex];
                    if (inverseDependency == null) {
                        inverseDependency = new ArrayList<>();
                        invertedDependencies[sourceNodeIndex][targetModuleIndex] = inverseDependency;
                    }
                    inverseDependency.add(new ModuleModuleDependency(sourceModule, dependency, targetModule));
                }
            }
        }
    }

    /**
     * Returns all modules on which sourceNode depends.
     *
     * @param sourceNode The sourceNode.
     * @return A list (sorted by display name) on which sourceNode depends.
     */
    public List<ModuleModuleDependency> dependenciesOf(ModuleInfo sourceNode) {
        Integer sourceNodeIndex = codeNameBase2NodeIndex.get(sourceNode.getCodeNameBase());
        if (sourceNodeIndex == null) {
            return Collections.<ModuleModuleDependency>emptyList();
        }
        ArrayList<ModuleModuleDependency> result = new ArrayList<>();
        for (int targetNodeIndex = 0; targetNodeIndex < directDependencies.length; targetNodeIndex++) {
            if (directDependencies[sourceNodeIndex][targetNodeIndex] != null) {
                result.addAll(directDependencies[sourceNodeIndex][targetNodeIndex]);
            }
        }
        result.sort((d1, d2) -> {
            int n1 = d1.getTargets().size();
            int n2 = d2.getTargets().size();
            int c = Integer.compare(n1, n2);
            if (c == 0 && n1 > 0) {
                return d1.getTargets().get(0).getDisplayName().compareTo(d2.getTargets().get(0).getDisplayName());
            }
            return c;
        });

        return result;
    }

    /**
     * Returns all modules that use the given targetModule.
     *
     * @param targetModule A targetModule.
     * @return A list of all ModuleModuleDependency which target contains the
 given targetModule.
     */
    public List<ModuleModuleDependency> invertedDepdendenciesOf(ModuleInfo targetModule) {
        Integer targetIndex = codeNameBase2NodeIndex.get(targetModule.getCodeNameBase());
        if (targetIndex == null) {
            return Collections.<ModuleModuleDependency>emptyList();
        }
        ArrayList<ModuleModuleDependency> result = new ArrayList<>();
        for (int sourceNodeIndex = 0; sourceNodeIndex < invertedDependencies.length; sourceNodeIndex++) {
            if (directDependencies[sourceNodeIndex][targetIndex] != null) {
                result.addAll(directDependencies[sourceNodeIndex][targetIndex]);
            }
        }
        result.sort((d1, d2) -> {
            int n1 = d1.getTargets().size();
            int n2 = d2.getTargets().size();
            int c = Integer.compare(n1, n2);
            if (c == 0 && n1 > 0) {
                return d1.getTargets().get(0).getDisplayName().compareTo(d2.getTargets().get(0).getDisplayName());
            }
            return c;
        });

        return result;
    }

}
