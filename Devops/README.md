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


## Kube Steps

kubectl create ns twitter-website
kubectl apply -f ./00-global/00-twitter-website-configmap.yaml

## postgres
kubectl apply -f ./01-postgres-database/01-postgres-pv.yaml
kubectl apply -f ./01-postgres-database/01-postgres-pvc.yaml
kubectl apply -f ./01-postgres-database/01-postgres-service.yaml
kubectl apply -f ./01-postgres-database/01-postgres-secret.yaml
kubectl apply -f ./01-postgres-database/01-postgres-deployment.yaml

## zipkin
kubectl apply -f ./02-zipkin/02-zipkin-service.yaml
kubectl apply -f ./02-zipkin/02-zipkin-deployment.yaml

## rabbitmq
kubectl apply -f ./03-rabbitmq/03-rabbitmq-service.yaml
kubectl apply -f ./03-rabbitmq/03-rabbitmq-deployment.yaml

## zookeeper
kubectl apply -f ./04-zookeeper/04-zookeeper-service.yaml
kubectl apply -f ./04-zookeeper/04-zookeeper-deployment.yaml


## eureka-server
kubectl apply -f ./05-eureka-server/05-eureka-server-service.yaml
kubectl apply -f ./05-eureka-server/05-eureka-server-deployment.yaml

## config-server
kubectl apply -f ./06-config-server/06-config-server-service.yaml
kubectl apply -f ./06-config-server/06-config-server-deployment.yaml


## api-gateway
kubectl apply -f ./07-api-gateway/07-api-gateway-service.yaml
kubectl apply -f ./07-api-gateway/07-api-gateway-deployment.yaml

## broker
kubectl apply -f ./08-broker/08-broker-service.yaml
kubectl apply -f ./08-broker/08-broker-deployment.yaml

## websocket-service
kubectl apply -f ./09-websocket-service/09-websocket-service-service.yaml
kubectl apply -f ./09-websocket-service/09-websocket-service-deployment.yaml

## user-service
kubectl apply -f ./10-user-service/10-user-service-service.yaml
kubectl apply -f ./10-user-service/10-user-service-deployment.yaml

## email-service
kubectl apply -f ./11-email-service/11-email-service-service.yaml
kubectl apply -f ./11-email-service/11-email-service-deployment.yaml

## notification-service
kubectl apply -f ./12-notification-service/12-notification-service-service.yaml
kubectl apply -f ./12-notification-service/12-notification-service-deployment.yaml

## chat-service
kubectl apply -f ./13-chat-service/13-chat-service-service.yaml
kubectl apply -f ./13-chat-service/13-chat-service-deployment.yaml

## lists-service
kubectl apply -f ./14-lists-service/14-lists-service-service.yaml
kubectl apply -f ./14-lists-service/14-lists-service-deployment.yaml

## tag-service
kubectl apply -f ./15-tag-service/15-tag-service-service.yaml
kubectl apply -f ./15-tag-service/15-tag-service-deployment.yaml

## topic-service
kubectl apply -f ./16-topic-service/16-topic-service-service.yaml
kubectl apply -f ./16-topic-service/16-topic-service-deployment.yaml

## tweet-service
kubectl apply -f ./17-tweet-service/17-tweet-service-service.yaml
kubectl apply -f ./17-tweet-service/17-tweet-service-deployment.yaml

## image-service 
kubectl apply -f ./18-image-service/18-image-service-service.yaml
kubectl apply -f ./18-image-service/18-image-service-deployment.yaml

## minio
kubectl apply -f ./19-minio/19-minio-secret.yaml
kubectl apply -f ./19-minio/19-minio-pvc.yaml
kubectl apply -f ./19-minio/19-minio-pv.yaml
kubectl apply -f ./19-minio/19-minio-service.yaml
kubectl apply -f ./19-minio/19-minio-deployment.yaml

## frontend
kubectl apply -f ./20-frontend/20-frontend-service.yaml
kubectl apply -f ./20-frontend/20-frontend-deployment.yaml
kubectl apply -f ./20-frontend/20-frontend-ingress.yaml

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
docker network create -d frontend-swarm-network
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




java.io.FileNotFoundException: MultipartFile resource [file] cannot be resolved to absolute file path
      at org.springframework.core.io.AbstractResource.getFile(AbstractResource.java:138) ~[spring-core-5.3.24.jar!/:5.3.24]
      at java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke0(Native Method) ~[na:na]
      at java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:77) ~[na:na]
      at java.base/jdk.internal.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43) ~[na:na]
      at java.base/java.lang.reflect.Method.invoke(Method.java:568) ~[na:na]
      at com.fasterxml.jackson.databind.ser.BeanPropertyWriter.serializeAsField(BeanPropertyWriter.java:689) ~[jackson-databind-2.13.4.2.jar!/:2.13.4.2]
      at com.fasterxml.jackson.databind.ser.std.BeanSerializerBase.serializeFields(BeanSerializerBase.java:774) ~[jackson-databind-2.13.4.2.jar!/:2.13.4.2]
      at com.fasterxml.jackson.databind.ser.BeanSerializer.serialize(BeanSerializer.java:178) ~[jackson-databind-2.13.4.2.jar!/:2.13.4.2]
      at com.fasterxml.jackson.databind.ser.BeanPropertyWriter.serializeAsField(BeanPropertyWriter.java:728) ~[jackson-databind-2.13.4.2.jar!/:2.13.4.2]
      at com.fasterxml.jackson.databind.ser.std.BeanSerializerBase.serializeFields(BeanSerializerBase.java:774) ~[jackson-databind-2.13.4.2.jar!/:2.13.4.2]
      at com.fasterxml.jackson.databind.ser.BeanSerializer.serialize(BeanSerializer.java:178) ~[jackson-databind-2.13.4.2.jar!/:2.13.4.2]
      at com.fasterxml.jackson.databind.ser.std.MapSerializer.serializeFields(MapSerializer.java:808) ~[jackson-databind-2.13.4.2.jar!/:2.13.4.2]
      at com.fasterxml.jackson.databind.ser.std.MapSerializer.serializeWithoutTypeInfo(MapSerializer.java:764) ~[jackson-databind-2.13.4.2.jar!/:2.13.4.2]
      at com.fasterxml.jackson.databind.ser.std.MapSerializer.serialize(MapSerializer.java:720) ~[jackson-databind-2.13.4.2.jar!/:2.13.4.2]
      at com.fasterxml.jackson.databind.ser.std.MapSerializer.serialize(MapSerializer.java:35) ~[jackson-databind-2.13.4.2.jar!/:2.13.4.2]
      at com.fasterxml.jackson.databind.ser.DefaultSerializerProvider._serialize(DefaultSerializerProvider.java:480) ~[jackson-databind-2.13.4.2.jar!/:2.13.4.2]
      at com.fasterxml.jackson.databind.ser.DefaultSerializerProvider.serializeValue(DefaultSerializerProvider.java:400) ~[jackson-databind-2.13.4.2.jar!/:2.13.4.2]
      at com.fasterxml.jackson.databind.ObjectWriter$Prefetch.serialize(ObjectWriter.java:1514) ~[jackson-databind-2.13.4.2.jar!/:2.13.4.2]
      at com.fasterxml.jackson.databind.ObjectWriter.writeValue(ObjectWriter.java:1007) ~[jackson-databind-2.13.4.2.jar!/:2.13.4.2]
      at org.springframework.http.converter.json.AbstractJackson2HttpMessageConverter.writeInternal(AbstractJackson2HttpMessageConverter.java:456) ~[spring-web-5.3.24.jar!/:5.3.24]
      at org.springframework.http.converter.AbstractGenericHttpMessageConverter.write(AbstractGenericHttpMessageConverter.java:104) ~[spring-web-5.3.24.jar!/:5.3.24]
      at org.springframework.cloud.openfeign.support.SpringEncoder.checkAndWrite(SpringEncoder.java:217) ~[spring-cloud-openfeign-core-3.1.0.jar!/:3.1.0]
      at org.springframework.cloud.openfeign.support.SpringEncoder.encodeWithMessageConverter(SpringEncoder.java:146) ~[spring-cloud-openfeign-core-3.1.0.jar!/:3.1.0]
      at org.springframework.cloud.openfeign.support.SpringEncoder.encode(SpringEncoder.java:134) ~[spring-cloud-openfeign-core-3.1.0.jar!/:3.1.0]
      at org.springframework.cloud.openfeign.support.PageableSpringEncoder.encode(PageableSpringEncoder.java:101) ~[spring-cloud-openfeign-core-3.1.0.jar!/:3.1.0]
      at feign.ReflectiveFeign$BuildFormEncodedTemplateFromArgs.resolve(ReflectiveFeign.java:358) ~[feign-core-11.7.jar!/:na]
      at feign.ReflectiveFeign$BuildTemplateByResolvingArgs.create(ReflectiveFeign.java:232) ~[feign-core-11.7.jar!/:na]
      at feign.SynchronousMethodHandler.invoke(SynchronousMethodHandler.java:84) ~[feign-core-11.7.jar!/:na]
      at org.springframework.cloud.openfeign.FeignCircuitBreakerInvocationHandler.lambda$asSupplier$1(FeignCircuitBreakerInvocationHandler.java:112) ~[spring-cloud-openfeign-core-3.1.0.jar!/:3.1.0]
      at org.springframework.cloud.sleuth.instrument.circuitbreaker.TraceSupplier.get(TraceSupplier.java:52) ~[spring-cloud-sleuth-instrumentation-3.1.0.jar!/:3.1.0]
      at java.base/java.util.concurrent.FutureTask.run(FutureTask.java:264) ~[na:na]
      at java.base/java.util.concurrent.ThreadPoolExecutor.runWorker(ThreadPoolExecutor.java:1136) ~[na:na]
      at java.base/java.util.concurrent.ThreadPoolExecutor$Worker.run(ThreadPoolExecutor.java:635) ~[na:na]
      at java.base/java.lang.Thread.run(Thread.java:833) ~[na:na]
tweet-service  | 

                
## Test 
docker run --env POSTGRES_MULTIPLE_DATABASES=tweet-test,chat-test,lists-test,notification-test,tag-test,topic-test --env POSTGRES_DB=user --env POSTGRES_USER=postgres \
     --env PGUSER=postgres  --env POSTGRES_PASSWORD=root --env PGDATA=/data/postgres \
     -v ./postgres_test_test:/data/postgres -v ./postgresscript_test:/docker-entrypoint-initdb.d \
     -p 5432:5432 postgres:latest



docker run  --env PGADMIN_DEFAULT_EMAIL=${PGADMIN_DEFAULT_EMAIL:-pgadmin4@pgadmin.org} --env PGADMIN_DEFAULT_PASSWORD=${PGADMIN_DEFAULT_PASSWORD:-admin} -p  5050:80 dpage/pgadmin4





docker run -p 8888:8888 merikbest/twitter-spring-reactjs:config-server
docker run -p 8761:8761 merikbest/twitter-spring-reactjs:eureka-server



curl -i 'http://192.168.7.93:50440/api/v1/login'   -H 'Connection: keep-alive'   -H 'User-Agent: Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/99.0.4844.83 Safari/537.36'   -H 'Content-Type: application/json'   -H 'Accept: */*'   -H 'Sec-GPC: 1'   -H 'Origin: http://192.168.7.93:50440'   -H 'Sec-Fetch-Site: same-origin'   -H 'Sec-Fetch-Mode: cors'   -H 'Sec-Fetch-Dest: empty'   -H 'Referer: http://192.168.7.93:50440/login'   -H 'Accept-Language: en-US,en;q=0.9'   -H 'Cookie: PGADMIN_LANGUAGE=en'   --data-raw '{"accessKey":"minio-user","secretKey":"minio-password"}'   --compressed


docker compose up --build --scale frontend=0

ssh -L 8080:192.168.7.105:8080 vmuser@192.168.7.105
ssh -t vmuser@192.168.7.105 "cd twitter-spring-reactjs; exec \$SHELL -l"
scp ./docker-compose.yml vmuser@192.168.7.105:~/twitter-spring-reactjs/