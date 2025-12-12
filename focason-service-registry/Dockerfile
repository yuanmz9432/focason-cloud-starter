# =====================================================
# Copyright 2025 Focason Co.,Ltd. AllRights Reserved.
# =====================================================
FROM openjdk:17-jdk-alpine
LABEL maintainer="Focason Lab Team"

EXPOSE 8761
ADD build/libs/focason-service-registry-1.0.0-SNAPSHOT.jar focason-service-registry.jar
ENTRYPOINT ["java","-jar","/focason-service-registry.jar"]
