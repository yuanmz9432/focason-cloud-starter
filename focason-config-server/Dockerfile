# =====================================================
# Copyright 2025 Focason Co.,Ltd. AllRights Reserved.
# =====================================================
FROM amazoncorretto:11-alpine
LABEL maintainer="Focason Lab Team"

EXPOSE 8888
ADD build/libs/focason-config-server-1.0.0-SNAPSHOT.jar focason-config-server.jar
ENTRYPOINT ["java","-jar","/focason-config-server.jar"]
