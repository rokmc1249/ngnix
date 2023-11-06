FROM openjdk:17
WORKDIR /root
COPY ./team2project-0.0.1-SNAPSHOT.jar .
CMD java -jar -Dspring.profiles.active=${active} team2project-0.0.1-SNAPSHOT.jar