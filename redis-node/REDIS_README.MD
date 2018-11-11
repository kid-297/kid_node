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
 - 集合类型(seet)
 
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