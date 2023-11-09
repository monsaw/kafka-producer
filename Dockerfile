FROM openjdk:17
EXPOSE 8080
ADD target/kafka-producer.jar kafka-producer.jar
ENTRYPOINT ["java" , "-jar" , "kafka-producer.jar"]