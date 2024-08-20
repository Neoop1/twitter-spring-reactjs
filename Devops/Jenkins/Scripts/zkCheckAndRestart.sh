#!/usr/bin/env bash
# https://gist.github.com/cguegi/04d1ac65837a7fa6d892f96fa2b326b7
# Despite healthy state in Ambari, single Zookeeper servers are unable to join the quorum.
# See https://issues.apache.org/jira/browse/ZOOKEEPER-2938 for more details.
#
# zkCheckAndRestart.sh uses the Ambari REST API to determine if Zookeeper is healthy
# by starting a service check. In case of an unhealthy Zookeeper quorum,
# the script uses the Ambari REST API to trigger a rolling restart of the ZK servers.

#set -x

USER=$1
PASS=$2
# TODO: add your cluster name
CLUSTER=<your_cluster_name>
AMBARI_HOST=$(hostname -f)
COMPONENT_NAME=ZOOKEEPER_SERVER
SERVICE_NAME=ZOOKEEPER
PORT=8080
CURL_CMD="curl -s -u $USER:$PASS"
API_BASE="http://${AMBARI_HOST}:${PORT}/api/v1/clusters"

# check zk service state
# state=STARTED --> all zk servers are running
# state=INSTALLED --> one or more zk servers stopped
result=`$CURL_CMD -H 'X-Requested-By: ambari' -X GET $API_BASE/$CLUSTER/services/$SERVICE_NAME?fields=ServiceInfo/state | jq .ServiceInfo.state`
echo "[`date "+%Y-%m-%d %H:%M:%S"`] Zookeeper service state: ${result}"

# run zookeeper service check
echo "[`date "+%Y-%m-%d %H:%M:%S"`] Running ZK service check..."
requestid=`$CURL_CMD -H 'X-Requested-By: ambari' -X POST -d '{"RequestInfo":{"context":"ZK Service Check","command":"ZOOKEEPER_QUORUM_SERVICE_CHECK"},\
"Requests/resource_filters": [{"service_name":"ZOOKEEPER"}]}' $API_BASE/$CLUSTER/requests | jq .Requests.id`
echo "[`date "+%Y-%m-%d %H:%M:%S"`] serivce check request id: $requestid"
# wait 5 seconds until ambari started service check
sleep 5

count=0
LIMIT=6

while [ $count -lt $LIMIT ];
do
    # check progress
    result=`$CURL_CMD -X GET $API_BASE/$CLUSTER/requests/$requestid | jq .Requests.request_status`
    echo "[`date "+%Y-%m-%d %H:%M:%S"`] restart status: $result"
    if [[ "$result" == '"FAILED"' ]] || [[ "$result" == '"COMPLETED"' ]];
    then
        break
    else
        count=$((count+1))
    fi
    sleep 10
    echo "[`date "+%Y-%m-%d %H:%M:%S"`] waiting 10 sec..."
done

if [[ $count -gt $LIMIT ]];
then
    echo "[`date "+%Y-%m-%d %H:%M:%S"`] ERROR: unable to connect to Ambari API"
    exit 1
fi

echo "[`date "+%Y-%m-%d %H:%M:%S"`] ZK service check: $result"

# restart zk server in case service check failed
if [[ $result == '"FAILED"' ]];
then
    # TODO: add your zk hostnames here
    zk_hostnames=(<host_A> <host_B> <host_C>)
    echo "[`date "+%Y-%m-%d %H:%M:%S"`] service check failed, restarting  ZooKeeper"
    for i in "${zk_hostnames[@]}";
    do
        echo "[`date "+%Y-%m-%d %H:%M:%S"`] Restarting Zookeeper server on ${i}"
        output=`$CURL_CMD -H "X-Requested-By: ambari" -X POST -d '{"RequestInfo":{"command":"RESTART","context":"Restart ZK"},\
        "Requests/resource_filters":[{"service_name":"ZOOKEEPER","component_name":"'"$COMPONENT_NAME"'","hosts":"'"$i"'"}]}' $API_BASE/$CLUSTER/requests`
        # wait 15 seconds
        sleep 15
    done
    echo "[`date "+%Y-%m-%d %H:%M:%S"`] ZK restart done"
fi

exit 0
