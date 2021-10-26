# ExtremeWorldNetty

使用 Kotlin + Netty + Spring 实现的《极世界》Unity培训项目的服务端。

课程上的技术选型和代码架构设计实在令人难以接受，故考虑使用 Kotlin + Netty 以一个更加清爽和易懂的设计来重写。

主要目标：
* 可以跨平台开发和运行，不局限于 Windows 平台；
* 不依赖 MS SQL Server 数据库，可以迁移至 MySQL 或其他数据库平台（也是为了跨平台）；
* 使用更加纯粹的 SQL 操作；
* 代码的分层和职责划分更加清晰易懂，使用 Spring 和依赖注入机制大量减少单例模式的直接使用，使依赖关系更加清晰；
* 减少套路化代码的编写，如各种 `NetMessage` 的封装，使用注解等机制实现自动的消息路由分发；
