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
package org.nbdemo.introspection.nodes;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import org.openide.modules.ModuleInfo;
import org.openide.nodes.AbstractNode;
import org.openide.nodes.Children;
import org.openide.nodes.Node;
import org.openide.util.Lookup;
import org.openide.util.lookup.AbstractLookup;
import org.openide.util.lookup.InstanceContent;

/**
 *
 */
public class NBAllModulesNode extends AbstractNode {

    private static class NBAllModulesNodeChildren extends Children.Keys<ModuleInfo> {

        public NBAllModulesNodeChildren() {
        }

        @Override
        protected void addNotify() {
            super.addNotify();
            List<ModuleInfo> moduleInfos = Lookup.getDefault().lookupAll(ModuleInfo.class).stream().sorted((m1, m2) -> {
                return m1.getDisplayName().compareTo(m2.getDisplayName());
            }).collect(Collectors.toList());
            Logger.getLogger(getClass().getName()).log(Level.INFO, "Showing " + moduleInfos.size() + " modules");
            setKeys(moduleInfos);
        }

        @Override
        protected Node[] createNodes(ModuleInfo moduleInfo) {
            return new Node[]{new NBModuleNode(moduleInfo)};
        }
    }

    public NBAllModulesNode() {
        this(new InstanceContent());
    }

    private NBAllModulesNode(InstanceContent content) {
        super(new NBAllModulesNodeChildren(), new AbstractLookup(content));
    }

}
