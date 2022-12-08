FROM eclipse-temurin:11.0.17_8-jre-ubi9-minimal

# Default to UTF-8 file.encoding
ENV LANG C.UTF-8

RUN mkdir -p /opt
ADD maven/opt/waste-spring.jar /opt/waste-spring.jar


EXPOSE 8081

ENTRYPOINT java -jar /opt/waste-spring.jar -Djava.net.preferIPv4Stack=true