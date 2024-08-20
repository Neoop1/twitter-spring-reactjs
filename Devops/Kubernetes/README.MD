### Create one File manifests


find ./Kube-manifests/manifests-twitter-site/ -type f -printf '%h\0%d\0%p\n' | sort -t '\0' -n | awk -F '\0' '{print $3}' | while read file; do
    xargs -I{} -t find {} -exec printf '---\n' {} \; -exec cat {} \; -exec printf '\n' \; >> ./complete-demo_$(date +%Y-%m-%d_%H:%M).yaml 2>/dev/null
done



### Kube Steps 
kubectl create ns twitter-website

# Delete old Files (!)

kubectl apply -f ./complete-demo*.yaml 






## External IP
Service Exposure in on-premises Kubernetes cluster using Ingress Controller
https://medium.com/@tanmaybhandge/simplifying-service-exposure-in-on-premises-kubernetes-cluster-using-ingress-controller-39ed8cd9fa93


kubectl create -f https://raw.githubusercontent.com/tanmaybhandge/Nginx_ingress_Controller_Metallb/main/metallb.yaml
kubectl apply -f https://raw.githubusercontent.com/kubernetes/ingress-nginx/controller-v1.1.3/deploy/static/provider/cloud/deploy.yaml
kubectl create -f https://raw.githubusercontent.com/tanmaybhandge/Nginx_ingress_Controller_Metallb/main/ip-adress-pool.yaml




## deltet all Terminating pods
for p in $(kubectl get pods | grep Terminating | awk '{print $1}'); do kubectl delete pod $p --grace-period=0 --force;done



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



-Temp
###
find ./Kube-manifests/manifests-twitter-site/ -type f -exec printf '---\n' {} \; -exec cat {} \; -exec printf '\n' \; >> ./complete-demo.yaml 2>/dev/null
####


####
find ./Kube-manifests/manifests-twitter-site/ -type f -printf '%h\0%d\0%p\n' | sort -t '\0' -n | awk -F '\0' '{print $3}' | while read file; do
    xargs cat >> ./complete-demo.yaml 2>/dev/null
done
####