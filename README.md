# Apache NetBeans Incubator Demo App

This is a simple desktop application built on top of the Apache NetBeans
Incubator Platform.

The application shows a list of installed modules, as well as some information
about each module. It also highlights some of the features of the NetBeans
Platform.

This application requires just 35 Java classes.

## Building

- Build the [Apache NetBeans Incubator Platform](https://github.com/apache/incubator-netbeans).
- Use the NetBeans IDE to open and run the project in the [netbeans-platform-demo](/netbeans-platform-demo) directory.

## Pre-built binaries

There are not pre-built binaries for any platform. You can build these yourself, though.

## Screenshots

![Running on a Linux desktop](https://raw.githubusercontent.com/vieiro/nbp-demo/master/resources/screenshot.png)

## Contributing

### Adding features

If you want to highlight a feature of the NetBeans Platform please send me a PR updating the [nbp-features.yml](netbeans-platform-demo/nbdemo-gui/src/org/nbdemo/gui/features/model/nbp-features.yml) YAML file. Please include a 480x270 image also. Images must be properly licensed, and the PR should include a link to the source of the image.

### Other contributions

Enhancements are welcome.

# License

   Copyright 2017 Antonio Vieiro (antonio@vieiro.net)

   Licensed under the Apache License, Version 2.0 (the "License");
   you may not use this file except in compliance with the License.
   You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

   Unless required by applicable law or agreed to in writing, software
   distributed under the License is distributed on an "AS IS" BASIS,
   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
   See the License for the specific language governing permissions and
   limitations under the License.
