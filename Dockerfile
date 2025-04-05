FROM openjdk:21-jdk

WORKDIR /app

COPY target/product-0.0.1-SNAPSHOT.jar /app/product.jar

EXPOSE 8018

CMD ["java", "-jar", "/app/product.jar"]
