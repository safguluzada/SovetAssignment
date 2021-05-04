FROM openjdk:11
EXPOSE 8080
ADD target/assignment4.jar assignment4.jar
ENTRYPOINT ["java","-jar","/assignment4.jar"]