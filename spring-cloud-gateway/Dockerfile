FROM amazoncorretto:23-alpine

ENV SPRING_OUTPUT_ANSI_ENABLED=ALWAYS \
    JAVA_OPTS="" \
    PORT=8080

ADD /target/*.jar /spring-cloud-gateway-nacos.jar

ENTRYPOINT ["sh", "-c", "java $JAVA_OPTS -jar /spring-cloud-gateway-nacos.jar "]

EXPOSE $PORT