#!/bin/bash
# =====================================================
# Copyright 2025 Focason Co.,Ltd. AllRights Reserved.
# =====================================================

# 镜像仓库前缀
ECR_REPO_PREFIX="focason-cloud"

echo "Start Building Images..."

# 定义模块和它们的目录
declare -A modules=(
    ["focason-service-registry"]="focason-service-registry"
    ["focason-config-server"]="focason-config-server"
    ["focason-gateway"]="focason-gateway"
    ["focason-platform-service"]="focason-platform-service"
)

# 获取当前时间戳，用于创建旧镜像的标签
timestamp=$(date +%Y%m%d%H%M)

# 构建每个模块的 Docker 镜像
for module in "${!modules[@]}"
do
    echo " 🛠️ Building Docker image for $module"

    # 进入模块目录
    cd "${modules[$module]}" || { echo "Failed to enter directory ${modules[$module]}"; exit 1; }

    # 检查是否存在当前的latest镜像，并给它加上时间戳标签
    if docker image inspect "$ECR_REPO_PREFIX/$module:latest" >/dev/null 2>&1
    then
        echo "Tagging existing image $ECR_REPO_PREFIX/$module:latest to $module:$timestamp"
        docker tag "$ECR_REPO_PREFIX/$module:latest" "$module:$timestamp"
    fi

    # 构建新的 Docker 镜像
    if ! docker build -t "$ECR_REPO_PREFIX/$module:latest" .
    then
        echo " ❌ Failed to build image for $module"
        exit 1
    fi

    # 返回脚本所在目录
    cd - || exit
done

echo " ✅ All Docker images have been built successfully."
