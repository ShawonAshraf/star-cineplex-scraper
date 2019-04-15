FROM gradle:5.3-jdk11-slim as builder
COPY --chown=gradle:gradle . /home/scraper
WORKDIR /home/scraper
RUN gradle fatJar

FROM openjdk:11-jdk-slim-stretch
COPY --from=builder /home/scraper/build/libs/datascrapper-all-1.0-SNAPSHOT.jar /app/
WORKDIR /app
CMD ["java", "-jar", "datascrapper-all-1.0-SNAPSHOT.jar"]
