ARG JAVA_VERSION

FROM openjdk:${JAVA_VERSION}

WORKDIR /app

COPY build/libs/database-manager-0.0.1-SNAPSHOT.jar /app/app.jar
COPY build/generated-src/jooq /app/generated-src/jooq

ENTRYPOINT ["java", "-jar", "app.jar"]