# Notes from official documentation

## Jar
[Reference](https://quarkus.io/guides/getting-started)

```bash
java -jar target/getting-started-1.0-SNAPSHOT-runner.jar
```
> The Class-Path entry of the MANIFEST.MF from the runner jar explicitly lists the jars from the lib directory. So if you want to deploy your application somewhere, you need to copy the runner jar as well as the lib directory.
> Before running the application, don’t forget to stop the hot reload mode (hit CTRL+C), or you will have a port conflict.

## Native build
[Reference](https://quarkus.io/guides/building-native-image)
```bash
# compile binary for current OS
./mvnw package -Pnative
```

```bash
# compile binary for linux_amd64 using docker
./mvnw package -Pnative -Dquarkus.native.container-build=true \
  -Dquarkus.native.container-runtime=docker
```

```bash
# build docker img with compiled binary 
docker build -f src/docker/Dockerfile.native -t game-api .

# create container our of image
docker run -i --rm -p 8080:8080 game-api
```

## OSx specific
```bash
xattr -r -d com.apple.quarantine /Library/Java/JavaVirtualMachines/graalvm
```
