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

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author antonio
 */
public class NetBeansPlatformFeatureListIOTest {

    public NetBeansPlatformFeatureListIOTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }
    
    @Test
    public void testSample() throws Exception {
        NetBeansPlatformFeature feature = new NetBeansPlatformFeature();
        feature.setTitle("Super strong modules");
        feature.setSubtitle("Battlefield tested module system");
        feature.setDescription("NetBeans Module System has been improved over twenty years. It's able to load hundreths of modules very quickly, and also handles OSGi modules.");
        
        NetBeansPlatformFeatureList list = new NetBeansPlatformFeatureList();
        list.getFeatures().add(feature);
        
        NetBeansPlatformFeatureListIO.write(list, System.out);
    }

    @Test
    public void testLoad() throws Exception {
        NetBeansPlatformFeatureList featureList = NetBeansPlatformFeatureListIO.read();
        Assert.assertTrue("No features found.", featureList.getFeatures().size() > 0);
        for (NetBeansPlatformFeature feature : featureList.getFeatures()) {
            System.out.println(feature.getTitle());
        }
    }
}
