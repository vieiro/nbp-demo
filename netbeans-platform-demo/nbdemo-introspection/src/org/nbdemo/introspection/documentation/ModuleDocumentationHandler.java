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

import java.io.IOException;
import java.io.InputStream;
import java.util.Collection;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.openide.modules.ModuleInfo;
import org.openide.util.Exceptions;
import org.openide.util.Lookup;
import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.constructor.Constructor;

/**
 * Reads 'module-documentation.yml' and creates AllModulesDocumentation. TODO:
 * This is work in progress.
 */
public class ModuleDocumentationHandler {

    private static ModuleDocumentationHandler instance;

    public static synchronized ModuleDocumentationHandler getDefault() {
        if (instance == null) {
            instance = new ModuleDocumentationHandler();
        }
        return instance;
    }

    private AllModulesDocumentation documentation;

    private ModuleDocumentationHandler() {
        // Read "module-documentation.yml"
        // This is a Map<String, List<String>> mapping module's CodeNameBase to a list of links.

        InputStream moduleDocumentationInput = ModuleDocumentationHandler.class.getResourceAsStream("module-documentation.yml");
        if (moduleDocumentationInput != null) {
            try {
                Yaml yaml = new Yaml(new Constructor(AllModulesDocumentation.class));
                documentation = yaml.load(moduleDocumentationInput);
            } catch (Exception e) {
                Logger.getLogger(ModuleDocumentationHandler.class.getName()).log(Level.SEVERE, "Could not load 'module-documentation.yml':" + e.getMessage(), e);
            } finally {
                try {
                    moduleDocumentationInput.close();
                } catch (IOException ex) {
                    Exceptions.printStackTrace(ex);
                }
            }
        }
        if (documentation == null) {
            documentation = new AllModulesDocumentation();
        }
        /*
        Populate any possible missing modules
         */
        Collection<? extends ModuleInfo> allModules = Lookup.getDefault().lookupAll(ModuleInfo.class);

        allModules.stream().forEach((moduleInfo) -> {
            String key = moduleInfo.getCodeNameBase();
            ModuleDocumentation moduleDocumentation = documentation.getModules().get(key);
            if (moduleDocumentation == null) {
                moduleDocumentation = new ModuleDocumentation();
                moduleDocumentation.setModuleCodeNameBase(key);
                documentation.getModules().put(key, moduleDocumentation);
            }
        });

        /*
        Print out the result. This was used to create the original documentation.yml file.
        THis is kept here just in case it's required to regenerate this file, either in part or in full.
         */
//        Yaml yaml = new Yaml();
//        StringWriter out = new StringWriter(32*1024);
//        yaml.dump(documentation, out);
//        try {
//            out.close();
//        } catch (IOException ex) {
//            Exceptions.printStackTrace(ex);
//        }
//        Logger.getLogger(getClass().getName()).log(Level.INFO, "MODULE DOCUMENTATION:");
//        Logger.getLogger(getClass().getName()).log(Level.INFO, out.toString());
    }

    public AllModulesDocumentation getDocumentation() {
        return documentation;
    }

}
