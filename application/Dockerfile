FROM adoptopenjdk/openjdk11:x86_64-alpine-jre-11.0.6_10
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} goku-ecommerce.jar
ENTRYPOINT ["java","-jar","/goku-ecommerce.jar"]