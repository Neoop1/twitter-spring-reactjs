### Create one File manifests

find ./manifests-twitter-site/ -type f -exec printf '---\n' {} \; -exec cat {} \; -exec printf '\n' \; >> ./complete-demo.yaml 2>/dev/null



### Kube Steps 
kubectl create ns twitter-website

kubectl apply -f ./complete-demo.yaml