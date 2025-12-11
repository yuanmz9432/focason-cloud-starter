# =====================================================
# Copyright 2025 Focason Co.,Ltd. AllRights Reserved.
# =====================================================
FROM openjdk:17-jdk-alpine
LABEL maintainer="Focason Lab Team"

EXPOSE 8761
ADD build/libs/eureka-service-1.0.0-SNAPSHOT.jar eureka-service.jar
ENTRYPOINT ["java","-jar","/eureka-service.jar"]
