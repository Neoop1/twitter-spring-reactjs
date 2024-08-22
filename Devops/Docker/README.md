
## Build MVN without tests
mvn clean package -DskipTests




## Build Docker Images 
docker compose -f docker-compose.yml build
docker compose -f docker-compose.yml build config-server
## Push Docker Images 
docker compose -f docker-compose.yml push 
docker compose -f docker-compose.yml push image-service







## Docker Compose Test Steps
docker compose up postgres
docker compose up pgadmin
docker compose up zipkin
docker compose up rabbitmq
docker compose up zookeeper
docker compose up eureka-server
docker compose up config-server
docker compose up api-gateway
docker compose up broker



docker compose up user-service
docker compose up websocket-service
docker compose up image-service 
docker compose up email-service
docker compose up notification-service
docker compose up chat-service
docker compose up lists-service
docker compose up tag-service
docker compose up topic-service
docker compose up tweet-service
docker compose up frontend

docker compose up traefik
docker compose up nginxproxy 



docker compose up createbuckets
docker compose up minio

docker compose up prometheus
docker compose up grafana
docker compose up cadvisor

## Groups ---- ## 
docker compose up postgres zipkin rabbitmq zookeeper
docker compose up eureka-server  config-server api-gateway broker websocket-service
docker compose up image-service email-service notification-service chat-service tag-service lists-service topic-service
docker compose up user-service tweet-service
docker compose up frontend traefik
docker compose up minio createbuckets nginxproxy
docker compose up prometheus grafana cadvisor

## Groups ---- build ##
docker compose up postgres zipkin rabbitmq zookeeper
docker compose up --build eureka-server  config-server api-gateway broker websocket-service
docker compose up --build image-service email-service notification-service chat-service tag-service lists-service topic-service
docker compose up --build user-service tweet-service
docker compose up frontend traefik
docker compose up minio createbuckets nginxproxy
docker compose up prometheus grafana cadvisor






## Temp

# Stop all the containers

docker stop $(docker ps -a -q)

# Remove all the containers

docker rm $(docker ps -a -q)

# Remove all images !!!
docker rmi $(docker images -q)


# Remove all volumes !!!
docker volume rm $(docker volume ls -q)

### Remove all !!! @@@ !!!
sudo docker system prune -a -f


## pull image 
docker image pull confluentinc/cp-kafka:7.0.1
docker image pull confluentinc/cp-zookeeper:7.0.1
docker image pull rabbitmq:3.9.11-management-alpine
docker image pull openzipkin/zipkin
docker image pull dpage/pgadmin4
docker image pull quay.io/minio/minio
docker image pull postgres
docker image pull gcr.io/cadvisor/cadvisor
docker image pull prom/prometheus
docker image pull docker.io/library/traefik:v3.1.1
docker image pull eclipse-temurin:17-jre




docker compose up --build --scale frontend=0

**Dockerfile-multistage-layered**
FROM maven:3-eclipse-temurin-17 AS maven
RUN mkdir -p /opt/app/src
COPY src /opt/app/src
COPY pom.xml /opt/app
RUN --mount=type=cache,target=/root/.m2 mvn -f /opt/app/pom.xml clean package

FROM eclipse-temurin:17-jre as builder
WORKDIR application
COPY --from=maven /opt/app/target/simplecode-0.0.1-SNAPSHOT.jar application.jar
RUN java -Djarmode=layertools -jar application.jar extract

FROM eclipse-temurin:17-jre
WORKDIR application
COPY --from=builder application/dependencies/ ./
COPY --from=builder application/spring-boot-loader/ ./
COPY --from=builder application/snapshot-dependencies/ ./
COPY --from=builder application/application/ ./
ENTRYPOINT ["java","org.springframework.boot.loader.JarLauncher"]


## Sites:
https://medium.com/@kiarash.shamaii/dockerizing-spring-boot-best-practices-for-efficient-containerization-f60ed3980dc7
https://github.com/maeddes/options-galore-container-build/tree/main/java



## Temp

# Run Site Without web security (Chrome browser without CORS)
Cross-Origin Request Blocked: The Same Origin Policy disallows reading the remote resource at http://twitter.local/ui/v1/user/token. (Reason: CORS header ‘Access-Control-Allow-Origin’ missing). Status code: 504.

**Linux**
chromium --disable-web-security --user-data-dir=./ChromeDevSession
**Windows**
"C:\Program Files\Google\Chrome\Application\chrome.exe" --disable-web-security --user-data-dir="C:\tmpChromeSession"