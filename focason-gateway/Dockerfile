# =====================================================
# Copyright 2025 Focason Co.,Ltd. AllRights Reserved.
# =====================================================
FROM openjdk:17-jdk-alpine
LABEL maintainer="Focason Lab Team"

ENV SERVER_PORT=80
ENV APPLICATION_NAME=focason-gateway
ENV ENVIRONMENT=development
ENV EUREKA_HOSTNAME=focason-gateway

EXPOSE 80
ADD build/libs/gateway-service-1.0.0-SNAPSHOT.jar gateway-service.jar
ENTRYPOINT ["java","-jar","/gateway-service.jar"]
