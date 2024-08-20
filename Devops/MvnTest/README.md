# Host
127.0.0.1 Twitter.local

# Run Site Without web security (Chrome browser without CORS)
# Linux
chromium --disable-web-security --user-data-dir=./ChromeDevSession
# Windows 
"C:\Program Files\Google\Chrome\Application\chrome.exe" --disable-web-security --user-data-dir="C:\tmpChromeSession"


# kubernetes/ingress logs

kubectl describe ing





## MVN Test - steps


## Docker containers for test environment ##
docker run -d  --rm --name zipkin-test --net=host -p 9411:9411 openzipkin/zipkin

docker run -d --rm --net=host --name postgres-test --env POSTGRES_DB=user --env POSTGRES_USER=postgres \
     --env PGUSER=postgres --env POSTGRES_PASSWORD=root --env PGDATA=/data/postgres \
     -v ./postgresscript_test/:/docker-entrypoint-initdb.d \
     -p 5432:5432 postgres:latest

docker run -d --rm --name zookeeper-test --net=host --env ZOOKEEPER_CLIENT_PORT=2181 \
     --env KAFKA_OPTS='-Dzookeeper.admin.enableServer=false' --env ZOOKEEPER_TICK_TIME=2000 -p 2181:2181 confluentinc/cp-zookeeper:7.0.1

docker run -d --rm --name rabbitmq-test --net=host -p 5672:5672 -p 15672:15672  rabbitmq:3.9.11-management-alpine

docker run -d --name kafka-test -p 9092:9092  --net=host --rm \
          --env KAFKA_BROKER_ID=1 \
          --env KAFKA_ADVERTISED_LISTENERS='PLAINTEXT://localhost:9092,PLAINTEXT_INTERNAL://broker:29092' \
          --env KAFKA_LISTENER_SECURITY_PROTOCOL_MAP='PLAINTEXT:PLAINTEXT,PLAINTEXT_INTERNAL:PLAINTEXT' \
          --env KAFKA_OFFSETS_TOPIC_REPLICATION_FACTOR=1 \
          --env KAFKA_TRANSACTION_STATE_LOG_MIN_ISR=1 \
          --env KAFKA_TRANSACTION_STATE_LOG_REPLICATION_FACTOR=1 \
          --env KAFKA_ZOOKEEPER_CONNECT='localhost:2181' confluentinc/cp-kafka:7.0.1

## Java 
mvn install -pl :eureka-server -am 
nohup java -jar ./eureka-server/target/eureka-server-*.jar &

mvn install -pl :config-server -am 
(sleep 30)
nohup java -jar ./config-server/target/config-server-*.jar &









## MVN TEST SERVICE ##

topic-service 
mvn test -pl :topic-service -am 

user-service 
mvn test -pl :user-service -am 
#####################
java -  -Dspring.profiles.active=test ./tweet-service/target/tweet-service-0.0.1-SNAPSHOT.
java -  -Dspring.profiles.active=test ./tag-service/target/tag-service-0.0.1-SNAPSHOT.
#####################

kill $(ps aux | grep 'tag-service-*.' | awk '{print $2}')


tweet-service 
mvn test -pl :tweet-service -am 
#####################
java -  -Dspring.profiles.active=test ./lists-service/target/lists-service-0.0.1-SNAPSHOT.
java -  -Dspring.profiles.active=test ./websocket-service/target/websocket-service-0.0.1-SNAPSHOT.
java -  -Dspring.profiles.active=test ./image-service/target/image-service-0.0.1-SNAPSHOT.
#####################

tag-service +
mvn test -pl :tag-service -am 
#####################
java -  -Dspring.profiles.active=test ./tweet-service/target/tweet-service-0.0.1-SNAPSHOT.
#####################


mvn test -pl :lists-service -am
#####################
java -  -Dspring.profiles.active=test ./tweet-service/target/tweet-service-0.0.1-SNAPSHOT.
#####################


chat-service +
mvn test -pl :chat-service -am
#####################
java -  -Dspring.profiles.active=test ./tweet-service/target/tweet-service-0.0.1-SNAPSHOT.
java -  -Dspring.profiles.active=test ./websocket-service/target/websocket-service-0.0.1-SNAPSHOT.
#####################


notification-service 
mvn test -pl :notification-service  -am
#####################
java -  -Dspring.profiles.active=test ./websocket-service/target/websocket-service-0.0.1-SNAPSHOT.
java -  -Dspring.profiles.active=test ./tweet-service/target/tweet-service-0.0.1-SNAPSHOT.
java -  -Dspring.profiles.active=test ./user-service/target/user-service-0.0.1-SNAPSHOT.
#####################



docker stop kafka-test
docker stop rabbitmq-test
docker stop zookeeper-test
docker stop postgres-test
docker stop zipkin-test


## Mvn ####################
[INFO] twitter-spring-reactjs ............................. SUCCESS [  0.007 s]
[INFO] eureka-server ...................................... SUCCESS [  1.756 s] no test 
[INFO] config-server ...................................... SUCCESS [  0.183 s] no test 
[INFO] commons ............................................ SUCCESS [  0.171 s] no test 
[INFO] api-gateway ........................................ SUCCESS [  0.192 s] no test 
[INFO] email-service ...................................... SUCCESS [  0.142 s] no test
[INFO] image-service ...................................... SUCCESS [  0.271 s] no test 
[INFO] topic-service ...................................... SUCCESS [ 30.181 s]
[INFO] user-service ....................................... FAILURE [ 36.279 s]
[INFO] tweet-service ...................................... SKIPPED ---
[INFO] tag-service ........................................ SKIPPED ---
[INFO] lists-service ...................................... SKIPPED ---
[INFO] chat-service ....................................... SKIPPED ---
[INFO] notification-service ............................... SKIPPED ---
[INFO] websocket-service .................................. SKIPPED no test 
## Build MVN without tests build Jar 


mvn clean package -DskipTests


mvn install -pl :eureka-server -am            
java - ./eureka-server/target/eureka-server-0.0.1-SNAPSHOT.

nohup java - ./eureka-server/target/eureka-server-0.0.1-SNAPSHOT.       
            
mvn install -pl :config-server -am  
java - ./config-server/target/config-server-0.0.1-SNAPSHOT.
            
email-service
mvn install -pl :email-service -am

image-service
mvn install -pl :image-service -am 


####
java -  -Dspring.profiles.active=test ./topic-service/target/topic-service-0.0.1-SNAPSHOT.
#####


mvn install -pl :api-gateway  -am
java - ./api-gateway/target/api-gateway-0.0.1-SNAPSHOT.




127.0.0.1 tag-service
 127.0.0.1 tweet-service


java -  -Dspring.profiles.active=test ./websocket-service/target/websocket-service-0.0.1-SNAPSHOT.

 127.0.0.1 websocket-service 
 127.0.0.1 lists-service











##Errors test






docker run -p 8888:8888 merikbest/twitter-spring-reactjs:config-server
docker run -p 8761:8761 merikbest/twitter-spring-reactjs:eureka-server



curl -i 'http://192.168.7.93:50440/api/v1/login'   -H 'Connection: keep-alive'   -H 'User-Agent: Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/99.0.4844.83 Safari/537.36'   -H 'Content-Type: application/json'   -H 'Accept: */*'   -H 'Sec-GPC: 1'   -H 'Origin: http://192.168.7.93:50440'   -H 'Sec-Fetch-Site: same-origin'   -H 'Sec-Fetch-Mode: cors'   -H 'Sec-Fetch-Dest: empty'   -H 'Referer: http://192.168.7.93:50440/login'   -H 'Accept-Language: en-US,en;q=0.9'   -H 'Cookie: PGADMIN_LANGUAGE=en'   --data-raw '{"accessKey":"minio-user","secretKey":"minio-password"}'   --compressed




ssh -L 8080:192.168.7.105:8080 vmuser@192.168.7.105

ssh -t vmuser@192.168.7.87 "cd twitter-spring-reactjs; exec \$SHELL -l"

scp ./docker-compose.yml vmuser@192.168.7.87:~/twitter-spring-reactjs/
scp ./docker-stack.yml  vmuser@192.168.7.87:~/twitter-spring-reactjs/


cd /home/user1/Desktop/Tools/DevOps/DevopsTwitter/Git/twitter-spring-reactjs/







docker run -p 9092:9092  --rm \
          --env KAFKA_BROKER_ID=1 \
          --env KAFKA_ADVERTISED_LISTENERS='PLAINTEXT://localhost:9092' \
          --env KAFKA_LISTENER_SECURITY_PROTOCOL_MAP='PLAINTEXT:PLAINTEXT,PLAINTEXT_INTERNAL:PLAINTEXT' \
          --env KAFKA_OFFSETS_TOPIC_REPLICATION_FACTOR=1 \
          --env KAFKA_TRANSACTION_STATE_LOG_MIN_ISR=1 \
          --env KAFKA_TRANSACTION_STATE_LOG_REPLICATION_FACTOR=1 \
          --env KAFKA_ZOOKEEPER_CONNECT="localhost:2181" confluentinc/cp-kafka:7.0.1










          

mvn test -rf :topic-service

 mvn test -pl :topic-service

 mvn test -Dtest=topic-service -pl topic-service -am -DfailIfNoTests=false




 mvn test -Dtest=user-service -pl user-service -am -DfailIfNoTests=false




rabbitmq:
        image: 
        hostname: rabbitmq
        #deploy:
           #placement:
              #constraints:
                #- "node.role==worker"
        #ports:
        #    - ""
        #   - ""
        networks:
            - spring-swarm



            mvn test -Dtest=user-service
            
            
mvn test -pl :user-service -am

# -v ./postgres_test_test:/data/postgres

docker run  --env PGADMIN_DEFAULT_EMAIL=${PGADMIN_DEFAULT_EMAIL:-pgadmin4@pgadmin.org} --env PGADMIN_DEFAULT_PASSWORD=${PGADMIN_DEFAULT_PASSWORD:-admin} -p  5050:80 dpage/pgadmin4





























# Temp

nohup java -jar  -Dspring.profiles.active=test ./user-service/target/user-service-*.jar &
nohup java -jar  -Dspring.profiles.active=test ./tweet-service/target/tweet-service-*.jar &
nohup java -jar  -Dspring.profiles.active=test ./tag-service/target/tag-service-*.jar &
nohup java -jar  -Dspring.profiles.active=test ./lists-service/target/lists-service-*.jar &
nohup java -jar  -Dspring.profiles.active=test ./websocket-service/target/websocket-service-*.jar &
nohup java -jar  -Dspring.profiles.active=test ./image-service/target/image-service-*.jar &


test config service 

java -jar ./eureka-server/target/eureka-server-*.jar
java -jar ./config-server/target/config-server-*.jar
java -jar  -Dspring.profiles.active=test ./user-service/target/user-service-*.jar &
java -jar  -Dspring.profiles.active=test ./tweet-service/target/tweet-service-*.jar &
java -jar  -Dspring.profiles.active=test ./tag-service/target/tag-service-*.jar &
java -jar  -Dspring.profiles.active=test ./chat-service/target/chat-service-*.jar &
java -jar  -Dspring.profiles.active=test ./notification-service/target/notification-service-*.jar &
java -jar  -Dspring.profiles.active=test ./topic-service/target/topic-service-*.jar &
java -jar  -Dspring.profiles.active=test ./lists-service/target/lists-service-*.jar &
java -jar  -Dspring.profiles.active=test ./websocket-service/target/websocket-service-*.jar &






sonarqube 

docker run -d --rm --env POSTGRES_DB=sonarqube --env POSTGRES_USER=sonarqube \
     --env PGUSER=sonarqube --env POSTGRES_PASSWORD=SecurePassword --env PGDATA=/data/postgres \
     -p 5432:5432 postgres:latest

wget https://binaries.sonarsource.com/Distribution/sonarqube/sonarqube-9.2.2.50622.zip



# Stop all the containers

docker stop $(docker ps -a -q)

# Remove all the containers

docker rm $(docker ps -a -q)


Analyze "Twitter-WebSite": sqp_8fe50e5243edbd90c95e59d9b80bb0aca21c2c13

mvn clean package -DskipTests verify sonar:sonar \
  -Dsonar.projectKey=Twitter-WebSite \
  -Dsonar.projectName='Twitter-WebSite' \
  -Dsonar.host.url=http://192.168.7.87:9000 \
  -Dsonar.token=sqp_8fe50e5243edbd90c95e59d9b80bb0aca21c2c13



timeout(5) {
    waitUntil {
       script {
         def r = sh script: 'wget -q http://remoterhoste/welcome.jsf -O /dev/null', returnStdout: true
         return (r == 0);
       }
    }
}




Pipline {
    agent any
    timeout(time: 15, unit: 'SECONDS') {
        stage('Check Availability') {
          steps {             
              waitUntil {
                  try {         
                      sh "curl -s --head  --request GET  localhost:8081/actuator/health | grep '200'"
                      return true
                  } catch (Exception e) {
                        return false
                  }
              }
           }
       }
    }
}


kill $(ps aux | grep 'java config-server' | awk '{print $2}')



        stage("postgres - close / clear environment before test "){
            steps{
                script{
                   try {
                       sh "docker stop test-postgres" //  will stop postgres docker container !!!
                       sh "docker rm test-postgres" //  will stop postgres docker container !!!
                    }
                    catch (err) {
                          echo err.getMessage()
                          echo "Error detected, but we will continue."
                    }
                }
            }
        }


steps ('Check ceureka-server Availability') {
    sh "nohup java - ./eureka-server/target/eureka-server-*. &
    waitUntil(initialRecurrencePeriod: 15000) {
        script {
            def status = sh "curl --fail --silent localhost:8761/actuator/health | grep UP"
            if ( status =~ "UP") {
                return true
            }else {
                println("Server not Availabil yet!")
                return false
            }
        }
    }
}


docker run \
     --env PGADMIN_DEFAULT_EMAIL=${PGADMIN_DEFAULT_EMAIL:-pgadmin4@pgadmin.org} \
     --env PGADMIN_DEFAULT_PASSWORD=${PGADMIN_DEFAULT_PASSWORD:-admin} \
     --env PGADMIN_CONFIG_SERVER_MODE='False' \
     -p 5050:80 dpage/pgadmin4


kill $(ps aux | grep 'eureka-server-*' | awk '{print $2}') 2> /dev/null 
kill $(ps aux | grep 'config-server-*' | awk '{print $2}') 2> /dev/null 
kill $(ps aux | grep 'image-service-*' | awk '{print $2}') 2> /dev/null
kill $(ps aux | grep 'topic-service-*' | awk '{print $2}') 2> /dev/null 
kill $(ps aux | grep 'user-service-*' | awk '{print $2}')  2> /dev/null 
kill $(ps aux | grep 'tweet-service-*' | awk '{print $2}') 2> /dev/null 
kill $(ps aux | grep 'tag-service-*' | awk '{print $2}')   2> /dev/null 
kill $(ps aux | grep 'lists-service-*' | awk '{print $2}') 2> /dev/null 
kill $(ps aux | grep 'chat-service-*' | awk '{print $2}')  2> /dev/null
kill $(ps aux | grep 'notification-service-*' | awk '{print $2}') 2> /dev/null 
kill $(ps aux | grep 'websocket-service-*' | awk '{print $2}')    2> /dev/null 
'


        stage('Check eureka-server Availability') {
            options {
                timeout(time: 30, unit: 'SECONDS') 
            }
            steps {
                sh '''export JENKINS_NODE_COOKIE=dontKillMe && nohup java -jar ./eureka-server/target/eureka-server-*.jar & '''
                waitUntil(initialRecurrencePeriod: 15000) {
                    script {
                        def status =  sh(script: ''' curl --fail --silent localhost:8761/actuator/health | grep UP ''', returnStdout: true)
                        if ( status =~ status.find('"UP"') ){
                            return true                      
                        }else {
                            println("Server not Availabil yet!")
                            return false
                        }
                    }
                }
            }
        }


        
        stage("test environment"){
            options {
                timeout(time: 30, unit: 'SECONDS') 
            }
            steps {
                sh ''' nohup java -jar ./eureka-server/target/eureka-server-*.jar & '''
                waitUntil(initialRecurrencePeriod: 15000) {
                    script {
                        def status = sh (script: ''' sleep 15 && wget -T5 -qO-  http://localhost:8761/actuator/health | grep UP ''', returnStdout: true)
                        if ( status =~ status.find('"UP"') ){
                            return true
                        }else {
                            println("no hello yet!")
                            return false
                        }
                    }
                }
            }
        }

steps {
    sh "echo something > statusfile" // do something in the shell
    waitUntil(initialRecurrencePeriod: 15000) {
        script {
            def status = readFile(file: "statusfile")
            if ( status =~ "hello") {
                return true
            }else {
                println("no hello yet!")
                return false
            }
        }
    }
}


wget -nv -t1 --spider
 wget localhost:8080 ./docs/
 wget localhost:8761 --directory-prefix=/actuator/health --no-host-directories --recursive --convert-links --adjust-extension



 wget -T5 -qO- http://localhost:8080/actuator/health | grep UP



 retry(count: 3) {
    try {
        echo "Sleeping for 1 minute since e2e build is running..."
        sleep(time: 5, unit: 'SECONDS')
        
    } catch(e) {
        if(Jenkins.instance.getItem('test').getLastBuild().getExecution().isComplete()) {
            // Let's error out the the condition is true which will trigger a retry
            throw e
        }
        echo "Condition failed, means no retry, so complete the Job" 
    }
  
}


timeout(5) {
    waitUntil {
       script {
         def r = sh script: 'wget -q http://remoterhoste/welcome.jsf -O /dev/null', returnStdout: true
         return (r == 0);
       }
    }
}





Since version 1.3 of Declarative Pipeline plugin, this is officially supported. It's officially called "Sequential Stages".

pipeline {
    agent none

    stages {
        stage("check code style") {
            agent {
                docker "code-style-check-image"
            }
            steps {
                sh "./check-code-style.sh"
            }
        }

        stage("build and test the project") {
            agent {
                docker "build-tools-image"
            }
            stages {
               stage("build") {
                   steps {
                       sh "./build.sh"
                   }
               }
               stage("test") {
                   steps {
                       sh "./test.sh"
                   }
               }
            }
        }
    }
}
