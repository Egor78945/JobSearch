ARG JAVA_VERSION

FROM openjdk:${JAVA_VERSION}

WORKDIR /app

COPY ./cert/russian_trusted_root_ca_pem.crt /tmp/cert.crt

RUN keytool -import -alias sber_root_ca -keystore $JAVA_HOME/lib/security/cacerts \
    -file /tmp/cert.crt -storepass changeit -noprompt

RUN rm /tmp/cert.crt

COPY build/libs/vacancy-manager-service-0.0.1-SNAPSHOT.jar /app/app.jar

ENTRYPOINT ["java", "-jar", "app.jar"]