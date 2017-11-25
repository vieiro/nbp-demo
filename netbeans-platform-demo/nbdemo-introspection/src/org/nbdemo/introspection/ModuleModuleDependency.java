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

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.openide.modules.Dependency;
import org.openide.modules.ModuleInfo;

/**
 * Represents a Dependency between two modules. A module can depend on another
 * module (Dependency.TYPE_MODULE) or some more modules
 * (Dependency.TYPE_PROVIDES). Although this class has a list of targets, the
 * class represents a single Dependency.
 *
 * @see Dependency
 * @author Antonio Vieiro (antonio@vieiro.net)
 */
public final class ModuleModuleDependency {

    private ModuleInfo source;
    private List<ModuleInfo> targets;
    private Dependency dependency;

    public ModuleModuleDependency(ModuleInfo source, Dependency dependency, ModuleInfo target) {
        this(source, dependency, Collections.<ModuleInfo>singletonList(target));
    }

    public ModuleModuleDependency(ModuleInfo source, Dependency dependency, List<ModuleInfo> targets) {
        this.source = source;
        this.dependency = dependency;
        this.targets = new ArrayList<>(targets);
        this.targets.sort((m1, m2) -> {
            return m1.getDisplayName().compareTo(m2.getDisplayName());
        });
    }

    public ModuleInfo getSource() {
        return source;
    }

    public List<ModuleInfo> getTargets() {
        return targets;
    }

    public Dependency getDependency() {
        return dependency;
    }

}
