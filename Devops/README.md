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

mvn compile

## Build Docker Images 
docker compose -f docker-stack.yml build
docker compose -f docker-stack.yml build config-server
## Push Docker Images 
docker compose -f docker-stack.yml push 
docker compose -f docker-stack.yml push image-service

## Docker Swarm 
## -------------

# Stop all the containers

docker stop $(docker ps -a -q)

# Remove all the containers

docker rm $(docker ps -a -q)

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

# Run Site Without web security (Chrome browser without CORS)
# Linux
chromium --disable-web-security --user-data-dir=./ChromeDevSession
# Windows 
"C:\Program Files\Google\Chrome\Application\chrome.exe" --disable-web-security --user-data-dir="C:\tmpChromeSession"


# kubernetes/ingress logs

kubectl describe ing




## Errors 

Cross-Origin Request Blocked: The Same Origin Policy disallows reading the remote resource at http://twitter.local/ui/v1/user/token. (Reason: CORS header ‘Access-Control-Allow-Origin’ missing). Status code: 504.

import com.fasterxml.jackson.annotation.Json




Flyway failed to initialize: none of the following migration scripts locations could be found:

        - classpath:db/migration/topic

/home/user1/Desktop/Tools/DevOps/DevopsTwitter/twitter-spring-reactjs/topic-service/target/classes/db/migration/topic/
/home/user1/Desktop/Tools/DevOps/DevopsTwitter/twitter-spring-reactjs/topic-service/src/db/migration/topic/




		at org.apache.catalina.core.StandardContextValve.invoke(StandardContextValve.java:97) ~[tomcat-embed-core-9.0.62.jar!/:na]
		at org.apache.catalina.authenticator.AuthenticatorBase.invoke(AuthenticatorBase.java:541) ~[tomcat-embed-core-9.0.62.jar!/:na]
		at org.apache.catalina.core.StandardHostValve.invoke(StandardHostValve.java:135) ~[tomcat-embed-core-9.0.62.jar!/:na]
		at org.apache.catalina.valves.ErrorReportValve.invoke(ErrorReportValve.java:92) ~[tomcat-embed-core-9.0.62.jar!/:na]
		at org.apache.catalina.core.StandardEngineValve.invoke(StandardEngineValve.java:78) ~[tomcat-embed-core-9.0.62.jar!/:na]
		at org.apache.catalina.valves.RemoteIpValve.invoke(RemoteIpValve.java:769) ~[tomcat-embed-core-9.0.62.jar!/:na]
		at org.apache.catalina.connector.CoyoteAdapter.service(CoyoteAdapter.java:360) ~[tomcat-embed-core-9.0.62.jar!/:na]
		at org.apache.coyote.http11.Http11Processor.service(Http11Processor.java:399) ~[tomcat-embed-core-9.0.62.jar!/:na]
		at org.apache.coyote.AbstractProcessorLight.process(AbstractProcessorLight.java:65) ~[tomcat-embed-core-9.0.62.jar!/:na]
		at org.apache.coyote.AbstractProtocol$ConnectionHandler.process(AbstractProtocol.java:890) ~[tomcat-embed-core-9.0.62.jar!/:na]
		at org.apache.tomcat.util.net.NioEndpoint$SocketProcessor.doRun(NioEndpoint.java:1743) ~[tomcat-embed-core-9.0.62.jar!/:na]
		at org.apache.tomcat.util.net.SocketProcessorBase.run(SocketProcessorBase.java:49) ~[tomcat-embed-core-9.0.62.jar!/:na]
		at org.apache.tomcat.util.threads.ThreadPoolExecutor.runWorker(ThreadPoolExecutor.java:1191) ~[tomcat-embed-core-9.0.62.jar!/:na]
		at org.apache.tomcat.util.threads.ThreadPoolExecutor$Worker.run(ThreadPoolExecutor.java:659) ~[tomcat-embed-core-9.0.62.jar!/:na]
		at org.apache.tomcat.util.threads.TaskThread$WrappingRunnable.run(TaskThread.java:61) ~[tomcat-embed-core-9.0.62.jar!/:na]
		at java.base/java.lang.Thread.run(Thread.java:833) ~[na:na]


                
## Test 
docker run --env POSTGRES_MULTIPLE_DATABASES=tweet-test,chat-test,lists-test,notification-test,tag-test,topic-test --env POSTGRES_DB=user --env POSTGRES_USER=postgres \
     --env PGUSER=postgres  --env POSTGRES_PASSWORD=root --env PGDATA=/data/postgres \
     -v ./postgres_test_test:/data/postgres -v ./postgresscript_test:/docker-entrypoint-initdb.d \
     -p 5432:5432 postgres:latest



docker run  --env PGADMIN_DEFAULT_EMAIL=${PGADMIN_DEFAULT_EMAIL:-pgadmin4@pgadmin.org} --env PGADMIN_DEFAULT_PASSWORD=${PGADMIN_DEFAULT_PASSWORD:-admin} -p  5050:80 dpage/pgadmin4





docker run -p 8888:8888 merikbest/twitter-spring-reactjs:config-server
docker run -p 8761:8761 merikbest/twitter-spring-reactjs:eureka-server



curl -i 'http://192.168.7.93:50440/api/v1/login'   -H 'Connection: keep-alive'   -H 'User-Agent: Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/99.0.4844.83 Safari/537.36'   -H 'Content-Type: application/json'   -H 'Accept: */*'   -H 'Sec-GPC: 1'   -H 'Origin: http://192.168.7.93:50440'   -H 'Sec-Fetch-Site: same-origin'   -H 'Sec-Fetch-Mode: cors'   -H 'Sec-Fetch-Dest: empty'   -H 'Referer: http://192.168.7.93:50440/login'   -H 'Accept-Language: en-US,en;q=0.9'   -H 'Cookie: PGADMIN_LANGUAGE=en'   --data-raw '{"accessKey":"minio-user","secretKey":"minio-password"}'   --compressed



ssh -L 8080:192.168.7.105:8080 vmuser@192.168.7.105
