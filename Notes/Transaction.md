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

**Dirty Read**：一个事务还没有结束，另一个用户读到了错误的消息（中间状态，如果rollback，刚刚看到的就是错误的值）

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























