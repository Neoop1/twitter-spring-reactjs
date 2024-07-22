## Docker Compose Steps
docker compose up postgres  
docker compose up pgadmin  
docker compose up zipkin  
docker compose up rabbitmq
docker compose up zookeeper
docker compose up eureka-server
docker compose up config-server
docker compose up api-gateway
docker compose up broker
docker compose up createbuckets
docker compose up minio

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
docker compose up nginxproxy 

docker compose up prometheus
docker compose up grafana


## Kube Steps
kubectl create ns twitter-website

#postgres
kubectl apply -f ./postgres-database/postgres-pv.yaml
kubectl apply -f ./postgres-database/postgres-pvc.yaml
kubectl apply -f ./postgres-database/postgres-service.yaml
kubectl apply -f ./postgres-database/postgres-secret.yaml
kubectl apply -f ./postgres-database/postgres-deployment.yaml

#zipkin
kubectl apply -f ./zipkin/zipkin-service.yaml
kubectl apply -f ./zipkin/zipkin-deployment.yaml

#rabbitmq
kubectl apply -f ./rabbitmq/rabbitmq-service.yaml
kubectl apply -f ./rabbitmq/rabbitmq-deployment.yaml

#zookeeper
kubectl apply -f ./zookeeper/zookeeper-service.yaml
kubectl apply -f ./zookeeper/zookeeper-deployment.yaml


#eureka-server
kubectl apply -f ./eureka-server/eureka-server-service.yaml
kubectl apply -f ./eureka-server/eureka-server-deployment.yaml

#config-server
kubectl apply -f ./config-server/config-server-service.yaml
kubectl apply -f ./config-server/config-server-deployment.yaml


#api-gateway
kubectl apply -f ./api-gateway/api-gateway-service.yaml
kubectl apply -f ./api-gateway/api-gateway-deployment.yaml


#broker
kubectl apply -f ./broker/broker-service.yaml
kubectl apply -f ./broker/broker-deployment.yaml

#minio
kubectl apply -f ./minio/minio-secret.yaml
kubectl apply -f ./minio/minio-pvc.yaml
kubectl apply -f ./minio/minio-pv.yaml
kubectl apply -f ./minio/minio-service.yaml
kubectl apply -f ./minio/minio-deployment.yaml



#user-service
kubectl apply -f ./user-service/user-service-service.yaml
kubectl apply -f ./user-service/user-service-deployment.yaml

#websocket-service
kubectl apply -f ./websocket-service/websocket-service-service.yaml
kubectl apply -f ./websocket-service/websocket-service-deployment.yaml

#image-service 
kubectl apply -f ./image-service/image-service-service.yaml
kubectl apply -f ./image-service/image-service-deployment.yaml

#email-service
kubectl apply -f ./email-service/email-service-service.yaml
kubectl apply -f ./email-service/email-service-deployment.yaml

#notification-service
kubectl apply -f ./notification-service/notification-service-service.yaml
kubectl apply -f ./notification-service/notification-service-deployment.yaml

#chat-service
kubectl apply -f ./chat-service/chat-service-service.yaml
kubectl apply -f ./chat-service/chat-service-deployment.yaml

#lists-service
kubectl apply -f ./lists-service/lists-service-service.yaml
kubectl apply -f ./lists-service/lists-service-deployment.yaml

#tag-service
kubectl apply -f ./tag-service/tag-service-service.yaml
kubectl apply -f ./tag-service/tag-service-deployment.yaml

#topic-service
kubectl apply -f ./topic-service/topic-service-service.yaml
kubectl apply -f ./topic-service/topic-service-deployment.yaml

#tweet-service
kubectl apply -f ./tweet-service/tweet-service-service.yaml
kubectl apply -f ./tweet-service/tweet-service-deployment.yaml

#frontend
kubectl apply -f ./frontend/frontend-service.yaml
kubectl apply -f ./frontend/frontend-deployment.yaml
kubectl apply -f ./frontend/frontend-ingress.yaml

## ---------
## Build MVN without tests
mvn clean package -DskipTests

## Build Docker Images 
docker compose -f docker-stack.yml build
docker compose -f docker-stack.yml build config-server
## Push Docker Images 
docker compose -f docker-stack.yml push 
docker compose -f docker-stack.yml push image-service

## Docker Swarm 
## -------------

# create betwork 
docker network create -d overlay spring-swarm
docker network create -d overlay postgres-swarm

# start stack  - docker stack deploy -c docker-stack.yml {NAME_OF_THE_STACK}

docker stack deploy -c docker-stack.yml twitter

# update service if needed
docker service update --force twitter_user-service
docker service update --image <username>/<repo> <servicename>   
docker service update --image neoop1/twitter-nginxproxy-service:v0.5 twitter_nginxproxy 
# view stack 
docker stack services twitter

# stop stack 
docker stack down twitter


# view logs - docker service logs -f {NAME_OF_THE_SERVICE}
docker service logs -f twitter_user-service
docker service logs -f twitter_frontend

# Host
127.0.0.1 Twitter.local






# kubernetes/ingress logs

kubectl describe ing