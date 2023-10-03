# Transaction

事务管理：解决线程冲突。临界区加锁

原子性事务，同时成功或者回退

### What Is a Transaction?

Java Transaction API (JTA) 

编程实现：UserTranscation

Annotation方式实现：声明事务边界

> Try
>
> ​	begin transaction
> ​	 debit checking account
> ​	 credit savings account
> ​	 update history log
> ​	commit transaction 
>
> Catch
> 	rollback 

**Rollback**

事务性资源：支持回滚（数据库和Kafka），恢复硬盘持久化资源

Tomcat自己不回滚：变量在内存里

人工解决：进入transcation缓存——判断transaction是否成功——恢复缓存。

### Container-Managed Transactions

Annotation实现：靠容器解决

> Eg. 
>
> 交易{
>
> ​	转账{
> ​		withdraw()
> ​		deposit()	
>
> ​	}
> }

@transactional()

- Required：没有事务context，就新开一个事务。有事务context，只有在整个事务结束了才能提交

- RequriesNew：原先的事务context会被挂起，必须自己新开一个，提交后，再恢复上层事务

  > withdraw和deposit在两个事务中执行

- NotSupported：有事务先挂起

- Supports：如果没有事务，非事务方式执行

- Mandatory：必须在事务中，不然抛异常

- NEVER：有事务抛异常

### Isolation and Database Locking

用户之间的隔离

**Dirty Read**：写读冲突。一个事务还没有结束，另一个用户读到了错误的消息（中间状态，如果rollback，刚刚看到的就是错误的值）

**Unrepeatable read**：读写冲突，两次read之间被更改，read不一致

**Dirty Write**：写写冲突，对旧的东西write，并且最后覆盖了正确数据

**isolation：**越往下越严格，性能越差

- READ_UNCOMMITTED：可以读中间状态，可能是错的。
- READ_CONMMITTED：不可重复读、幻读（Phantom Read）
- REPEATBALE_READ：读到的数据加锁，别人不能改，幻读（Phantom Read：只锁了记录，表可能新插入值）
- SERIALIZABLE：锁住了一整张table：串行化

> MySQL workbench可以使设计隔离级别
>
> 连接数据库的时候connnection
>
> Spring中Transcational中设置isolation的值：依赖于底层（不能比底层严格）



**Lock**：取决于数据库事务管理系统

- Read Lock：读到这里锁死

- Write Lock：不能改，但可以Dirty Read

- Exclusive write lock：

- Snapshot：快照，冻结视角。rollback到快照，相当于产生一个新的数据库（副本），浪费空间。

  > fork一个进程：增量式，只记录改动，其余指针指到原来的地方



### Updating Multiple Databases

分布式事务（资源管理器是多个），eg事务在同一台机器的两个数据库中完成。两个数据库中间需要一个Mediator，协商是否都完成write

**Two-phase commit**：

tomcat中存在**Transaction Manager**，有一票否决，其中一个失败，都rollback。

- 准备阶段：多个事务发送个Mediator，是否能完成
- 执行阶段：都能完成再提交，否则rollback

启发式异常：第二阶段的广播没发送成功。事务启发式执行（guess），陷入无法修复的状态



Optimistic Offiline Lock：

- 保持乐观，不加锁
- 有校验机制：是否有改写
- 增加版本号：write操作递增版本号
  - 陈旧版本号扔掉
  - 尝试merge


Coarse-Grained Lock：关联表（eg. order和order_item），希望两边锁。数据库管理机制只能锁一张table



### 可串行化调度

**调度**：read和write组成的序列

可串行化调度：给定一个并发调度，存在串行调度方式，使得结果相同

**数据库如何校验？**通过找充分性条件（子集）

> eg. 
> T1：A转出钱；B收到钱
> T2：A涨了5%，B涨了5%
>
> 

**冲突可串行化**：找到冲突，推导出串行的方案

**如何验证？**优先图无环：有向图表示。根据无环图得到**拓扑排序**。



### 系统恢复Log

系统崩溃影响：原子性，持久型

binlog ；redo log ；undo log

1. 原子性保证
   1. NO_STEAL：没有结束的不能刷硬盘（脏页）。但是占内存
   2. STEAL：未结束也可以刷硬盘，需要回滚。影响原子性。**Undo**
2. 持久型保证
   1. Force：一完成强制写入。但性能差
   2. No-Force：不强制立刻写，影响持久型，需要重做。**Redo**

**怎么写log**：

log写入磁盘不会被改，顺序写append，高速

**写数据库和log是否是3倍IO时间**：log是顺序写的，比数据库（random）快



**Undo log**

1. 格式：<T,X,old_value>，T是事务标识，X是数据项。

   找到T，将被改的数据项X恢复为old_value

2. 使用时机：回滚



|          | Force | No-Foce |
| -------- | ----- | ------- |
| NO-STEAL |       |         |
| STEAL    |       |         |

**Redo**

记录新值



**为什么要两个log？**使用场景不一样，在任何一个条件下，只使用一个文件。每次打开都需要打开很大的文件。分开效率更高



**预写日志WAL**

Write Ahead Logging。必须先写成功了才能操作事务。

Undo Redo都属于预写日志



**日记记录方案**

1. 逻辑日志：记录执行了SQL语句（抽象）
2. 物理日志：只记录数据值的变化
3. 物理逻辑日志：页面的物理信息，页面内记录逻辑
   1. 第100页发生变化
   2. 小明的年龄从20变成200

**日志性质**

1. 幂等性：执行多次依然一样。

   物理日志满足，逻辑不满足。数据库update不满足，可能第一次update改变了where的条件

2. 失败可重做性：可以通过重做来达到恢复目的

   物理日志可以；逻辑不行，重做一次效果可能不一样了。**所以redo用物理**

3. 可逆性：逆向操作log

   物理不可逆（比如offset变了），逻辑可以，**所以undo用逻辑**



**事务特性**：

1. a：不可分割，全部成功
2. c：
3. i：读写冲突，存在dirty read
4. durability：在flush到硬盘的过程中数据丢失
