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
package org.nbdemo.gui.features.model;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author antonio
 */
public class NetBeansPlatformFeatureList {
    
    private List<NetBeansPlatformFeature> features;
    
    public NetBeansPlatformFeatureList() {
        features = new ArrayList<>();
    }

    public List<NetBeansPlatformFeature> getFeatures() {
        return features;
    }

    public void setFeatures(List<NetBeansPlatformFeature> features) {
        this.features = features;
    }
    
}
