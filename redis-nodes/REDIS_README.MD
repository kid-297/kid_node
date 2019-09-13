# NoSQL(not only sql) 非关系型数据库
- High performance 高并发读写
- Huge Storage 海量数据的高效率存储和访问
- High Scalability && High Availability 高可扩展和高可用性
- 包括redis、mongoDB等

# NoSQL数据库的四大分类
- 键值（Key-Velue）存储
- 列存储
- 文档数据库
- 图形数据库

# NoSQL的特点
- 易扩展
- 灵活的数据模型
- 大数据量，高性能
- 高可用

# Redis
 高新能键值对数据库，支持的键值数据类型：
 - 字符串类型(String)
 - 列表类型(list)
 - 有序集合类型(sorted set)
 - 散列类型(hash)
 - 集合类型(set)
 
 # 应用场景
 - 缓存
 - 任务队列
 - 应用排行榜
 - 网站访问统计
 - 数据过期处理
 - 分布式集群架构中的session分离
 
 # redis特性
 - 多数据库
 - 支持事务
 
 # redis持久化
 - RDB持久化：在指定的时间里将数据写入磁盘
 - AOF持久化: 将通过日志的形式记录操作
 - 无持久化
 - 同时使用两个 
 
 # jedis 配置
 - maxActive：控制一个pool可分配多少个jedis实例，通过pool.getResource()来获取；如果赋值为-1，则表示不限制；如果pool已经分配了maxActive个jedis实例，则此时pool的状态就成exhausted了，在JedisPoolConfig
 - maxIdle：控制一个pool最多有多少个状态为idle的jedis实例；
 - whenExhaustedAction：表示当pool中的jedis实例都被allocated完时，pool要采取的操作；默认有三种WHEN_EXHAUSTED_FAIL（表示无jedis实例时，直接抛出NoSuchElementException）、WHEN_EXHAUSTED_BLOCK（则表示阻塞住，或者达到maxWait时抛出JedisConnectionException）、WHEN_EXHAUSTED_GROW（则表示新建一个jedis实例，也就说设置的maxActive无用）；
 - maxWait：表示当borrow一个jedis实例时，最大的等待时间，如果超过等待时间，则直接抛出JedisConnectionException；
 - testOnBorrow：在borrow一个jedis实例时，是否提前进行alidate操作；如果为true，则得到的jedis实例均是可用的；
 - testOnReturn：在return给pool时，是否提前进行validate操作；
 - testWhileIdle：如果为true，表示有一个idle object evitor线程对idle object进行扫描，如果validate失败，此object会被从pool中drop掉；这一项只有在timeBetweenEvictionRunsMillis大于0时才有意义；
 - timeBetweenEvictionRunsMillis：表示idle object evitor两次扫描之间要sleep的毫秒数；
 - numTestsPerEvictionRun：表示idle object evitor每次扫描的最多的对象数；
 - minEvictableIdleTimeMillis：表示一个对象至少停留在idle状态的最短时间，然后才能被idle object evitor扫描并驱逐；这一项只有在timeBetweenEvictionRunsMillis大于0时才有意义；
 - softMinEvictableIdleTimeMillis：在minEvictableIdleTimeMillis基础上，加入了至少minIdle个对象已经在pool里面了。如果为-1，evicted不会根据idle time驱逐任何对象。如果minEvictableIdleTimeMillis>0，则此项设置无意义，且只有在timeBetweenEvictionRunsMillis大于0时才有意义；
 - lifo：borrowObject返回对象时，是采用DEFAULT_LIFO（last in first out，即类似cache的最频繁使用队列），如果为False，则表示FIFO队列；
 
 - 其中JedisPoolConfig对一些参数的默认设置如下：
    - testWhileIdle=true
    - minEvictableIdleTimeMills=60000
    - timeBetweenEvictionRunsMillis=30000
    - numTestsPerEvictionRun=-1
    
 # 分布式之redis复习精讲
 [分布式之redis复习精讲](https://www.cnblogs.com/rjzheng/p/9096228.html)
 
 # 
 读多写少用缓存
 读少写多用消息队列
 
 # redis经典面试题
 1.redis有哪些数据结构
     - 字符串类型(String)
     - 列表类型(list)
     - 有序集合类型(sorted set)
     - 散列类型(hash)
     - 集合类型(set)
 2.redis如何做持久化的？
     - RDB和AOF的方式
        - RDB 全量 将数据全部定时存储在硬盘
        - AOF 增量 记录命令 类似日志，将每条指令记录下来
 3.redis的同步机制了解么？
 
 4.redis适合的场景
 
 