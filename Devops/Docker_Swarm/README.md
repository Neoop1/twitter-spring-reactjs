## Docker Swarm 
## -------------

# create betwork 
docker network create -d overlay spring-swarm
docker network create -d overlay postgres-swarm
docker network create -d frontend-swarm-network


## Build MVN without tests
mvn clean package -DskipTests

mvn compile

## Build Docker Images 
docker compose -f docker-stack.yml build
docker compose -f docker-stack.yml build config-server
## Push Docker Images 
docker compose -f docker-stack.yml push 
docker compose -f docker-stack.yml push image-service



# start stack  - docker stack deploy -c docker-stack.yml {NAME_OF_THE_STACK}

docker stack deploy -c docker-stack.yml twitter


# update service if needed
docker service update --force twitter_user-service
docker service update --image <username>/<repo> <servicename>   
docker service update --image neoop1/twitter-nginxproxy-service:v0.5 twitter_nginxproxy 
# view stack 
docker stack services twitter


# rmove service 
docker service rm  twitter_rabbitmq

# stop stack 
docker stack down twitter


# view logs - docker service logs -f {NAME_OF_THE_SERVICE}
docker service logs -f twitter_user-service
docker service logs -f twitter_frontend








## Temp

twitter_api-gateway            replicated   1/1        neoop1/twitter-api-gateway:latest            
twitter_broker                 replicated   1/1        confluentinc/cp-kafka:7.0.1                  
twitter_cadvisor               replicated   1/1        gcr.io/cadvisor/cadvisor:latest              
twitter_chat-service           replicated   1/1        neoop1/twitter-chat-service:latest           
twitter_config-server          replicated   1/1        neoop1/twitter-config-server:v0.12           
twitter_createbuckets          replicated   0/1        minio/mc:latest                              
twitter_docker-api-socat       replicated   1/1        tecnativa/docker-socket-proxy:0.2            
twitter_email-service          replicated   1/1        neoop1/twitter-email-service:latest          
twitter_eureka-server          replicated   1/1        neoop1/twitter-eureka-server:latest          
twitter_frontend               replicated   1/1        neoop1/twitter-frontend:v0.10                
twitter_grafana                replicated   1/1        neoop1/twitter-grafana:latest                
twitter_image-service          replicated   1/1        neoop1/twitter-image-service:latest          
twitter_lists-service          replicated   1/1        neoop1/twitter-lists-service:latest          
twitter_minio                  replicated   1/1        quay.io/minio/minio:latest                   
twitter_notification-service   replicated   1/1        neoop1/twitter-notification-service:latest   
twitter_pgadmin                replicated   1/1        dpage/pgadmin4:latest                        
twitter_postgres               replicated   1/1        postgres:latest                              
twitter_prometheus             replicated   1/1        prom/prometheus:latest                       
twitter_rabbitmq               replicated   1/1        rabbitmq:3.9.11-management-alpine            
twitter_tag-service            replicated   1/1        neoop1/twitter-tag-service:latest            
twitter_topic-service          replicated   1/1        neoop1/twitter-topic-service:latest          
twitter_traefik                replicated   1/1        traefik:v3.1.1                               *:80->80/tcp, *:8088->8080/tcp
twitter_tweet-service          replicated   1/1        neoop1/twitter-tweet-service:latest          
twitter_user-service           replicated   1/1        neoop1/twitter-user-service:latest           
twitter_websocket-service      replicated   1/1        neoop1/twitter-websocket-service:latest      
twitter_zipkin                 replicated   1/1        openzipkin/zipkin:latest                     
twitter_zookeeper              replicated   1/1        confluentinc/cp-zookeeper:7.0.1