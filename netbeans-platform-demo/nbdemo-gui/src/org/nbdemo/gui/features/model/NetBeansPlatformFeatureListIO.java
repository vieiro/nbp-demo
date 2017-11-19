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

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import java.io.PrintWriter;
import org.yaml.snakeyaml.DumperOptions;
import org.yaml.snakeyaml.DumperOptions.FlowStyle;
import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.constructor.Constructor;

/**
 *
 * @author antonio
 */
public class NetBeansPlatformFeatureListIO {

    public static final NetBeansPlatformFeatureList read() throws IOException {
        InputStream input = NetBeansPlatformFeatureListIO.class.getResourceAsStream("nbp-features.yml");
        if (input == null) {
            return new NetBeansPlatformFeatureList();
        }
        Yaml yaml = new Yaml(new Constructor(NetBeansPlatformFeatureList.class));
        NetBeansPlatformFeatureList featureList = yaml.load(input);
        input.close();
        return featureList;
    }

    static void write(NetBeansPlatformFeatureList list, PrintStream out) {
        PrintWriter pw = new PrintWriter(out);
        DumperOptions options = new DumperOptions();
        options.setAllowUnicode(true);
        options.setDefaultFlowStyle(FlowStyle.BLOCK);
        options.setDefaultScalarStyle(DumperOptions.ScalarStyle.LITERAL);
        Yaml yaml = new Yaml(options);
        yaml.dump(list, pw);
        pw.flush();
    }
}
