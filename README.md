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

1. Add a new Java configuration

```shell
update-alternatives --config java
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