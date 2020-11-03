FROM gradle:jdk14 AS build-env
ADD . /app
WORKDIR /app
RUN gradle shadowJar

FROM openjdk:14-jdk-slim
COPY --from=build-env /app/build/libs/pokemons.jar /app/pokemons.jar
WORKDIR /app
EXPOSE 4567/tcp
ENTRYPOINT ["java", "-jar", "pokemons.jar"]
