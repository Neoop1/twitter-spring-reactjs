FROM openjdk:17
LABELMAINTAINER Miroslav Khotinskiy <merikbest2015@gmail.com>
WORKDIR /app
COPY target/websocket-service-0.0.1-SNAPSHOT.jar .
COPY wait-for-it.sh .
RUN chmod +x ./wait-for-it.sh
ENTRYPOINT ["./wait-for-it.sh", "-t", "30" ,"-s" , "${CONFIG_SERVER_HOST}:8888", "--", "java", "-jar", "websocket-service-0.0.1-SNAPSHOT.jar"]
#CMD ["java", "-jar", "websocket-service-0.0.1-SNAPSHOT.jar"]
