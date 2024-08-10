


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






# Host
127.0.0.1 Twitter.local

# Run Site Without web security (Chrome browser without CORS)
# Linux
chromium --disable-web-security --user-data-dir=./ChromeDevSession
# Windows 
"C:\Program Files\Google\Chrome\Application\chrome.exe" --disable-web-security --user-data-dir="C:\tmpChromeSession"


# kubernetes/ingress logs

kubectl describe ing





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
ssh -t vmuser@192.168.7.87 "cd twitter-spring-reactjs; exec \$SHELL -l"
scp ./docker-compose.yml vmuser@192.168.7.87:~/twitter-spring-reactjs/

scp ./docker-stack.yml  vmuser@192.168.7.87:~/twitter-spring-reactjs/


