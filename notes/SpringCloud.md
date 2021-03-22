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
      defaultZone: http://localhost:7001/erueka/,http://localhost:7002/erueka/,http://localhost:7003/erueka/
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

# Ribbon及负载均衡

## 是什么

- Ribbon：基于Netfix Ribbon实现的一套**客户端负载均衡的工具**

- 简单的说，Ribbon的主要功能式提供客户端的软件负载均衡算法，将NetFix的中间层服务连接再一起，Ribbon的客户端组件提供一系列的原则的配置项如：连接超时，重试等等。简单的说就是在配置文件中列出LoadBalancer（简称LB：负载均衡）后面所有的机器，Ribbon会自动帮你基于某种算法规则（如简单轮询，随机连接等等）去连接这些机器，我们也很容易使用Ribbon实现自定义的负载均衡算法

## 能干嘛

- LB，即负载均衡（Load Balance），在微服务或分布式集群中经常用的一种应用。
- 负载均衡简单的说就是将客户的请求平摊的分配到多个服务上，从而达到系统的HA（高可用）。
- 常见的负载均衡软件有Nginx，Lvs等等。
- dubbo，SpringCloud都给我们提供了负载均衡，**SpringCloud的负载均衡算法可用自定义**

- 负载均衡简单分类:
	- 集中式LB
		- 即在服务的消费方和提供方之间使用独立的LB设备，如Nginx，由改设施负责把访问请求通过某种策略转发至服务的提供方
	- 进程式LB
		- 将LB逻辑集成到消费方，消费方从服务注册中心获知有哪些地址可用，然后自己再从这些地址中选出一个合适的服务器
		- **Ribbon就属于进程内LB**，它只是一个类库，集成于消费方进程，消费方通过它来获取到服务提供方的地址

## 简单应用

1. 服务消费者为80端口，进行Eureka配置

	```yaml
	# Eureka 配置
	eureka:
	  client:
	    register-with-eureka: false # 不向Eureka中注册自己
	    service-url:
	      defaultZone: http://localhost:7001/eureka/,http://localhost:7002/eureka/,http://localhost:7003/eureka/
	```

2. 为原来的RestTemplate配置负载均衡

	```java
	@Configuration //--spring applicationContext.xml
	public class ConfigBean {
	
	
	    //配置负载均衡实现RestTemplate
	    @Bean
	    @LoadBalanced //Ribbon
	    public RestTemplate getRestTemplate(){
	        return new RestTemplate();
	    }
	}
	```

3. 将REST_URL改为服务名称，不再是一个固定的服务提供者的地址

	```java
	//private static final String REST_URL_PREFIX = "http://localhost:8081";
	// 通过Ribbon实现的时候，地址应该是一个变量，通过服务名来进行访问
	private static final String REST_URL_PREFIX = "http://SPRINGCLOUD-PROVIDER-DEPT";
	```

4. 将原来的一个服务提供者8001扩展为3个，分别为8001，8002，8003，修改端口号和数据库，服务名称保持一致，这时候，再通过服务消费者去访问，就能查看实现效果（每次请求都获得不同数据库里的内容）

## 自定义负载均衡算法

核心接口：IRule

```java
package com.netflix.loadbalancer;

public interface IRule {
    Server choose(Object var1);

    void setLoadBalancer(ILoadBalancer var1);

    ILoadBalancer getLoadBalancer();
}
```

此接口下有许多实现类：

`AbstractLoadBalancerRule`: 抽象的负载均衡规则，一般不用

`AvailabilityFilteringRule`: 会先过滤掉崩溃，访问故障的服务，在剩下的服务中进行轮询

`RoundRobinRule`: 轮询，默认的负载均衡算法

`RandomRule`: 随机

`ReTry`:会先按照轮询获取服务，如果轮询失败，则会在指定的时间内重试

### 更改默认算法

只需在ConfigBean中配置即可。

```java
@Configuration //--spring applicationContext.xml
public class ConfigBean {


    //配置负载均衡实现RestTemplate
    @Bean
    @LoadBalanced //Ribbon
    public RestTemplate getRestTemplate(){
        return new RestTemplate();
    }

    @Bean
    public IRule myRule(){
        //将默认轮询算法改为随机算法
        return new RandomRule();
    }
}
```

### 自定义算法规则

规则：每个服务访问5次，然后访问下一个服务

1. 模仿随机算法写自己的规则

	```java
	public class MyRule extends AbstractLoadBalancerRule {
	    private int total = 0; //共访问服务次数
	    private int currentIndex = 0; //当前服务的下标
	    public MyRule() {
	    }
	
	    public Server choose(ILoadBalancer lb, Object key) {
	        if (lb == null) {
	            return null;
	        } else {
	            Server server = null;
	
	            while(server == null) {
	                if (Thread.interrupted()) {
	                    return null;
	                }
	
	                List<Server> upList = lb.getReachableServers(); //获得所有可用的服务
	                List<Server> allList = lb.getAllServers(); //获得所有的服务
	                int serverCount = allList.size();
	                if (serverCount == 0) {
	                    return null;
	                }
	
	                if (total < 5){
	                    server =  upList.get(currentIndex);
	                    total++;
	                }else {
	                    total = 0;
	                    currentIndex++;
	                    if (currentIndex >= upList.size()){
	                        currentIndex = 0;
	                    }
	                    server = upList.get(currentIndex);
	                }
	
	                if (server == null) {
	                    Thread.yield();
	                } else {
	                    if (server.isAlive()) {
	                        return server;
	                    }
	
	                    server = null;
	                    Thread.yield();
	                }
	            }
	
	            return server;
	        }
	    }
	
	    protected int chooseRandomInt(int serverCount) {
	        return ThreadLocalRandom.current().nextInt(serverCount);
	    }
	
	    public Server choose(Object key) {
	        return this.choose(this.getLoadBalancer(), key);
	    }
	
	    public void initWithNiwsConfig(IClientConfig clientConfig) {
	    }
	}
	```

2. 配置自己的config，注入自己写的规则

	```java
	@Configurable
	public class MyRuleConfig {
	    @Bean
	    public IRule myRule(){
	        return new MyRule();
	    }
	}
	```

3. 在主启动类上加上`@RibbonClient`注解

	```java
	@SpringBootApplication
	@EnableEurekaClient
	@RibbonClient(name = "SPRINGCLOUD-PROVIDER-DEPT",configuration = MyRuleConfig.class)
	public class DeptConsumer_80 {
	    public static void main(String[] args) {
	        SpringApplication.run(DeptConsumer_80.class,args);
	    }
	}
	```

	- name为服务名
	- configuration为自定义Ribbon的配置类
	- 注意：自定义的Ribbon配置类和规则类不能放在主启动类的统计或者下级目录

# Feign负载均衡

## 简介

Feign是声明式Web Service客户端，它让微服务之间的调用变得更简单，类似controller调用service。SpringCloud集成了Ribbon和Eureka，可以使用Feigin提供负载均衡的http客户端

只需要创建一个接口，添加注解即可。

Feign，主要是社区版，大家都习惯面向接口编程。这个是很多开发人员的规范。调用微服务访问两种方法：

1. 微服务名字 【ribbon】
2. 接口和注解 【feign】

**Feign能干什么**

- Feign旨在使编写Java Http客户端变得更容易

- 前面在使用Ribbon + RestTemplate时，利用RestTemplate对Http请求的封装处理，形成了一套模板化的调用方法。但是在实际开发中，由于对服务依赖的调用可能不止一处，往往一个接口会被多处调用，所以通常都会针对每个微服务自行封装一个客户端类来包装这些依赖服务的调用。所以，Feign在此基础上做了进一步的封装，由他来帮助我们定义和实现依赖服务接口的定义，**在Feign的实现下，我们只需要创建一个接口并使用注解的方式来配置它 (类似以前Dao接口上标注Mapper注解，现在是一个微服务接口上面标注一个Feign注解)**，即可完成对服务提供方的接口绑定，简化了使用Spring Cloud Ribbon 时，自动封装服务调用客户端的开发量。

**Feign集成了Ribbon**

- 利用Ribbon维护了MicroServiceCloud-Dept的服务列表信息，并且通过轮询实现了客户端的负载均衡，而与Ribbon不同的是，通过Feign只需要定义服务绑定接口且以声明式的方法，优雅而简单的实现了服务调用。

## 使用步骤

1. 依据springcloud-consumer-ribbon-80模块复制，创建springcloud-consumer-feign-80模块，修改依赖

	```xml
	<!--实体类+web-->
	<dependencies>
	    <dependency>
	        <groupId>com.venns</groupId>
	        <artifactId>springcloud-api</artifactId>
	        <version>1.0-SNAPSHOT</version>
	    </dependency>
	    <dependency>
	        <groupId>org.springframework.boot</groupId>
	        <artifactId>spring-boot-starter-web</artifactId>
	    </dependency>
	    <dependency>
	        <groupId>org.springframework.boot</groupId>
	        <artifactId>spring-boot-devtools</artifactId>
	    </dependency>
	    <!-- feign -->
	    <dependency>
	        <groupId>org.springframework.cloud</groupId>
	        <artifactId>spring-cloud-starter-feign</artifactId>
	        <version>1.4.6.RELEASE</version>
	    </dependency>
	    <!-- eureka -->
	    <dependency>
	        <groupId>org.springframework.cloud</groupId>
	        <artifactId>spring-cloud-starter-eureka</artifactId>
	        <version>1.4.6.RELEASE</version>
	    </dependency>
	</dependencies>
	```

2. 在实体项目下（springcloud-api）中新建 DeptClientService类

	```java
	@Component
	@FeignClient(value = "SPRINGCLOUD-PROVIDER-DEPT")
	public interface DeptClientService {
	
	    @RequestMapping("/dept/get/{id}")
	    public Dept queryById(@PathVariable("id") long id);
	
	    @RequestMapping("/dept/list")
	    public List<Dept> queryAll();
	
	    @RequestMapping("/dept/list")
	    public boolean addDept(Dept dept);
	}
	```

3. 将服务消费者springcloud-consumer-feign-80模块中的服务改为 DeptClientService

	```java
	@RestController
	public class DeptConsumerController {
	    /*
	     * 消费者调用服务者的server层
	     */
	
	    @Autowired
	    private DeptClientService deptClientService;
	
	    @RequestMapping("/consumer/dept/get/{id}")
	    public Dept get(@PathVariable("id") long id){
	        return this.deptClientService.queryById(id);
	    }
	
	    @RequestMapping("/consumer/dept/add")
	    public boolean add(Dept dept){
	        return this.deptClientService.addDept(dept);
	    }
	
	    @RequestMapping("/consumer/dept/list")
	    public List<Dept> list(){
	        return this.deptClientService.queryAll();
	    }
	}
	```

4. 再将服务消费者的主启动类加上`@EnableFeignClients`注解

	```java
	@SpringBootApplication
	@EnableEurekaClient
	@EnableFeignClients(basePackages = {"com.venns.springcloud"})
	public class FeignDeptConsumer_80 {
	    public static void main(String[] args) {
	        SpringApplication.run(FeignDeptConsumer_80.class,args);
	    }
	}
	```

	- basePackages为扫描包，Feign会扫描指定包下的带有`@FeignClient`注解的类

5. 启动服务提供者8001，8002，8003，Eureka服务注册中心7001，再启动采用Feigin的服务消费者，默认也采用轮询的规则

# Hystrix服务熔断/降级

**分布式系统面临的问题**
复杂分布式体系结构中的应用程序有数十个依赖关系，每个依赖关系在某些时候将不可避免地失败。

**服务雪崩**

- 多个微服务之间调用的时候，假设微服务A调用微服务B和微服务C，微服务B和微服务C又调用其它的微服务，这就是所谓的“扇出”。如果扇出的链路上某个微服务的调用响应时间过长或者不可用，对微服务A的调用就会占用越来越多的系统资源，进而引起系统崩溃，所谓的“雪崩效应”.

- 对于高流量的应用来说，单一的后端依赖可能会导致所有服务器上的所有资源都在几秒钟内饱和。比失败更糟糕的是，这些应用程序还可能导致服务之间的延迟增加，备份队列，线程和其他系统资源紧张，导致整个系统发生更多的级联故障。这些都表示需要对故障和延迟进行隔离和管理，以便单个依赖关系的失败，不能取消整个应用程序或系统。

**什么是Hystrix**

- Hystrix是一个用于处理分布式系统的延迟和容错的开源库，在分布式系统里，许多依赖不可避免的会调用失败，比如超时、异常等，Hystrix能够保证在一个依赖出问题的情况下，不会导致整体服务失败，避免级联故障，以提高分布式系统的弹性。

- “断路器”本身是一种开关装置，当某个服务单元发生故障之后，通过断路器的故障监控（类似熔断保险丝），向调用方返回一个符合预期的、可处理的备选响应（FallBack），而不是长时间的等待或者抛出调用方无法处理的异常，这样就保证了服务调用方的线程不会被长时间、不必要地占用，从而避免了故障在分布式系统中的蔓延，乃至雪崩。