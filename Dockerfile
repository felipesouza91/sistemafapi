
FROM openjdk:13.0.2-jdk-slim
RUN mkdir /usr/app
ARG JAR_FILE
COPY ./target/${JAR_FILE} /usr/app/app.jar
#COPY --from=builder /usr/src/app/target/*.jar /usr/app/app.jar
WORKDIR /usr/app
EXPOSE 8080
CMD ["java","-jar" ,"app.jar"]