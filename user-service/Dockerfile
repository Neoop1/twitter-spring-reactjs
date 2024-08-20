FROM eclipse-temurin:17-jre AS builder
WORKDIR /app
COPY ./target/user-service-*.jar user-service.jar
RUN java -Djarmode=layertools -jar user-service.jar extract


FROM eclipse-temurin:17-jre
LABEL MAINTAINER="Miroslav Khotinskiy <merikbest2015@gmail.com>"
LABEL Version="0.0.1"
LABEL Description="Spring Boot - User-service"
WORKDIR /app
COPY --from=builder /app/dependencies/ ./
COPY --from=builder /app/spring-boot-loader/ ./
COPY --from=builder /app/snapshot-dependencies/ ./
COPY --from=builder /app/application/ ./

COPY wait-for-it.sh .
RUN chmod +x ./wait-for-it.sh

ENTRYPOINT ["./wait-for-it.sh", "-t", "30" ,"-s" , "config-server:8888", "--","-XX:MaxRAMPercentage=85","org.springframework.boot.loader.JarLauncher"]





#Temp
#use it before springboot 3.2
#ENTRYPOINT ["java" , "org.springframework.boot.loader.JarLauncher"]
#use it for after springboot 3.2
#ENTRYPOINT ["java" , "org.springframework.boot.loader.launch.JarLauncher"]














