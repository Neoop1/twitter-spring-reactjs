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