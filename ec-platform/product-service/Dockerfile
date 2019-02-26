FROM java:8-jre

ADD ./target/product-service.jar /app/
CMD ["java", "-Xmx200m", "-jar", "/app/product-service.jar"]

EXPOSE 5000