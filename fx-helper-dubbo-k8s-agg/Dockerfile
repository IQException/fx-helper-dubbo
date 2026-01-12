FROM amazoncorretto:23-alpine

ENV SPRING_OUTPUT_ANSI_ENABLED=ALWAYS \
    JAVA_OPTS="" \
    PORT=8080

ADD /target/*.jar /fx-helper-agg.jar

ENTRYPOINT ["sh", "-c", "java $JAVA_OPTS -jar /fx-helper-agg.jar "]

EXPOSE $PORT