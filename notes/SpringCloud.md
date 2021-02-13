# SpringCloud

## SpringCloud简介

- 基于springboot提供了一套微服务的解决方案，包括服务注册与发现，配置中心，全链路监控，服务网关，负载均衡等等
- 简化了分布式系统基础设施的开发，为开发人员快速构建了分布式系统的一些工具，包括配置管理，服务发现，断路器，路由，微代理，事件总线，全局锁，决策竞选，分布式会话等等
- 是分布式微服务架构下的一站式解决方案，是各个微服务架构落地技术的结合体，俗称微服务全家桶

## SpringCloud和SpringBoot的关系

- SpingBoot专注于快速方便的开发单个个体微服务
- SpringCloud是关注全局的微服务协调治理框架，它将SpringBoot开发的一个个单体微服务整合并管理起来，为各个微服务之间提供 配置管理，服务发现，断路器，路由，微代理，路由等等集成服务
- SpringBoot可以离开SpringCloud独立使用，开发项目，但是SpringCloud离不开SpringBoot，属于依赖关系
- **SpringBoot专注于快速，方便的开发单个个体微服务，SpringCloud关注全局的服务治理框架**

## SpringCloud能干嘛

- Distributed/versioned configuration （分布式/版本控制）
- Service registration and discovery （服务注册与发现）
- Routing （路由）
- Service-to-service calls （服务到服务之间的调用）
- Load balancing （负载均衡）
- Circuit Breakers （断路器）
- Global locks （全局锁）
- Distributed messaging （分布式消息管理）
- .....

# Eureka服务注册与发现

Eureka是Netfix的一个子模块，也是核心模块之一，Eureka是一个基于REST的服务，用于定位服务，以实现云端中间层服务发现和故障转移，功能类似于Dubbo的注册中心

## 原理简述

- 采用C-S架构设计，EurekaServer作为服务注册功能的服务器

- 系统中的其他微服务，使用Eureka的客户端连接到EurekaServer并维持心跳连接，这样系统的维护人员就可以通过EurekaServer来监控系统中各个微服务是否正常运行，SpringCloud的一些其他模块（比如Zuul）就可以通过EurekaServer来发现系统中的其他微服务，并执行相关的逻辑
- Eureka包含两个组件：**Eureka Server** 和 **Eureka Client**
- Eureka Server：提高服务注册服务，各个节点启动后，会在Eureka中进行注册，这样Eureka Server中的服务注册表中会将会话中所有可用节点的信息，服务节点的信息在界面中直观的看到
- Eureka Client：是一个Java客户端，用于简化EurekaServer的交互，客户端同时也具备一个内置的，使用轮询负载算法的负载均衡器，在应用启动后，会向EurekaServer发送心跳（默认周期为30s），如果Eureka Server在多个心跳周期内没有接收到某个节点的心跳，EurekaServer会从服务注册表中把这个服务节点删除掉（默认周期为90s）
- 三大角色
	- Eureka Server:提高服务的注册与发现
	- Service Provider：将自身服务注册到Eureka中，从而使消费方能够找到
	- Service Consumer：服务消费方从Eureka中获取注册服务列表，从而找到消费服务
- Eureka也具有自我保护机制，简单来说就是某个时刻某个服务不可以用了，Eureka不会立刻清理，而是会保存该服务信息

## 简单应用

### 服务端

1. 新建一个model，导入eureka依赖

	```xml
	<dependency>
	    <groupId>org.springframework.cloud</groupId>
	    <artifactId>spring-cloud-starter-eureka-server</artifactId>
	    <version>1.4.6.RELEASE</version>
	</dependency>
	```

2. 编写配置文件：application.yml

	```yaml
	server:
	  port: 7001
	
	# Eureka配置
	eureka:
	  instance:
	    hostname: localhost # Eureka服务端的实例名称
	  client:
	    register-with-eureka: false # 表示是否向Eureka注册中心注册自己
	    fetch-registry: false # 如果为false，则表示自己为注册中心
	    service-url: # 监控页面
	      defaultZone: http://${eureka.instance.hostname}:${server.port}/erueka/
	```

3. 新建主启动类，加上`@EnableEurekaServer`注解

	```java
	@SpringBootApplication
	@EnableEurekaServer
	public class EurekaServer_7001 {
	    public static void main(String[] args) {
	        SpringApplication.run(EurekaServer_7001.class,args);
	    }
	}
	```

- 启动之后直接访问配置的端口即可

### 服务注册

1.  在服务提供者中添加依赖

	```xml
	<dependency>
	    <groupId>org.springframework.cloud</groupId>
	    <artifactId>spring-cloud-starter-eureka</artifactId>
	    <version>1.4.7.RELEASE</version>
	</dependency>
	<!--监控信息-->
	<dependency>
	    <groupId>org.springframework.boot</groupId>
	    <artifactId>spring-boot-starter-actuator</artifactId>
	</dependency>
	```

2. 编写配置

	```yaml
	# Eureka的配置
	eureka:
	  client:
	    service-url:
	      service-url:
	        defaultZone: http://localhost:7001/erueka/
	```

	即配置Eureka服务端的地址

3. 为服务提供者的主启动类添加注解`@EnbaleEurekaClient`,表示这是一个Eureka的客户端，服务启动后，自动注册到Eureka中

## 集群搭建

搭建3个Eureka服务端，端口分别为7001，7002，7003，进行关联

7001的配置文件:

```yaml
server:
  port: 7001
eureka:
  instance:
    hostname: localhost 
  client:
    register-with-eureka: false 
    fetch-registry: false 
    service-url: 
      defaultZone: http://localhost:7002/erueka/,http://localhost:7003/erueka/
```

相对应的更改7002和7003的配置文件,再将服务提供者的发布地址增加7002，7003:

```
eureka:
  client:
    service-url:
      service-url:
        defaultZone: http://localhost:7001/erueka/,http://localhost:7002/erueka/,http://localhost:7003/erueka/
```

## CAP原则

- C（Consistency）强一致性
- A（Availability）可用性
- P（Partition tolerance）分区容错性

### 核心

- 是在一个分布式系统中，一致性、可用性）、分区容错性,CAP 原则指的是，这三个要素最多只能同时实现两点，不可能三者兼顾

- CA：单点集群，满足一致性，可用性的系统，通常可扩展性较差 (Zookeeper)
- CP：满足一致性，分区容错性的系统，通常性能不是特别高
- AP：满足可用性，分区容错性的系统，通常可能一致性相对要求低一些 (Eureka)