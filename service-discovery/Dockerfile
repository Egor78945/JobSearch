ARG JAVA_VERSION

FROM openjdk:${JAVA_VERSION}

WORKDIR /app

COPY build/libs/service-discovery-0.0.1-SNAPSHOT.jar /app/app.jar

ENTRYPOINT ["java", "-jar", "app.jar"]