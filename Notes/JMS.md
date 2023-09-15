# JMS

JAVA Message Service，Tomcat内置

> 同步（打电话）：
>
> 1. 紧耦合（根据对方的API编程，如http头请求）；
> 2. 没有保证到达（断网），没有缓存机制；
> 3. 发送后，处于等待返回状态，不能连续发
> 4. 强调是（request，response）

异步通信：（发信息）

1. 中介者Agent，不直接交互，解耦合（不依赖api具体实现）
2. 没有阻塞，不用处于等待状态
3. Agent有缓存期限，防止缓存满了
4. 编程复杂
5. 不能马上得到结果

### Messaging in JAVA

1. 对等收发，无主从
2. 格式：
   1. Header
   2. Properties（optional，如name），可自主扩展
   3. Body（optional）

**JMS API Architecture**

1. JNDI Namespace树下找到对应k-v
2. CF：Connection Factory创建连接
3. D：找到目的地

### Message Style

1. PTP：点对点

   one message， one consumer

2. publish/subscribe

   - 使用topic交互
   - client有selector，筛选type

   - 消息properties中加入type

### JMS Programming Model

<img src="/Users/dxm/Library/Application Support/typora-user-images/image-20230913084159877.png" width=70%>



**Durable subscirption**

不会丢

Browser：不消费，只是读





## Kafka

消息中间键，松散耦合

实现机制：消息有序写入文件log。（在硬盘，但是末尾追加，顺序写，快速）

Offset：每个consumer记录自己读到哪里

Topic：消息任意类型；可分区，并行

Event（消息）：Header（属性），Key（Topic分区，hash(key)），Timestamp，Value（内容）

Cluster：ZooKeeper管理

Customer Groups：发心跳