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



