FROM openjdk:11

EXPOSE 8080

ADD build/libs/DSM-TCG-Backend-0.0.1-SNAPSHOT.jar DSM-TCG-Backend-0.0.1-SNAPSHOT.jar

ENTRYPOINT ["java","-jar","DSM-TCG-Backend-0.0.1-SNAPSHOT.jar"]