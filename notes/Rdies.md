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

## 基本命令

```bash
redis-cli -p 6379 # 使用Redis客户端进行连接
ping # 测试连接，连接成功返回pong
set xxx xxx # 创建键值对
get xxx # 获取ky对应的value
keys * # 查看所有的key
```

## 测试性能

**redis-benchmark**  压力测试工具
