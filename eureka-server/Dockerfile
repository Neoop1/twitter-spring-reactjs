FROM openjdk:17
LABEL MAINTAINER Miroslav Khotinskiy <merikbest2015@gmail.com>
WORKDIR /app
COPY target/eureka-server-0.0.1-SNAPSHOT.jar .
COPY wait-for-it.sh .
RUN chmod +x ./wait-for-it.sh
ARG ZIPKIN_HOST=zipkin
ENTRYPOINT ["./wait-for-it.sh", "-t", "30" ,"-s" , "${ZIPKIN_HOST}:9411", "--", "java", "-jar", "eureka-server-0.0.1-SNAPSHOT.jar"]
#CMD ["java", "-jar", "eureka-server-0.0.1-SNAPSHOT.jar"]
