<a href="http://www.reactivemanifesto.org/"> <img style="border: 0; position: fixed; right: 0; top:0; z-index: 9000" src="//d379ifj7s9wntv.cloudfront.net/reactivemanifesto/images/ribbons/we-are-reactive-blue-right.png"> </a>

# **Microservicio TRM Colombia**
![Java CI with Gradle](https://github.com/jsierra93/ms_consultar_trm/workflows/Java%20CI%20with%20Gradle/badge.svg?branch=master)

API Rest para consultar TRM desarrollada con microservicios reactivos.

#### Tecnologias
1. MongoDB (Local, embebida y Dockerizada )
2. Spring Framework
3. lombok
4. Project Reactor

### Ejecutar aplicación

Gradle
 ```shell
 $ ./gradlew bootRun
 ```

### Probar aplicación

Root Endpoint: 
 ```shell
 http://localhost:8081/
 ```
Consultar TRM del dia

 ```shell
 http://localhost:8081/consult/today
 ```
Consultar Historico TRM
 ```shell
 http://localhost:8081/consult/history
 ```
Resetear BD ó Eliminar datos DB
 ```shell
 http://localhost:8081/reset/history
 ```
Sincronizar BD con API Externa TRM
 ```shell
 http://localhost:8081/sync/history
 ```
Eliminar datos Repetidos
 ```shell
 http://localhost:8081/delete/repeat
 ```

### Docker

Construcción de imagen
 ```shell
 docker build -t ms-trm-colombia:latest .
 ```

Ejecutar contenedor con la imagen
 ```shell
docker run -p 8081:8081 -it --name contenedor-trm -d ms-trm-colombia:latest
 ```

Eliminar contenedor
 ```shell
 docker rm ms-trm-colombia:latest
 ```

### That's all
Hope you enjoy it.
