# =====================================================
# Copyright 2025 Focason Co.,Ltd. AllRights Reserved.
# =====================================================
FROM amazoncorretto:17
LABEL maintainer="Focason Lab Team"

EXPOSE 8761
ADD build/libs/focason-service-registry-1.0.0-SNAPSHOT.jar focason-service-registry.jar
ENTRYPOINT ["java","-jar","/focason-service-registry.jar"]
