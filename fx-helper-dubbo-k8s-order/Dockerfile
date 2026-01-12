FROM amazoncorretto:23-alpine

ENV SPRING_OUTPUT_ANSI_ENABLED=ALWAYS \
    JAVA_OPTS="" \
    PORT=8080

ADD /target/*.jar /fx-helper-order.jar

ENTRYPOINT ["sh", "-c", "java $JAVA_OPTS -jar /fx-helper-order.jar "]

EXPOSE $PORT