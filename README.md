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

