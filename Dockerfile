FROM amazoncorretto:21-alpine-jdk

WORKDIR /tmp
COPY . .

RUN mkdir ServinatorPlugin/run \
  && echo 'eula=true' > ServinatorPlugin/run/eula.txt

ENV INTERVAL=1

RUN ./gradlew shadowJar --no-daemon
ENTRYPOINT ["./gradlew", "runServer"]
