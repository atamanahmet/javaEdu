## 1. Which base image do you want to use?
FROM openjdk
## 2. Set the working directory inside the container.
WORKDIR /src/app
## 3. Copy your source code file to the working directory inside the container.
COPY JavaApp.jar .

EXPOSE 5000
## 4. Define the command to run when the container starts.
CMD [ "java","-cp","/src/app/JavaApp.jar", "JavaApp" ]