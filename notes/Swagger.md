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

## 配置Swagger信息

Swagger的bean实例：Docket

```java
//配置了Swagger的Docket的bean实例
@Bean
public Docket docket(){
    return new Docket(DocumentationType.SWAGGER_2);
}
```

### 配置基本信息

```java
//配置Swagger信息 == apiInfo
private ApiInfo apiInfo(){
    Contact contact = new Contact("venns","http://venns.cn/","2396177829@qq.com");
    return new ApiInfo("Venns\'Swagger",
            "Hello Swagger",
            "v1.0",
            "http://venns.cn/",
            contact,
            "Apache 2.0",
            "http://www.apache.org/licenses/LICENSE-2.0",
            new ArrayList());
}
```

再在Docket里面用自己配置的apiInfo

```java
@Bean
public Docket docket(){
    return new Docket(DocumentationType.SWAGGER_2)
            .apiInfo(apiInfo())
        	.enable(true); //默认开启swagger，为false则不开启swagger
}
```

## 配置Swagger扫描接口

Docket中的select方法

```java
//配置了Swagger的Docket的bean实例
@Bean
public Docket docket(){
    return new Docket(DocumentationType.SWAGGER_2)
            .apiInfo(apiInfo())
            .select()
            //RequestHandlerSelectors 配置要扫描接口的方式
            /*
                basePackage 扫描指定包
                any 扫描全部
                none 都不扫描
                withClassAnnotation 扫描类上的注解
                withMethodAnnotation 扫描方法上的注解
             */
            .apis(RequestHandlerSelectors.basePackage("com.venns.controller"))
            //paths 指定路径
            .paths(PathSelectors.ant("/venns/**"))
            .build();
}
```

## 配置Swagger的API文档分组

```java
.groupName("venns")
```

- 配置多个分组：配置多个Docket实例即可

## 扫描实体类

只要我们的接口的返回值存在实体类，就会被扫描到Swagger中，再添加对应注解即可

```java
@ApiModel("用户实体类")
public class User {

    @ApiModelProperty("用户名")
    public String username;
    @ApiModelProperty("密码")
    public String password;
}
```

### 其他注解

- `@ApiOperation("")`：放在controller类的方法上
- `@ApiParam("")`：放在方法的参数前