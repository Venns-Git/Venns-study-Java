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

