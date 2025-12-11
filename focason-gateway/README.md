# Focason Cloud Gateway

Focason Cloud API Gateway Service 是整个微服务架构的唯一入口点。它基于 Spring Cloud Gateway（WebFlux
响应式堆栈）构建，负责处理所有的外部流量，执行路由转发、身份认证、安全控制、流量监控和异常处理等核心任务。

## 1. 核心特性

本 Gateway 服务集成了以下关键功能：

| 模块/功能   | 描述                                                                      | 对应的实现类/配置                                               |
|---------|:------------------------------------------------------------------------|:--------------------------------------------------------|
| 微服务注册   | 注册到 Eureka 注册中心，允许配置中心通过服务 ID 发现本服务。                                    | `GatewayApplication.java, application.yaml`             |
| 动态路由    | 配置灵活的路由规则，将外部请求转发到下游微服务（如 `eureka-service`, `base-service`）并应用过滤器。      | `GatewayConfig.java`                                    |
| JWT 认证  | 全局身份认证过滤器，负责解析、校验 JWT 令牌的有效性和过期时间。                                      | `AuthenticationTokenFilter.java`                        |
| 用户状态校验  | **有状态的用户校验**，通过 Feign 调用 Auth Service，确保令牌对应的用户在系统中是活跃且合法的（防止被禁用后仍能访问）。 | `AuthenticationTokenFilter.java` (调用 `AuthFeignClient`) |
| 全局安全配置  | 基于 WebFlux 的安全配置，禁用 CSRF、Basic 认证和表单登录。                                 | `WebSecurityConfig.java`                                |
| 全局日志监控  | 记录每个请求的 IP、方法、路径、响应状态码和处理耗时，用于性能监控。                                     | `GlobalLogFilter.java`                                  |
| 统一异常处理  | 捕获 Gateway 层面以及 Feign 调用下游服务时抛出的异常，统一封装为标准格式返回给客户端。                     | `GlobalLogFilter.java`                                  |
| 全局 CORS | 配置跨域资源共享策略，允许前端应用访问。                                                    | `GatewayConfig.java` / `application.yaml`               |
| 配置中心集成  | 通过 Eureka 发现配置中心 (`configure-server`)，拉取配置信息。                           | `application.yaml`                                      |

## 2. 环境配置 (`application.yaml`)

Gateway 服务依赖于一系列环境变量进行配置。

| 环境变量                | 默认值                | 描述                                 |
|---------------------|:-------------------|:-----------------------------------|
| `SERVER_PORT`       | `8080`             | 网关服务的 HTTP 监听端口。                   |
| `APPLICATION_NAME`  | `gateway-service`  | Spring 应用名和 Eureka 服务 ID。          |
| `EUREKA_HOSTNAME`   | `localhost`        | Eureka Server 的主机名。                |
| `RABBITMQ_HOST`     | `localhost`        | RabbitMQ 消息队列主机名。                  |
| `RABBITMQ_USERNAME` | `guest`            | RabbitMQ 用户名。                      |
| `RABBITMQ_PASSWORD` | `guest`            | RabbitMQ 密码。                       |
| `CONFIG_SERVICE_ID` | `configure-server` | Spring Cloud Config Server 的服务 ID。 |
| `LOG_LEVEL`         | `INFO`             | 根日志级别。                             |

## 3. 路由规则概览 (`GatewayConfig.java`)

以下是主要的路由和过滤器配置：

| Route ID         | Path Pattern                                                                                      | 目标服务 (Eureka ID)      | 应用的过滤器                      |
|------------------|:--------------------------------------------------------------------------------------------------|:----------------------|-----------------------------|
| `eureka-service` | `/api/v1/eureka/**`                                                                               | `lb://eureka-service` | `AuthenticationTokenFilter` |
| `base-service`   | `/api/v1/notifications/**` <br/>`/api/v1/auth/**` <br/>`/api/v1/users/**` <br/>`/api/v1/files/**` | `lb://base-service`   | `AuthenticationTokenFilter` |

## 4. 身份认证与白名单

### 4.1 认证流程 (`AuthenticationTokenFilter.java`)

1. **白名单检查**: 检查请求路径是否在豁免列表中。

2. **令牌检查**: 检查 `Authorization` Header 是否存在。

3. **JWT 解析**: 解析令牌结构和载荷（payload）。

4. **过期检查**: 校验 JWT 是否过期。

5. **用户状态校验**: 通过 Feign 调用 Auth Service 校验用户是否合法（即时封禁）。

6. **Header 注入**: 将 `uid` 和 `email` 注入到请求头 (`X-User-ID`, `X-User-Email`)，传递给下游服务。

### 4.2 白名单路径 (`AUTH_WHITE_PATTERNS`)

以下路径无需携带有效的 Access Token 即可访问：

* `/api/v1/auth/register`

* `/api/v1/auth/login`

* `/api/v1/auth/refresh-token`

* `/api/v1/auth/verify-code` 等认证相关核心路径。

* `/api/v1/auth/oauth/**` (所有 OAuth 回调)。

* `/api/v1/eureka.*` (所有 Eureka 客户端相关的路径)。

## 5. 快速启动

1. **启动依赖服务**: 确保您的 configure-server (Config Server) 和 eureka-server (Discovery Server) 正在运行。

2. **配置环境变量**: 根据 application.yml 中的配置，设置相应的环境变量，特别是 RabbitMQ 和 Eureka 的连接信息。

3. **运行应用**:
    ```shell
    # 编译 & 打包
    ./gradlew clean build
    
    # 运行
    java -jar build/libs/gateway-service.jar
    ```

4. 访问: 网关服务将运行在 http://localhost:8080 (取决于 SERVER_PORT)。
