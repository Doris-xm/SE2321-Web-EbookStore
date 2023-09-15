# Intro

1. 数据库放在内网，防止从外网访问

   > 数据库放在本地，可以通过本机ip：3306黑进

2. 分布式缓存

3. 服务器不够：集群。负载均衡服务器

4. 数据库不够用：集群。需要数据持久化，分主从，write只能在主。保证读到数据一致性。

5. CDN服务器：反向代理

   > 代理：所有人都使用代理ip

6. 统一数据访问网关：数据库扩容，保证代码不变（统一的Dao层屏蔽具体数据来源，都视为object）

7. 异步通讯



### 有状态&无状态

1. HTTP无状态：不为某个服务器维护状态

   > 状态：对象。占资源

2. Spring Bean Scopes：@Scope("")

   > 多个用户访问时，有几个controller实例？
   >
   > scope参数：
   >
   > 默认singleton：所有用户一个实例
   >
   > prototype：每次新的实例
   >
   > session：开一个session新建一个实例

   其中，singleton是stateless，prototype是stateful


### Database Connection Pool Size

Tomcat建立数据库连接池，建立n个连接，访问时选择空的连接，

执行完后，连接不断开，连接**收回**

