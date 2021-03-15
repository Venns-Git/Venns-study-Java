# Rdies简单入门

## 概述

> Redis是什么？

Redis(Remote Dictionary Server),即远程字典服务

是一个开源的，C语言编写，支持网络，可基于内存亦可持久化的日志型，Key-Value数据库，会周期性的把更新的数据写入磁盘或者把修改操作写入追加的记录文件，并提供多种语言的API，是当下最热门的NoSQL技术之一，也被人们称之为结构化数据库

> Redis能干嘛?

1. 内存存储，持久化（rdb，aof）
2. 效率高，可用于高速缓存
3. 发布订阅系统
4. 地图信息分析
5. 计时器，计数器
6. ......

> 特性

1. 多样的数据类型
2. 持久化
3. 集群
4. 事务
5. ......

## 基础命令

redis有16个数据库，默认使用第0个

> select [index]

切换数据库

> set [key] [value]

设置k-v键值对

> get [key]

获取key对应的value

>keys *

查看所有的键

> flushdb

清除当前数据库内容

> flushall

清除所有数据库内容

> EXISTS [key]

查看某个键是否存在

> move [key] [index] 

移动key到index数据库，如果index为本身，则在当前库移除key

> EXPIRE [key] [seconds]

设置key在second秒后过期

> ttl [key]

查看key剩余时间

> type [key]

查看key的数据类型

## 测试性能

**redis-benchmark**  压力测试工具

| 序号 | 选项      | 描述                                       | 默认值    |
| :--- | :-------- | :----------------------------------------- | :-------- |
| 1    | **-h**    | 指定服务器主机名                           | 127.0.0.1 |
| 2    | **-p**    | 指定服务器端口                             | 6379      |
| 3    | **-s**    | 指定服务器 socket                          |           |
| 4    | **-c**    | 指定并发连接数                             | 50        |
| 5    | **-n**    | 指定请求数                                 | 10000     |
| 6    | **-d**    | 以字节的形式指定 SET/GET 值的数据大小      | 2         |
| 7    | **-k**    | 1=keep alive 0=reconnect                   | 1         |
| 8    | **-r**    | SET/GET/INCR 使用随机 key, SADD 使用随机值 |           |
| 9    | **-P**    | 通过管道传输 <numreq> 请求                 | 1         |
| 10   | **-q**    | 强制退出 redis。仅显示 query/sec 值        |           |
| 11   | **--csv** | 以 CSV 格式输出                            |           |
| 12   | **-l**    | 生成循环，永久执行测试                     |           |
| 13   | **-t**    | 仅运行以逗号分隔的测试命令列表。           |           |
| 14   | **-I**    | Idle 模式。仅打开 N 个 idle 连接并等待。   |           |

简单测试100个并发连接，100000个请求

```bash
redis-benchmark -h loaclhost -p 6379 -c 100 -n 100000
```

## 五大数据类型

### String（字符串）

除了基本命令外，还支持以下命令：

#### 基本操作

> APPEND [key] [String]

将string追加到key对应的value，返回新字符串的长度

> STRLEN [key]

获取key对应的value字符串长度

> incr [key] 

将key对应的value自增1

> decr [key] 

将key对应的value自减1

> INCRBY [key]  [steps]

将key对应的value自增steps

> DECRBY [key]  [steps]

将key对应的value自减steps

> GETRANGE [key] [start] [end]

截取字符串，[start,end]为截取区间

> SETRANGE [key] [offset] [value]

字符串替换，offset为偏移量

> setex [key] [seconds] [value]

设置键值对，并设置过期时间

> setnx [key] [value]

如果不存在key，则设置，存在没变化

#### 批量处理

> mset [key] [value] ...

批量设置key-value

> mget [key]....

批量获取key

> msetnx [key] [value]

批量设置，如果不存在则设置，但，**msetnx是一个原子性操作，要么一起成功，要么一起失败**

#### 设置对象

> set user:1 {name:venns,age:18}

设置一个user:1 对象，值为json字符串，也可以用mset

> mest user:1:name venns user:1:age 18

#### 组合命令

>getset [key] [value]

获取key原来的value并设置新的value

### List（列表）

在reids里，可以把list当成双端队列，redis里所有list的操作命令，都以L开头

#### 基本命令

> LPUST/RPUSH  [key] [value]...

将一个或多个值从列表头部/尾部插入

> LRANGE [key] [start] [end]

从头部查看列表中start到end位置的值

> LPOP/RPOP [key] 

将左边/右边的值移出list

> lindex [key] [index]

通过下标获取list的值

> Llen [key]

返回列表的长度

#### 复杂命令

> lrem [key] [count] [value]

移除指定个数的值

> ltrim [key] [start] [end]

截取list指定位置的值，list只剩下截取的元素

> rpoplpush [source] [destination]

移除源列表的最后一个元素到目标列表

> lset [key] [index] [value]

替换列表中指定下标的值

> LINSERT [key]  [before]|[after] [pivot] [value]

在列表中将指定的值(value)插入到原来的值(pivot)的前面或后面

### Set（集合）

**无序不重复集合**

#### 基本命令

> sadd [key] [value]...

往集合中添加元素

> smembers [key]

查看集合中的元素

> sismember [key] [value]

判断集合中是否存在指定值

> scard [key]

查看集合中元素个数

> srem [key] [value]...

移除集合中的值

#### 复杂命令

> srandmember [key] [count]

随机获取集合中指定个数的元素

> spop [key] [count]

随机移除集合中指定个数的元素

> smove [source] [destination] [member]

将源集合中指定值移动到目标集合

> sdiff [key] [key]...

查看指定集合的查集（集合1存在，集合2不存在的元素）

> sinter [key] [key]...

查看指定集合的交集 （集合1和集合2共同存在的元素）

> sunion [key]  [key]..

查看指定集合的并集 （集合1和集合2存在的所有元素）

### Hash（哈希）

Map集合，本质和String 类型没有太大区别，还是一个简单key-value

#### 基本操作

> hset [key] [field] [value]

向指定集合添加K-V键值对

> hget [key] [field]

获取指定集合中指定键的值

> hdel [key] [filed..]

删除hash指定key，对应的valu也会被删除

> hlen [key]

查看hash中K-V键值对的个数

> hexists [key] [field]

判断hash中的指定字段是否存在

#### 批量操作

> hmset [key] [field] [value] [field value...]

批量设置键值对

> hget [key] [field...]

批量获取键对应的值

> hgetall [key]

获取hash中所有的键值对

> hkeys [key]

获取全部的key

> hvals [key]

获取全部的value

#### 其他命令

> hincrby [key] [key] [step]

将hash中的指定key自增指定step

> hsetnx [key] [filed] [value]

如果不存在则设置键值对

### Zset（有序集合）

#### 基本命令

> zadd [key] [NX|XX] [CH] [INCR] score member [score member ...] 

 往集合里添加数据

> zrange [key] [start] [value]

查看指定位置上的值

> zrangebyscore [score] [min] [max] [withscores]

按照符合指定区间的score排序，如果min为-inf（负无穷），max为+inf（正无穷）则排序所有,如果加 上withscores则显示score的值

> zrem [key]  [member...]

移除集合中指定的元素

> zcard [key]

获取集合中的元素个数

> zcount [key] [min] [max]

获取集合指定区间的元素个数

## 三种特殊数据类型

### Geospatial 地理位置

> geoadd [key] [longitude] [latitude] [member]

添加地理位置，格式为：集合名，纬度，经度，名字

> geopos [key] [member]

从集合中获取指定位置的经纬度

> geodist [key] [member1] [member2] [unit]

获取集合中指定两个位置的距离，unit为单位

> georadius [key] [longitude] [latitude] [radius] [unit] [withcoord] [withdist] count [number]

获取指定位置的指定半径内的所有元素

参数说明：

- withcoord：显示符合条件的经纬度
- withdist：显示距离
- count number：显示指定个数，如：count 1

> georadiusbymember [key] [member] [radius] [unit] [withcoord] [withdist] count [number]

获取集合中指定地点的的指定半径内的元素

> geohash [key] [member...]

获取指定地点的的11位字符的geohash字符串

### Hyperloglog

是一种数据结构， 用于做基数统计。

> pfadd [key] [element...]

往key里添加数据

> pfcount [key...]

统计key里数据个数

> pfmerge [destkey] [sourcekey...]

将sourcekey合并生成到destkey

### Bitmaps

位图，通过操作二进制来进行记录，就只有0和1两个状态

> setbit [key] [offset] [value]

设置指定键的指定位的值

> getbit [key] [offset]

获取指定键的指定位的值

> bitcount [key] [start] [end]

获取指定键的有效位个数，也可以指定区间

## 事务

**Redis单条命令是保持原子性的，但是事务不保持原子性**

**Redis事务本质：一组命令的集合，一组事务中的所有命令都会被序列化，在事务执行过程中，会按照顺序执行**

**Redis事务特性：一次性，顺序性，排他性**

**Redis事务没有隔离级别的概念：所有的命令在事务中，并没有直接被执行，只有在发起执行命令的时候才会执行**

**Redis的事务:**

- 开启事务（multi）
- 命令入队（...）
- 执行事务(exec)

> 正常执行事务

```bash
127.0.0.1:6379> multi # 开启事务
OK
# 命令入队 
127.0.0.1:6379> set k1 v1
QUEUED
127.0.0.1:6379> set k2 v2
QUEUED
127.0.0.1:6379> get k2
QUEUED
127.0.0.1:6379> exec # 执行事务
1) OK
2) OK
3) "v2"
```

> 放弃事务

```bash
127.0.0.1:6379> multi # 开启事务
OK
# 命令入队
127.0.0.1:6379> set k1 v2
QUEUED
127.0.0.1:6379> set k2 v2
QUEUED
127.0.0.1:6379> set k4 v4
QUEUED
127.0.0.1:6379> discard # 放弃事务，队列中所有命令都不会被执行
OK
```

> 编译型异常（代码有问题，命令有错），事务中所有的命令都不会被执行

```bash
127.0.0.1:6379> multi # 开启事务
OK
127.0.0.1:6379> set k1 v1
QUEUED
127.0.0.1:6379> set k2 v2
QUEUED
127.0.0.1:6379> set k3 v3
QUEUED
127.0.0.1:6379> getset k3 # 错误命令
(error) ERR wrong number of arguments for 'getset' command
127.0.0.1:6379> set k4 v4
QUEUED
127.0.0.1:6379> exec # 执行事务报错
(error) EXECABORT Transaction discarded because of previous errors.
127.0.0.1:6379> get k1 # 所有的命令都不会被执行
(nil)
```

> 运行时异常（代码运行时产生错误，逻辑错误），如果事务队列中存在语法性，那么执行命令的时候，其他命令时可以正常执行的，错误命令抛出异常

```bash
127.0.0.1:6379> set k1 "v1"
OK
127.0.0.1:6379> multi
OK
127.0.0.1:6379> incr k1 # 字符串自增1 逻辑错误
QUEUED
127.0.0.1:6379> set k2 v2
QUEUED
127.0.0.1:6379> get k2
QUEUED
127.0.0.1:6379> exec # 执行事务，虽然第一条命令失败了，但是其他命令成功了
1) (error) ERR value is not an integer or out of range
2) OK
3) "v2"
```

#### 监控 Watch

**悲观锁：**

- 认为什么时候都会出问题，无论做什么都会加锁

**乐观锁：**

- 认为什么时候都不会出问题，所以不会上锁，更新数据的时候取判断一下，在此期间是否有人修改过数据

- 获取version
- 更新的时候比较version

> Redis监控测试

正常执行成功：

```bash
127.0.0.1:6379> set money 100
OK
127.0.0.1:6379> set out 0
OK
127.0.0.1:6379> watch money # 监视money
OK
127.0.0.1:6379> multi # 事务正常结束，数据期间没有发送变动，这个时候就正常执行成功
OK
127.0.0.1:6379> decrby money 80
QUEUED
127.0.0.1:6379> incrby out 20
QUEUED
127.0.0.1:6379> exec
1) (integer) 20
2) (integer) 20
```

多进程模拟异常情况,使用watch当作redis的乐观锁操作：

1. 设置money（余额）为100，out（花费）为 0，为money开启监控，开始事务，out加10，money减10，但是不立即执行事务

	```bash
	127.0.0.1:6379> set money 100
	OK
	127.0.0.1:6379> set out 0
	OK
	127.0.0.1:6379> watch money
	OK
	127.0.0.1:6379> multi
	OK
	127.0.0.1:6379> decrby money 10
	QUEUED
	127.0.0.1:6379> incrby out 10
	QUEUED
	```

2. 开启另一个进程，模拟充值操作

	```bash
	127.0.0.1:6379> get money
	"100"
	127.0.0.1:6379> set money 1000
	OK
	```

3. 这时候再执行1步骤的事务，数据发生改变，事务执行失败

	```bash
	127.0.0.1:6379> exec # 对比监视的值是否发生变化，如果发生变化则执行失败，否则成功
	(error) EXECABORT Transaction discarded because of previous errors
	```

解决办法：

- 放弃监视后再重新监视，**但是事务执行后无论成功会失败都会自动解锁，所以只需要再次监视即可**

	```bash
	127.0.0.1:6379> unwatch
	OK
	127.0.0.1:6379> watch money
	OK
	```

## Jedis

> Redis官方推荐的Java连接开发工具，使用Java操作Redis的中间件，如果要使用Java操作Redis，一定要对Jedis熟悉

### 简单测试

1. 新建maven项目，导入对应依赖

	```xml
	<dependencies>
	        <dependency>
	            <groupId>redis.clients</groupId>
	            <artifactId>jedis</artifactId>
	            <version>3.3.0</version>
	        </dependency>
	        <dependency>
	            <groupId>com.alibaba</groupId>
	            <artifactId>fastjson</artifactId>
	            <version>1.2.74</version>
	        </dependency>
	</dependencies>
	```

2. 编码测试

	- 连接数据库
	- 操作命令，即Jedis对象.方法名()，方法名即为redis命令名。
	- 断开连接

	```java
	public class TestPing {
	    public static void main(String[] args) {
	
	        // 1.创建Jedis对象,连接服务器,需要先打开Redis服务,开放端口等等
	        Jedis jedis = new Jedis("127.0.0.1",6379);
	
	        // 2.测试连接
	        System.out.println(jedis.ping());
	
	        // 3. 关闭连接
	        jedis.close();
	    }
	}
	```

### 常用API

和Redis的命令同名，即Jedis对象.命令名()

### 事务操作

```java
public class TestTX {
    public static void main(String[] args) {

        // 1.连接Redis
        Jedis jedis = new Jedis("127.0.0.1",6379);

        // 2.创建命令
        jedis.flushDB();

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("hello","world");
        jsonObject.put("name","venns");

        // 3.开启事务
        Transaction multi = jedis.multi();

        // 4.执行命令
        String result = jsonObject.toJSONString();
        //jedis.watch(result); //添加监控
        try {
            multi.set("user1",result);
            multi.set("user2",result);
            // int i = 1 / 0; //代码抛出异常，执行失败
            //如果成功则执行事务
            multi.exec();
        } catch (Exception e) {
            // 如果失败，则放弃事务
            multi.discard();
            e.printStackTrace();
        } finally {
            System.out.println(jedis.get("user1"));
            System.out.println(jedis.get("user2"));
            // 5.关闭连接
            jedis.close();
        }
    }
}
```

## SpringBoot整合Redis

在springboot2.x之后，原来使用的Jedis被替换成了lettuce

jedis：采用的是直连方式，多个线程操作的话，是不安全的，如果想要避免不安全，可以使用 jedis pool 连接池，更像BIO模式

lettuce：采用nettty，实例可以在多个线程中进行共享，不存在线程不安全的情况，可以减少线程数量，更像NIO模式

### 源码分析

```java
@ConditionalOnClass({RedisOperations.class})
@EnableConfigurationProperties({RedisProperties.class})
@Import({LettuceConnectionConfiguration.class, JedisConnectionConfiguration.class})
public class RedisAutoConfiguration {
    public RedisAutoConfiguration() {
    }

    //我们可以自己定义一个RedisTemplate来替换这个默认的
    @Bean
    @ConditionalOnMissingBean(
        name = {"redisTemplate"}
    )
    @ConditionalOnSingleCandidate(RedisConnectionFactory.class)
    public RedisTemplate<Object, Object> redisTemplate(RedisConnectionFactory redisConnectionFactory) {
        // 默认的RedisTemplate 没有过多的设置，redis 的对象都需要序列化
        // 两个泛型都是Object类型，我们后面使用需要强制转换<String,Object>
        RedisTemplate<Object, Object> template = new RedisTemplate();
        template.setConnectionFactory(redisConnectionFactory);
        return template;
    }

    // 由于String类型是Redis中最常用的类型，所以单独写了一个bean
    @Bean
    @ConditionalOnMissingBean
    @ConditionalOnSingleCandidate(RedisConnectionFactory.class)
    public StringRedisTemplate stringRedisTemplate(RedisConnectionFactory redisConnectionFactory) {
        StringRedisTemplate template = new StringRedisTemplate();
        template.setConnectionFactory(redisConnectionFactory);
        return template;
    }
}
```

### 整合测试

1. 导入依赖

	```xml
	<dependency>
	    <groupId>org.springframework.boot</groupId>
	    <artifactId>spring-boot-starter-data-redis</artifactId>
	</dependency>
	```

2. 配置连接

	```properties
	# 配置redis
	# 服务器默认就是localhost
	spring.redis.host=127.0.0.1 
	spring.redis.port=6379
	```

	如果需要配置连接池之类，推荐使用lettuce下的配置，因为jedis中有许多类未能注入成功。

3. 测试连接

	```java
	@SpringBootTest
	class RedisSpringbootApplicationTests {
	
	    @Autowired
	    private RedisTemplate redisTemplate;
	
	    @Test
	    void contextLoads() {
	
	        // RedisTemplate
	        /*
	            opsForValue 操作字符串，类似String
	            opsForList 操作list 类似List
	            opsForSet
	            opsFor...
	         */
	        // 除了基本的操作，我们常用的方法都可以直接通过redisTemplate操作，比如事务和基本的CRUD
	
	
	        // 获取Redis连接对象
	        RedisConnection connection = redisTemplate.getConnectionFactory().getConnection();
	        connection.flushDb();
	        connection.flushDb();
	
	        redisTemplate.opsForValue().set("name","venns");
	        System.out.println(redisTemplate.opsForValue().get("name"));
	    }
	
	}
	```

### 对象的序列化

RedisTemplate中的序列化设置:

```java
@Nullable
private RedisSerializer<?> defaultSerializer;
@Nullable
private ClassLoader classLoader;
@Nullable
private RedisSerializer keySerializer = null;
@Nullable
private RedisSerializer valueSerializer = null;
@Nullable
private RedisSerializer hashKeySerializer = null;
@Nullable
private RedisSerializer hashValueSerializer = null;
private RedisSerializer<String> stringSerializer = RedisSerializer.string();
```

```java
if (this.defaultSerializer == null) {
            this.defaultSerializer = new JdkSerializationRedisSerializer(this.classLoader != null ? this.classLoader : this.getClass().getClassLoader());
        }
```

可以看出：RedisTemplate默认序列化采用JDK序列化，我们可能需要JSON序列化，就需要自己写一个Redis的配置类-RedisConfig

```java
@Configurable
public class RedisConfig {

    // 编写我们自己的redisTemplate
    @Bean
    public RedisTemplate<String,Object> redisTemplate(RedisConnectionFactory redisConnectionFactory) throws UnknownHostException {
        // 为了开发方便，采用<String,Object>
        RedisTemplate<String, Object> template = new RedisTemplate<>();
        template.setConnectionFactory(redisConnectionFactory);

        // 配置具体的序列化方式
        // Json序列化配置
        Jackson2JsonRedisSerializer jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer<>(Object.class);
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
        objectMapper.activateDefaultTyping(LaissezFaireSubTypeValidator.instance);
        jackson2JsonRedisSerializer.setObjectMapper(objectMapper);

        // String 的序列化
        StringRedisSerializer stringRedisSerializer = new StringRedisSerializer();

        // key采用String的序列化
        template.setKeySerializer(stringRedisSerializer);

        // hash的key也采用String的序列化
        template.setHashKeySerializer(stringRedisSerializer);

        // value的序列化采用Jackson
        template.setValueSerializer(jackson2JsonRedisSerializer);

        // hash的value也采用Jackson
        template.setHashValueSerializer(jackson2JsonRedisSerializer);

        //使自定义序列化配置生效
        template.afterPropertiesSet();

        return template;
    }
}
```

## Redis.conf 详解

Redis启动的时候，就通过配置文件来启动

> 单位

```bash
# Note on units: when memory size is needed, it is possible to specify
# it in the usual form of 1k 5GB 4M and so forth:
#
# 1k => 1000 bytes
# 1kb => 1024 bytes
# 1m => 1000000 bytes
# 1mb => 1024*1024 bytes
# 1g => 1000000000 bytes
# 1gb => 1024*1024*1024 bytes
#
# units are case insensitive so 1GB 1Gb 1gB are all the same.

################################## INCLUDES ###################################
```

1. 配置文件对大小写不敏感

> 包含

```bash
# include .\path\to\local.conf
# include c:\path\to\other.conf
```

2. 可以包含其他的配置文件

> 网络

```bash
bind 127.0.0.1 # 绑定的ip
protected-mode yes # 开启保护模式
port 6379 # 端口配置
```

> 通用GENERAL

```bash
daemonize yes # 以守护进程的方式运行 默认是no 需要我们自己开启为yes
pidfile /var/run/redis.pid # 如果以后台的方式运行 我们需要指定一个pid进程文件

# 日志
# Specify the server verbosity level.
# This can be one of:
# debug (a lot of information, useful for development/testing)
# verbose (many rarely useful info, but not a mess like the debug level)
# notice (moderately verbose, what you want in production probably) # 生产环境使用
# warning (only very important / critical messages are logged)
loglevel notice
logfile "server_log.txt" # 日志文件的位置 如果为空就默认输出
databases 16 # 数据库的数量 默认16个数量
syslog-enabled yes # 是否显示启动logo
```

> 快照

主要用于持久化操作，Redis是内存数据库，如果没有持久化，那么数据断电即失

```bash
save 900 1 # 如果900秒内 至少有1个key进行了修改 就进行持久化操作
save 300 10 # 如果300秒内 至少有10个key进行了修改 就进行持久化操作
save 60 10000 # 如果60秒内 至少有一个key进行了修改 就进行持久化操作

stop-writes-on-bgsave-error yes # 持久化出错后 是否继续工作
rdbcompression yes # 是否压缩rdb文件 需要消耗一些cpu文件
rdbchecksum yes # 保存rdb文件的时候 进行错误的校验检查
dir ./ # rdb文件保存的目录
```

> REPLICATION 主从复制 后面详细讲解

> SECURITY 安全操作

可以在这里设置Redis密码，默认没有密码

```bash
127.0.0.1:6379> ping # 测试连接
PONG
127.0.0.1:6379> config get requirepass # 获取登录密码
1) "requirepass"
2) ""
127.0.0.1:6379> config set requirepass "123456" # 设置登录密码
OK
127.0.0.1:6379> config get requirepass # 获取密码
(error) NOAUTH Authentication required. # 提示权限不管
127.0.0.1:6379> auth 123456 # 使用密码登录
OK
127.0.0.1:6379> config get requirepass
1) "requirepass"
2) "123456"
```

> CLIENTS 限制

```bash
maxclients 10000 # 设置客户端最大连接数量
maxmemory <bytes> # redis配置的最大内存容量
maxmemory-policy noeviction # 内存上限处理策略
# 六大策略
# noeviction: 不删除策略, 达到最大内存限制时, 如果需要更多内存, 直接返回错误信息。（默认值）
# allkeys-lru: 所有key通用; 优先删除最近最少使用(less recently used ,LRU) 的 key。
# volatile-lru: 只限于设置了 expire 的部分; 优先删除最近最少使用(less recently used ,LRU) 的 key。
# allkeys-random: 所有key通用; 随机删除一部分 key。
# volatile-random: 只限于设置了 expire 的部分; 随机删除一部分 key。
# volatile-ttl: 只限于设置了 expire 的部分; 优先删除剩余时间(time to live,TTL) 短的key。
```

> APPEND NOLY 模式 aof配置

```bash
appendonly no # 默认不开启aof模式 默认采用rdb方式进行持久化
appendfilename "appendonly.aof" # 持久化的文件名

# appendfsync always # 每次修改都会 sync，消耗性能
appendfsync everysec # 每秒执行一次 sync（同步）可能会丢失这1秒的数据
# appendfsync no # 不执行 sync，操作系统自己同步数据，速度最快
```

## Redis持久化

Redis是内存数据库，如果不将内存中的数据库状态保存到磁盘，那么一旦服务器进程退出，服务器中的数据库状态也会消失，所以Redis提供了持久化功能

### RDB（Redis Database）

> 什么是RDB

在指定的时间间隔内将内存中的数据集快照写入磁盘，也就是Snapshot快照，它恢复时将快照文件直接读到内存里

Redis会单独创建一个子进程（fork）来进行持久化，会先将数据写入到一个临时文件中，待持久化过程都结束了，再用这个临时文件替换上次持久化好的文件，整个过程中，主进程是不进行任何IO操作的，这就确保了极高的性能，如果需要进行大规模数据的恢复，且对于数据恢复的完整性不是非常敏感，那RDB方式要比任何AOP方式更加的高效，RDB的缺点是最后一次持久化的数据可能丢失。

**Redis默认的持久化就是RDB，一般情况下不需要修改这个配置**

有时候在生产环境我们会将这个文件备份

**RDB保存的文件就是dump.rdb**，都可以在配置文件中进行配置

> 触发机制

1. save规则满足的情况下，会自动触发rdb规则
2. 执行fluashall命令，也会触发rdb规则
3. 退出redis，也会产生rdb文件

备份就自动生成一个dump.rdb文件

> 如何恢复rdb文件

1. 只需要将rdb文件放在我们的redis启动目录就可以了，redis启动的时候会自动检查dump.rdb文件

2. 查看我们需要存放的位置

	```bash
	127.0.0.1:6379> config get dir
	1) "dir"
	2) "/user/local/bin" # 如果在这个目录下存在dump.rdb文件。启动就会自动恢复其中的数据
	```

**Redis几乎自己的默认配置就够用了，但是我们还是需要去学习**

> rdb优缺点

优点：

1. 适合大规模的数据恢复
2. 如果对数据的完整性要求不太高

缺点：

1. 需要一定的时间间隔，如果redis意外宕机，那么最后一条修改的数据就没有了
2. fork一个进程的时候，会占用一定的内存空间

### AOF（Append Only File）

将我们所有的命令都记录下来，类似于有一个history文件，恢复的时候就将这个文件中的命令再执行一遍

> 什么是AOF

以日志的形式来记录每个写操作，将Redis执行过的所有指令记录下来（除了读的操作），只许追加文件但不可以改写文件，Redis启动之初会读取该文件重新构建数据，换言之，Redis重启的化就工具日志文件的内容将写指令从前到后执行一次以完成数据的恢复工作。

**AOF保存的是appendonly.aof文件**

> append

在配置文件中查看配置：

```bash
appendonlyno no # 默认是不开启的，我们需要手动进行配置 重启redis后生效
```

如果aof文件有错误，这时候redis是启动不起来的，我们需要进行修复，redis给我们提供了aof修复工具-**redis-check-aof**

```bash
redis-chek-aof --fix appendonly.aof
```

如果文件正常，重启就可以直接恢复

> 重写规则

aop默认就是文件的无限追加，文件会越来越大

配置文件中如下定义：

```bash
no-appendfsync-on-rewrite no # 是否开启重写机制
auto-aof-rewrite-percentage 100 # 重写占比
auto-aof-rewrite-min-size 64mb # 重写限制
```
如果aof文件大于64mb，就会fork一个新的进程进行重写

> aof优缺点

优点：

1. 每次修改都同步，文件的完整性会更好
2. 每秒同步一次，可能会丢失1秒的数据
3. 从不同步，效率最高

缺点：

1. 相对于数据文件来说，aof远远大于rdb，修复的速度也比rdb慢
2. aof运行效率也要比rdb慢，所以redis默认的配置是rdb持久化

### 扩展

1. RDB持久化方式能够在指定的时间间隔内对你的数据进行快照存储。
2. AOF 持久化方式记录每次对服务器写的操作，当服务器重启的时候会重新执行这些命令来恢复原始的数据,，AOF命令以Redis协议追加保存每次写的操作到文件末尾，Redis还能对AOF文件进行后台重写，使得AOF文件的体积不至于过大。
3. 只做缓存，如果你只希望你的数据在服务器运行的时候存在，你也可以不使用任何持久化
4. 同时开启两种持久化方式
	- 在这种情况下，当redis重启的时候会优先载入AOF文件来恢复原始的数据，因为在通常情况下AOF文件保存的数据集要比RDB文件保存的数据集要完整。
	- RDB的数据不实时，同时使用两者时服务器重启也只会找AOF文件，那要不要只使用AOF呢？作者建议不要，因为RDB更适合用于备份数据库( AOF在不断变化不好备份)，快速重启，而且不会有AOF可能潜在的Bug，留着作为一个万一的手段。
5. 性能建议
	- 因为RDB文件只用作后备用途，建议只在Slave上持久化RDB文件，而且只要15分钟备份一 次就够了，只保留 save 900 1 这条规则。
	- 如果Enable AOF，好处是在最恶劣情况下也只会丢失不超过两秒数据，启动脚本较简单只load自己的AOF文件就可以了，代价一是带来了持续的IO，二是AOF rewrite的最后将rewrite过程中产生的新数据写到新文件造成的阻塞几乎是不可避免的。只要硬盘许可，应该尽量减少AOF rewrite的频率，AOF重写的基础大小默认值64M太小了，可以设到5G以上，默认超过原大小100%大小重写可以改到适当的数值。
	- 如果不Enable AOF，仅靠Master-Slave Repllcation实现高可用性也可以，能省掉一大笔IO，也减少了rewrite时带来的系统波动。代价是如果Master/Slave 同时挂掉，会丢失 十几分钟的数据，启动脚本也要比较两个Master/Slave中的RDB文件，载入较新的那个，微博就是这种架构。

