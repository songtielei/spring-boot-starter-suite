FROM harbor.ui-tech.cn/openjdk:8-alpine-font-timezone-1.0.1
VOLUME /app
ADD target/*.jar app.jar
ENTRYPOINT java -XX:+UseContainerSupport -XX:MaxRAMPercentage=75.0 -Djava.security.egd=file:/dev/./urandom -jar /app.jar
