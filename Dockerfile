FROM maven:3-eclipse-temurin-21
COPY . /usr/src/
WORKDIR /usr/src/
RUN mvn clean package
CMD ["java", "-jar", "target/directory-1.1.jar"]
