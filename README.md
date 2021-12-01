# GraalVM Java Python interoperation

## Installation
1. Download the latest version GraalVM Community from https://www.graalvm.org/downloads/
currently it's 21.3.0.
2. extract the archive

```shell
tar -xvzf Downloads/graalvm-ce-java11-linux-amd64-21.3.0.tar.gz
sudo mv graalvm-ce-java11-21.3.0/ /usr/lib/jvm/
sudo ln -s graalvm-ce-java11-21.3.0 graalvm
```

## Configuration

1. run **update-alternatives --config java**
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
3. Quick test: run **java --version**

```shell
java --version

openjdk 11.0.13 2021-10-19
OpenJDK Runtime Environment GraalVM CE 21.3.0 (build 11.0.13+7-jvmci-21.3-b05)
OpenJDK 64-Bit Server VM GraalVM CE 21.3.0 (build 11.0.13+7-jvmci-21.3-b05, mixed mode, sharing)
```


## Calling Java from Python

## Calling Python from Java

## Possible Gotchas
1. **error** 
   
   Downloading: Component catalog from www.graalvm.org
   
   Error: Unknown component 

   you might be running an old version of GraalVM.


## References
1. https://www.graalvm.org/python/quickstart/
2. 