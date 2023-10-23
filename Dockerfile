FROM openjdk:17-oracle
RUN mkdir -p /logs

ENV	PROFILE default
ENV TZ=Asia/Seoul
EXPOSE 8080

ARG JAVA_OPTS

ARG RELEASE_VERSION
ENV DD_VERSION=${RELEASE_VERSION}

ARG JAR_FILE="build/libs/goalajuma-*.jar"

COPY . .

RUN chmod -R 777 resources/local-develop-environment/mysql-init.d/

ENTRYPOINT java -XX:MaxGCPauseMillis=100 -XX:InitialRAMPercentage=50.0 -XX:MaxRAMPercentage=80.0 -agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=*:5005 -Dspring.profiles.active=dev -jar build/libs/goalajuma-0.0.1-SNAPSHOT.jar