#!/bin/bash
# =====================================================
# Copyright 2025 Focason Co.,Ltd. AllRights Reserved.
# =====================================================

# ECR 登录配置
AWS_REGION="ap-northeast-1"      # 替换为你的 AWS 区域
ACCOUNT_ID="442426889980"        # 替换为你的 AWS 账户 ID
ECR_REPO_PREFIX="focason-cloud"  # 镜像仓库前缀

# 登录到 ECR
aws ecr get-login-password --region "$AWS_REGION" | docker login --username AWS --password-stdin "$ACCOUNT_ID.dkr.ecr.$AWS_REGION.amazonaws.com"
if [ $? -ne 0 ]; then
    echo "Failed to log in to ECR."
    exit 1
fi

echo "Start Pushing Images to ECR..."

# 定义模块和它们的目录
declare -A modules=(
    ["focason-service-registry"]="focason-service-registry"
    ["focason-config-server"]="focason-config-server"
    ["focason-gateway"]="focason-gateway"
    ["focason-platform-service"]="focason-platform-service"
)

# 获取当前时间戳，用于推送镜像标签
timestamp=$(date +%Y%m%d%H%M)

# 推送每个模块的镜像到 ECR
for module in "${!modules[@]}"
do
    echo " 🛠️ Pushing Docker image for $ECR_REPO_PREFIX/$module"

    # 定义 ECR 镜像名称
    ECR_IMAGE="$ACCOUNT_ID.dkr.ecr.$AWS_REGION.amazonaws.com/$ECR_REPO_PREFIX/$module"

    # 为镜像打上 ECR 标签并推送
    docker tag "$ECR_REPO_PREFIX/$module:latest" "$ECR_IMAGE:latest"
    docker tag "$ECR_REPO_PREFIX/$module:latest" "$ECR_IMAGE:$timestamp"

    echo " ⭐ Pushing image $ECR_IMAGE to ECR"
    docker push "$ECR_IMAGE:latest"
    docker push "$ECR_IMAGE:$timestamp"

    if [ $? -ne 0 ]; then
        echo " ❌ Failed to push image $ECR_IMAGE to ECR."
        exit 1
    fi

done

echo " ✅ All Docker images have been pushed successfully."
