FROM openjdk:17
WORKDIR /
ADD target/mytodo-1.0-SNAPSHOT.jar app.jar
RUN useradd -m myuser
USER myuser
EXPOSE 8089
CMD java -jar app.jar
