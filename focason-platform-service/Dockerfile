# =====================================================
# Copyright 2025 Focason Co.,Ltd. AllRights Reserved.
# =====================================================
FROM openjdk:17-jdk-alpine
LABEL maintainer="Focason Lab Team"

EXPOSE 8081
ADD build/libs/focason-platform-service-1.0.0-SNAPSHOT-plain.jar focason-platform-service.jar
ENTRYPOINT ["java","-jar","/focason-platform-service.jar"]
