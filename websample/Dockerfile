FROM openjdk:8u275

COPY ./build/libs/websample-0.0.1-SNAPSHOT.jar /usr/src
WORKDIR /usr/src

EXPOSE 8080 9111

CMD ["java", "-jar", "websample-0.0.1-SNAPSHOT.jar"]
