# =====================================================
# Copyright 2025 Focason Co.,Ltd. AllRights Reserved.
# =====================================================
FROM openjdk:17-jdk-alpine
LABEL maintainer="Focason Lab Team"

EXPOSE 8081
ADD build/libs/base-service-1.0.0-SNAPSHOT-plain.jar base-service.jar
ENTRYPOINT ["java","-jar","/base-service.jar"]
