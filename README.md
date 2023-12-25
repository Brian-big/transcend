# Transcend

A high school admission backend

---

1. Getting Started
### Spinning a docker postgres database instance

``` shell
docker run --name transcendpostgres -p 5431:5432  -e POSTGRES_PASSWORD=transcend -e POSTGRES_DB=transcend -e POSTGRES_USER=transcend -d postgres:alpine
```
### Building the application:
```shell
./gradlew clean build
```


### Running the application:
```shell
./gradlew bootRun
```

### Creating executable jar for the application:
- generate the far file
 ```shell
./gradlew clean bootJar
```
- run the jar file
```shell
java -jar <build/libs/fileName.jar>
```
OpenAPI documentation:
[http://localhost:8080/swagger-ui/index.html](http://localhost:8080/swagger-ui/index.html)