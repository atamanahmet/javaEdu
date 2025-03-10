FROM maven:3.9.9-eclipse-temurin-17
WORKDIR /src/app
COPY global-superstore .
EXPOSE 8080
CMD [ "mvn","clean","spring-boot:run"]