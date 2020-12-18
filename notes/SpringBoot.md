# SpringBoot

Javaweb的开发框架，和SpringMVC类似，能够简化开发，迅速的开发web应用，约定大于配置

**主要优点：**

- 为所有Spring开发者更快的入门
- 开箱即用，简化项目配置
- 内嵌式简化Web项目
- 没有冗余代码生成和XML配置要求

**核心：自动装配**

# 第一个SpringBoot程序

1. 基于https://start.spring.io/进行快速开发，也可以从idea内置初始化，项目名为helloworld
2. 在helloworldApplication【程序主入口】同级目录增加controller包，dao包，service包...
3. 编写对应代码即可

**彩蛋：**

- 可以在resources目录下新建banner.txt改变springboot启动时的画面

# 原理初探

## 自动装配

**pom.xml**

- spring-boot-dependcies：核心依赖在父工程中
- 引入spring-boot依赖的时候，不需要指定版本，就是因为有这些版本仓库

**启动器**

- ```xml
	<dependency>
	    <groupId>org.springframework.boot</groupId>
	    <artifactId>spring-boot-starter</artifactId>
	</dependency>
	```

- 就是SpringBoot的启动场景，比如spring-boot-starter-web，就会自动导入web依赖
- SpringBoot会将所有的功能场景，变成一个个的启动器

 **主程序**

```java
@SpringBootApplication
public class Springboot01HelloworldApplication {

    public static void main(String[] args) {
        SpringApplication.run(Springboot01HelloworldApplication.class, args);
    }

}
```

- `@SpringBootApplication` 标注这个类是一个springboot的应用
- `SpringApplication.run(Springboot01HelloworldApplication.class, args); `  讲springboot应用启动

- 注解

	```Java
	@SpringBootConfiguration //springboot的配置
		@Configuration //表明这是一个spring配置类
			@Component //表明这是一个spring组件
	@EnableAutoConfiguration //自动配置
		@AutoConfigurationPackage //自动注册包
			@Import({Registrar.class}) //导入选择器
		@Import(AutoConfigurationImportSelector.class) //自动导入选择器
			getAutoConfigurationFntry() //获得自动配置的实体
	        getCandidateConfigurations() //获取候选的配置
	            getSrpingFactoriseLoaderFactorClass() //标注了EnableAutoConfiguration注解的类
	  		loadFactoryNames() //获取所有的加载配置
	       	loadSpringFactories() //获取所有的资源
	            ClassLoader.getResources() //获取项目资源 "META-INF/spring.factoris"
	            ClassLoader.getSystemResources() //获取系统资源
	@ComponentScan //扫描当前主启动类同级的包
	```

结论：springboot的所有自动配置都是在启动的时候扫描并加载：`spring.factories`所有的自动配置类都在这里面，但是不一定生效，要判断条件是否成立，只要导入了对应的start，就有对应的启动器，有了启动器，我们自动装配就会生效。

**步骤：**

1. springboot在启动的时候，从类路径下/META-INF/spring.factories获取指定的值
2. 将这些自动配置的类导入容器，自动配置就会生效，帮我进行自动配置
3. 以前需要自动配置的东西，springboot就帮我们做了
4. 整个JavaEE，解决方案和自动配置的东西都在 spring-boot-autoconfigure-2.2.0.RELEASE.jar 包下
5. 它会把所有需要导入的组件，以类名的方式返回，这些组件就会被添加到容器
6. 容器中也会存在非常多的xxxAutoConfiguration的文件（@bean），就是这些类给容器导入了这个场景需要的所有组件，并且自动装配@Configuration
7. 有了自动配置类，免去了手动编写配置文件的工作

## run

```java
@SpringBootApplication
public class Springboot01HelloworldApplication {

    //该方法返回一个ConfigurableApplicationContext对象
    //参数一：应用入口类     参数类：命令行参数
    public static void main(String[] args) {
        SpringApplication.run(Springboot01HelloworldApplication.class, args);
    }

}
```

本以为是运行了一个main方法，没想到却开启了一个服务

### SpringApplication.run

#### SpringApplication

1. 推断应用的类型是普通的项目还是Web项目
2. 查找并加载所有可用初始化器，设置到initializers属性中
3. 推出所有的应用程序监听器，设置到listeners属性中
4. 推断并设置main方法的定义类，找到运行的主类

# SpringBoot配置

springboot使用一个全局的配置文件，配置文件的名称是固定的，推荐使用yaml格式

- application.properties
	- 语法结构：key=value
- application.yaml
	- 语法结构：key 空格 value

**配置文件的作用：修改springboot自动配置的默认值**

## yaml

基础语法：key：空格value

```yaml
# 普通的key-value
name: venns

# 对象
student:
  name: venns
  age: 3
# 行内写法
students: {name: venns,age: 3}

# 数组
pet:
  - cat
  - dog
  - pig
pets: [cat,dog,pig]
```

## 通过yaml给属性赋值

1. 编写yaml

	```yaml
	person:
	  name: venns
	  age: 3
	  happy: false
	  birth: 2001/01/1
	  maps: {k1: v1,k2: v2}
	  list:
	    - code
	    - music
	    - girl
	  dog:
	    name: 旺财
	    age: 3
	```

2. 给需要注入属性的类添加注解绑定

	```java
	@Component
	@ConfigurationProperties(prefix = "person")
	public class Person {
	
	    private String name;
	    private int age;
	    private boolean happy;
	    private Date birth;
	    private Map<String,Object> maps;
	    private List<Object> lists;
	    private Dog dog;
	```

- 需要导入依赖

	```xml
	<dependency>
	    <groupId>org.springframework.boot</groupId>
	    <artifactId>spring-boot-configuration-processor</artifactId>
	    <optional>true</optional>
	</dependency>
	```

