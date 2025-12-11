## 核心服务
核心服务中包含整体项目运用到的共通类以及共通组件。 

※此项目**不需要启动**

### 快速开始
* 添加依赖
  ```groovy
  // focason-core
  implementation project(':focason-core')
  ```

### 核心服务概要

#### 异常类
* 构造
  * com.focason.core
    * exception
      * FsException.java 基础异常类
      * FsErrorResource.java 异常构造体
      * FsErrorCode.java 异常码
      * FsEntityNotFoundException.java 业务异常等
      * ...
* 所有业务异常均需要继承 ***FsException.java*** 基础异常类
#### 枚举类
* 提供微服务中使用的基础枚举类型
#### 工具类
* ***FsBCryptEncoder.java*** 密码解码器
#### Entity
* 主要负责与数据库交互层
#### Resource
* 主要负责Controller与Service层
#### Feign组件
* 微服务间调用
* 在需要引用的项目启动类上配置标签
  ```
  ***@EnableFeignClients(basePackages = "com.focason.core.feign")***
  ```
#### 标签类
* ***FsConditionParam.java*** 检索条件匹配标签
* ***FsPaginationParam.java*** 检索结果分页标签
* ***FsConditionParam.java*** 检索排序标签 
