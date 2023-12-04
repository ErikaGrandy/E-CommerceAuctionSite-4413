FROM maven:3.8.5-openjdk-17
ARG JAR_FILE=target/*.jar
COPY BidSphereForwardBidding-0.0.1-SNAPSHOT.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar"]

# docker build -t bidsphereuser .
