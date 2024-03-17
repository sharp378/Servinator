FROM amazoncorretto:21-alpine-jdk

WORKDIR /tmp
COPY ./servinator-plugin/ .

RUN mkdir app/run \
  && echo 'eula=true' > app/run/eula.txt

ENV INTERVAL=1

#RUN ./gradlew shadowJar --no-daemon
#ENTRYPOINT ["./gradlew", "runServer"]
ENTRYPOINT ["sh"]
