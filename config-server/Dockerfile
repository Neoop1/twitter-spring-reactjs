FROM openjdk:17
LABEL MAINTAINER Miroslav Khotinskiy <merikbest2015@gmail.com>
WORKDIR /app
COPY target/config-server-0.0.1-SNAPSHOT.jar .
COPY wait-for-it.sh .
RUN chmod +x ./wait-for-it.sh
ENTRYPOINT ["./wait-for-it.sh", "-t", "30" ,"-s" , "${EUREKA_HOST}:8761", "--", "java", "-jar", "config-server-0.0.1-SNAPSHOT.jar"]
#CMD ["java", "-jar", "config-server-0.0.1-SNAPSHOT.jar"]
