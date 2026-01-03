# =====================================================
# Copyright 2025 Focason Co.,Ltd. AllRights Reserved.
# =====================================================
FROM amazoncorretto:17
LABEL maintainer="Focason Lab Team"

ENV SERVER_PORT=80
ENV APPLICATION_NAME=focason-gateway
ENV ENVIRONMENT=development
ENV EUREKA_HOSTNAME=focason-gateway

EXPOSE 80
ADD build/libs/focason-gateway-1.0.0-SNAPSHOT.jar focason-gateway.jar
ENTRYPOINT ["java","-jar","/focason-gateway.jar"]
