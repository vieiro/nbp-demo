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

import java.util.ArrayList;
import java.util.List;

/**
 *
 */
public class ModuleDocumentation {
    private String moduleCodeNameBase;
    private List<String> documentationURLs;
    private List<String> tutorialURLs;
    
    public ModuleDocumentation() {
        documentationURLs = new ArrayList<>();
        tutorialURLs = new ArrayList<>();
    }

    public String getModuleCodeNameBase() {
        return moduleCodeNameBase;
    }

    public void setModuleCodeNameBase(String moduleCodeNameBase) {
        this.moduleCodeNameBase = moduleCodeNameBase;
    }

    public List<String> getDocumentationURLs() {
        return documentationURLs;
    }

    public void setDocumentationURLs(List<String> documentationURLs) {
        this.documentationURLs = documentationURLs;
    }

    public List<String> getTutorialURLs() {
        return tutorialURLs;
    }

    public void setTutorialURLs(List<String> tutorialURLs) {
        this.tutorialURLs = tutorialURLs;
    }
    
}
