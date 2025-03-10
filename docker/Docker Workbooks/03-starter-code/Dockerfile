## 1. Which base image do you want to use?
FROM maven:3.6.3-openjdk-17-slim
## 2. Set the working directory inside the container.
WORKDIR /src/app
## 3. Copy the application's project files into the working directory.
COPY springboot-demo .
## 4. Document and inform the developer that the application will use the container port: 8080.
EXPOSE 8080
## 5. Define the command to run when the container starts.
CMD ["mvn","clean","spring-boot:run"]