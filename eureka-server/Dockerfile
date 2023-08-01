# Stage 1: building
FROM openjdk:17-slim AS build

LABEL maintainer="Andrei Marshalov <and.marsh61@gmail.com>"

WORKDIR /usr/app/

COPY *.gradle *.gradle.kts gradlew ./
COPY gradle gradle
RUN ./gradlew --version

COPY src src
RUN ./gradlew bootJar

# Unpackage jar file
RUN mkdir -p build/dependency && (cd build/dependency; jar -xf ../libs/*-SNAPSHOT.jar)

# Stage 2: running
FROM openjdk:17-slim

# Add volume pointing to /tmp because that is where a Spring Boot application creates working directories for Tomcat by default.
VOLUME /tmp

# Copy unpackaged application to new container
ARG DEPENDENCY=/usr/app/build/dependency
COPY --from=build ${DEPENDENCY}/BOOT-INF/lib /app/lib
COPY --from=build ${DEPENDENCY}/META-INF /app/META-INF
COPY --from=build ${DEPENDENCY}/BOOT-INF/classes /app

# Execute the application
ENTRYPOINT ["java","-cp","app:app/lib/*","com.optimagrowth.eurekaserver.EurekaServerApplicationKt"]
