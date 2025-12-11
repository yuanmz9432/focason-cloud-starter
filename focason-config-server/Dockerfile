# =====================================================
# Copyright 2025 Focason Co.,Ltd. AllRights Reserved.
# =====================================================
FROM amazoncorretto:11-alpine
LABEL maintainer="Focason Lab Team"

EXPOSE 8888
ADD build/libs/configure-server-1.0.0-SNAPSHOT.jar configure-server.jar
ENTRYPOINT ["java","-jar","/configure-server.jar"]
