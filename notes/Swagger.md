# Swagger

**前后端分离时代：vue+springboot**

- 后端：后端控制层，服务层，数据访问层
- 前端：前端控制层，视图层

- 前后端交互：API

问题：前后端集成联调，无法做到及时协商

解决方案：

- 前端测试后端接口：postman
- 后端提供接口，需要试试更新最新的消息及改动

## Swagger

- 号称世界上最流行的Api框架
- RestFul Api 文档在线自动生成工具（API文档与API定义同步更新）
- 直接运行，可以在线测试API接口
- 支持多种语言：（Java，PHP....）

在项目使用Swagger需要springbox

- swagger2
- swaggerUI

## Springboot集成Swagger

1. 新建一个springboot-web项目

2. 导入相关依赖

	```xml
	<dependency>
	    <groupId>io.springfox</groupId>
	    <artifactId>springfox-boot-starter</artifactId>
	    <version>3.0.0</version>
	</dependency>
	```

3. 编写一个helloworld工程,主应用类加上`@EnableOpenApi`注解

4. 配置Swagger -> config

	```java
	@Configuration
	public class SwaggerConfig {
	}
	```

5. 测试运行: http://xxxx/swagger-ui/index.html