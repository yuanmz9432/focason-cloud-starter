#!/bin/bash
# =====================================================
# Copyright 2025 Focason Co.,Ltd. AllRights Reserved.
# =====================================================

# 配置参数
CLUSTER_NAME="focason-cloud-cluster" # 替换为你的 ECS 集群名
AWS_REGION="ap-northeast-1"          # 替换为你的 AWS 区域
ACTION=$1                            # 启动或停止，传递 "start" 或 "stop"
SERVICES=("focason-service-registry" "focason-config-server" "focason-gateway")     # 替换为你的服务列表

export AWS_PAGER=""

# 检查输入参数
if [[ "$ACTION" != "start" && "$ACTION" != "stop" ]]; then
  echo "Usage: $0 <start|stop>"
  exit 1
fi

# 处理每个服务
for SERVICE in "${SERVICES[@]}"; do
  echo "Processing service: $SERVICE"

  if [[ "$ACTION" == "start" ]]; then
    # 启动服务，将期望的任务数设置为1
    echo "Starting service: $SERVICE"
    aws ecs update-service \
      --cluster "$CLUSTER_NAME" \
      --service "$SERVICE" \
      --desired-count 1 \
      --region "$AWS_REGION"

  elif [[ "$ACTION" == "stop" ]]; then
    # 停止服务，将期望的任务数设置为0
    echo "Stopping service: $SERVICE"
    aws ecs update-service \
      --cluster "$CLUSTER_NAME" \
      --service "$SERVICE" \
      --desired-count 0 \
      --region "$AWS_REGION"
  fi
done

echo "All services have been ${ACTION}ed successfully."