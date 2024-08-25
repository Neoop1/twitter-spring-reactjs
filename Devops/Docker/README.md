
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
## ---- ## 
docker compose up postgres zipkin rabbitmq zookeeper
docker compose up eureka-server  config-server api-gateway broker websocket-service
docker compose up image-service email-service notification-service chat-service tag-service lists-service topic-service
docker compose up user-service tweet-service
docker compose up frontend traefik
docker compose up minio createbuckets nginxproxy
docker compose up prometheus grafana cadvisor
## --build ##
docker compose up postgres zipkin rabbitmq zookeeper
docker compose up --build eureka-server  config-server api-gateway broker websocket-service
docker compose up --build image-service email-service notification-service chat-service tag-service lists-service topic-service
docker compose up --build user-service tweet-service
docker compose up --build minio createbuckets 
docker compose up frontend traefik

docker compose up --build prometheus grafana cadvisor


docker compose up --build nginxproxy
# Stop all the containers

docker stop $(docker ps -a -q)

# Remove all the containers

docker rm $(docker ps -a -q)









## Temp


docker compose up --build --scale frontend=0


## Site:
https://medium.com/@kiarash.shamaii/dockerizing-spring-boot-best-practices-for-efficient-containerization-f60ed3980dc7