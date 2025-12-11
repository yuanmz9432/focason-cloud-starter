## Spring Cloud Config 消息总线
将配置信息中央化保存, 并通过配置Spring Cloud Bus实现动态修改配置文件。

### 环境参数
| 环境参数                      | 默认                | 参考                                                                          |
|---------------------------|:------------------|:----------------------------------------------------------------------------|
| APPLICATION_NAME          | focason-configure | 项目名称                                                                        |
| CONFIGURE_SERVER_PORT     | 8888              | 服务器端口                                                                       |
| CONFIGURE_SERVER_USERNAME | root              | Basic验证用户名                                                                  |
| CONFIGURE_SERVER_PASSWORD | password          | Basic验证密码                                                                   |
| RABBITMQ_HOST             | localhost         | RabbitMQ服务器地址                                                               |
| RABBITMQ_PORT             | 5672              | RabbitMQ服务器端口                                                               |
| RABBITMQ_USERNAME         | guest             | RabbitMQ服务器用户名                                                              |
| RABBITMQ_PASSWORD         | guest             | RabbitMQ服务器密码                                                               |
| GITHUB_PROJECT_HOST       | -                 | Github项目地址                                                                  |
| GITHUB_USERNAME           | -                 | Github用户名                                                                   |
| GITHUB_TOKEN              | -                 | Github验证令牌(Github → Settings → Developer Settings → Personal access tokens) |
| GITHUB_BRANCH             | -                 | Github配置文件所在分支                                                              |
| GITHUB_ROOT_PATH          | -                 | Github配置文件根路径                                                               |

### 快速开始
* 向各微服务中的resources文件夹中，添加***bootstrap.yml***文件
    ```yaml
    spring:
      main:
        web-application-type: reactive
      cloud:
        config:
          name: {微服务项目名称}
          label: {配置文件所在分支}
          profile: {配置文件后缀名称}
          discovery:
            enabled: true
            service-id: focason-configure
    
    eureka:
        instance:
            prefer-ip-address: true
            instance-id: ${spring.cloud.client.ip-address}:${server.port}
        client:
            service-url:
            defaultZone: http://root:password@localhost:8761/eureka/
    ```
