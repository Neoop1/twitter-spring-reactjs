# Montoring

First create the monitoring namespace using the `00-monitoring-ns.yaml` file:

`$ kubectl create -f 00-monitoring-ns.yaml`

### kube-state

`kubectl apply $(ls *-kube-state-*.yaml | awk ' { print " -f " $1 } ')`

### Prometheus

To deploy simply apply all the prometheus manifests (01-10) in any order:

`kubectl apply $(ls *-prometheus-*.yaml | awk ' { print " -f " $1 } ')`

The prometheus server will be exposed on Nodeport `31090`.

### Grafana Config-map

`kubectl apply $(ls *-config-map-*.yaml | awk ' { print " -f " $1 }'  | grep -v grafana-import)`

### Grafana

grafana manifests

`kubectl apply $(ls *-grafana-*.yaml | awk ' { print " -f " $1 }'  | grep -v grafana-import)`


Grafana will be exposed on the NodePort `31300` 

@@ Use Lens
