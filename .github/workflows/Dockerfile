FROM openjdk

WORKDIR /app

COPY /target/parking-control-0.0.1-SNAPSHOT.jar /app/parking-control.jar

ENTRYPOINT ["java", "-jar", "parking-control.jar"]
