### Create one File manifests


find ./Kube-manifests/manifests-twitter-site/ -type f -printf '%h\0%d\0%p\n' | sort -t '\0' -n | awk -F '\0' '{print $3}' | while read file; do
    xargs -I{} -t find {} -exec printf '---\n' {} \; -exec cat {} \; -exec printf '\n' \; >> ./complete-demo_$(date +%Y-%m-%d_%H:%M).yaml 2>/dev/null
done



### Kube Steps 
kubectl create ns twitter-website

kubectl apply -f ./complete-demo*.yaml # Delete old Files






## External IP
Service Exposure in on-premises Kubernetes cluster using Ingress Controller
https://medium.com/@tanmaybhandge/simplifying-service-exposure-in-on-premises-kubernetes-cluster-using-ingress-controller-39ed8cd9fa93


kubectl create -f https://raw.githubusercontent.com/tanmaybhandge/Nginx_ingress_Controller_Metallb/main/metallb.yaml
kubectl create -f https://raw.githubusercontent.com/tanmaybhandge/Nginx_ingress_Controller_Metallb/main/ip-adress-pool.yaml
kubectl apply -f https://raw.githubusercontent.com/kubernetes/ingress-nginx/controller-v1.1.3/deploy/static/provider/cloud/deploy.yaml



## deltet all Terminating pods
for p in $(kubectl get pods | grep Terminating | awk '{print $1}'); do kubectl delete pod $p --grace-period=0 --force;done








-Temp
###
find ./Kube-manifests/manifests-twitter-site/ -type f -exec printf '---\n' {} \; -exec cat {} \; -exec printf '\n' \; >> ./complete-demo.yaml 2>/dev/null
####


####
find ./Kube-manifests/manifests-twitter-site/ -type f -printf '%h\0%d\0%p\n' | sort -t '\0' -n | awk -F '\0' '{print $3}' | while read file; do
    xargs cat >> ./complete-demo.yaml 2>/dev/null
done
####