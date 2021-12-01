# GraalVM Java Python interoperation

## Installation

### Installing GraalVM

1. Download the latest version GraalVM Community from https://www.graalvm.org/downloads/
   currently it's 21.3.0.
2. Extract the archive

```shell
tar -xvzf Downloads/graalvm-ce-java11-linux-amd64-21.3.0.tar.gz
sudo mv graalvm-ce-java11-21.3.0/ /usr/lib/jvm/
sudo ln -s graalvm-ce-java11-21.3.0 graalvm
```

## Configuration

1. run ``update-alternatives --config java``

```shell
update-alternatives --config java

# you should see something like this
  Selection    Path                                            Priority   Status
------------------------------------------------------------
  0            /usr/lib/jvm/java-11-openjdk-amd64/bin/java      1111      auto mode
* 1            /usr/lib/jvm/java-11-openjdk-amd64/bin/java      2         manual mode
  2            /usr/lib/jvm/java-8-openjdk-amd64/jre/bin/java   1081      manual mode

Press <enter> to keep the current choice[*], or type selection number: 

```

2. Add a new Java configuration

```shell
sudo update-alternatives --install /usr/bin/java java /usr/lib/jvm/graalvm/bin/java
```

now you should see a new Java configuration

```shell
update-alternatives --config java
There are 3 choices for the alternative java (providing /usr/bin/java).

  Selection    Path                                            Priority   Status
------------------------------------------------------------
  0            /usr/lib/jvm/java-11-openjdk-amd64/bin/java      1111      auto mode
* 1            /usr/lib/jvm/graalvm/bin/java                    2         manual mode
  2            /usr/lib/jvm/java-11-openjdk-amd64/bin/java      1111      manual mode
  3            /usr/lib/jvm/java-8-openjdk-amd64/jre/bin/java   1081      manual mode

Press <enter> to keep the current choice[*], or type selection number: 

```

3. Quick test: run ``java --version``

```shell
java --version

openjdk 11.0.13 2021-10-19
OpenJDK Runtime Environment GraalVM CE 21.3.0 (build 11.0.13+7-jvmci-21.3-b05)
OpenJDK 64-Bit Server VM GraalVM CE 21.3.0 (build 11.0.13+7-jvmci-21.3-b05, mixed mode, sharing)
```

4. Update .bashrc profile

```shell
# open .bashrc file with an editor of your choice, e.g. VIM.
sudo vim ~/.bashrc

# add the following line at the bottom of the file
export PATH=$PATH:/usr/lib/jvm/graalvm-ce-java11-21.3.0/bin/installer/bin
```

### Installing Python support

run ``gu available``

```shell
gu available
Downloading: Release index file from oca.opensource.oracle.com
Downloading: Component catalog from www.graalvm.org
ComponentId              Version             Component name                Stability                     Origin 
---------------------------------------------------------------------------------------------------------------------------------
espresso                 21.3.0              Java on Truffle               Experimental                  github.com
llvm-toolchain           21.3.0              LLVM.org toolchain            Supported                     github.com
native-image             21.3.0              Native Image                  Early adopter                 github.com
nodejs                   21.3.0              Graal.nodejs                  Supported                     github.com
python                   21.3.0              Graal.Python                  Experimental                  github.com
R                        21.3.0              FastR                         Experimental                  github.com
ruby                     21.3.0              TruffleRuby                   Experimental                  github.com
wasm                     21.3.0              GraalWasm                     Experimental                  github.com
```

run the following:

1. ``gu install llvm-toolchain``
2. ``gu install  native-image``
3. ``gu install python``

### Setting up python env

```shell
graalpython -m venv graalenv
source graalenv/bin/activate
```

2. Installing packages you should be able to install most of common packages found on pypi with ``pip``, however; there
   are some packages that might not be compatible. Some of these packages can be patched and made available.
   run ``graalpython -m ginstall install --help``
   to see such packages and install them via ``graalpython`` if they can't be installed directly via pip. Please, also
   see item **4** in the **Possible Gotchas** section down below if you encounter some issues.

```shell
graalpython -m ginstall install pandas
```

## Calling Java from Python

## Calling Python from Java

suppose you have a Python module at /path/to/file/pure_python.py that does some computation with numpy e.g.

_pure_python.py_

```python
import site  # required!
import numpy as np


def compute_total(*nums):
    return float(np.array(nums).sum())

```

then to use it from within Java you can do something as follows:

```java
package org.vspaz;

import org.graalvm.polyglot.Context;
import org.graalvm.polyglot.Source;
import org.graalvm.polyglot.Value;

import java.io.File;
import java.io.IOException;

public class Main {
    public static double ComputeTotalWithPythonAndNumpy() {
        Context ctx = Context.newBuilder().allowAllAccess(true).build();
        File pythonModule = new File("/path/to/file/pure_python.py");
        try {
            ctx.eval(Source.newBuilder("python", pythonModule).build());
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(-1);
        }
        Value computeTotal = ctx.getBindings("python").getMember("compute_total");
        Object[] nums = {1.0, 2.23, 3.49494, 4.40404, 5.10110, 181.101, 133.11};
        Value computedResult = computeTotal.execute(nums);
        return computedResult.asDouble();
    }

    public static void main(String[] args) {
        System.out.println(ComputeTotalWithPythonAndNumpy()); // -> 330.44108
    }
}

```

## Possible Gotchas

1. **error**

   Downloading: Component catalog from www.graalvm.org

   Error: Unknown component

   **solution**: you might be running an old version of GraalVM; check the version.
2. **error**:

   gu: command not found

   **solution**: check if you can run ``gu`` by specifying the full path
   e.g. ``/usr/lib/jvm/graalvm-ce-java11-21.3.0/bin/gu``. if it works, then check the path in _.bashrc_
3. **error**:

   graalpython: command not found see item **2**.
4. **error**: pip can't install any package e.g. requests

   **solution**: pip install distutils

5. **error**:
   scipy can't be installed

   **solution**: make sure you have the following gfortran libopenblas-dev liblapack-dev installed,
   run ``sudo apt-get install gfortran libopenblas-dev liblapack-dev``

## References

1. https://www.graalvm.org/python/quickstart/
2. https://www.graalvm.org/python/
3. https://www.graalvm.org/reference-manual/python/