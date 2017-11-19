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

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import org.openide.modules.Dependency;
import org.openide.util.NbBundle;

/**
 * Module dependency types. With display names and other features.
 *
 * @author Antonio Vieiro <antonio@vieiro.net>
 */
public enum ModuleDependencyType {

    IDE(Dependency.TYPE_IDE), JAVA(Dependency.TYPE_JAVA), MODULE(Dependency.TYPE_MODULE), PACKAGE(Dependency.TYPE_PACKAGE), NEEDS(Dependency.TYPE_NEEDS), RECOMMENDS(Dependency.TYPE_RECOMMENDS), REQUIRES(Dependency.TYPE_REQUIRES);
    private int type;

    ModuleDependencyType(int type) {
        this.type = type;
    }

    public int getType() {
        return type;
    }

    public String getDisplayName() {
        return NbBundle.getBundle(ModuleDependencyType.class).getString(name() + ".DN"); // NO-I18N
    }

    public String getShortDescription() {
        return NbBundle.getBundle(ModuleDependencyType.class).getString(name() + ".DN"); // NO-I18N
    }

    private static final Comparator<ModuleDependencyType> COMPARATOR
            = (mdt1, mdt2) -> {
                return mdt1.getDisplayName().compareTo(mdt2.getDisplayName());
            };

    public static final List<ModuleDependencyType> TYPES_SORTED_BY_NAME = Arrays.stream(values()).sorted(COMPARATOR).collect(Collectors.toList());

}
