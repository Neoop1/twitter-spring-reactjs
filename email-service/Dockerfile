FROM openjdk:17
MAINTAINER Miroslav Khotinskiy <merikbest2015@gmail.com>
WORKDIR /app
COPY target/email-service-0.0.1-SNAPSHOT.jar .
COPY wait-for-it.sh .
RUN chmod +x ./wait-for-it.sh
ENTRYPOINT ["./wait-for-it.sh", "-t", "30" ,"-s" , "config-server:8888", "--", "java", "-jar", "email-service-0.0.1-SNAPSHOT.jar"]
#CMD ["java", "-jar", "email-service-0.0.1-SNAPSHOT.jar"]


    
