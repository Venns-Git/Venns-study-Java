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

> hset [key] [field] [value]

向指定集合添加K-V键值对

> hget [key] [field]

获取指定集合中指定键的值

> hmset [key] [field] [value] [field value...]



## 三种特殊数据类型



