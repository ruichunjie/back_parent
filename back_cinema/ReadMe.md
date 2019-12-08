### Hystrix
> 概念
 * Hystrix是处理延迟和容错的开源库
 * Hystrix主要用于避免级联故障，提高系统容错性
 * Hystrix解决了由于扇出导致的雪崩效应
 * 核心是隔离和熔断机制
 
>作用
 * 服务隔离和服务熔断 
 * 服务降级,限流及快速失败 
 * 请求合并和请求缓存
 * 自带单体和集群监控 

>流程
 * Hystrix调用Command/ObservableCommand
 * 判断有没有缓存
 * 熔断有没有开启
 * 限流有没有触发
 * 业务执行有没有失败
 * 业务执行有没有超时
 * 所有的失败都会触发fallback

>两种命令模式
 * HystrixCommand和HystrixObservableCommand
 * Command会以隔离的形式完成run方法调用
 * ObservableCommand使用当前线程进行调用  
     1. 引入依赖  
        ````
        <dependency>
            <groupId>com.netflix.hystrix</groupId>
            <artifactId>hystrix-core</artifactId>
            <version>1.5.18</version>
        </dependency>
        ````
     2. 继承HystrixCommand，必须要实现HystrixCommand(HystrixCommand.Setter setter) 
         (虽然体现在外面的是HystrixCommand(HystrixCommandGroupKey group)) 
         详见CommandDemo

> 请求特性
 * Hystrix请求缓存
    * Hystrix支持将请求结果进行本地缓存
    * 通过实现getCacheKey来判断是否取出缓存
    * 必须在一个上下文中
    * 可以通过RequestCacheEnabled开启 默认开启
 * Hystrix请求合并
    * Hystrix支持将多个请求合并成一个请求
    * Hystrix请求合并要求两次请求足够'近'
    * 请求合并分为局部合并和全局合并两种
    * Collapser可以设置相关参数
    
> 隔离术
 * ThreadPoolKey
    * 可以不填写,默认使用GroupKey命名线程池
    * 在Setting中加入andThreadPoolKey进行命名
 * 隔离介绍
    * 提供了信号量和线程两种隔离手段
    * 线程池会在单独的线程上执行逻辑
    * 信号量隔离在调用线程上执行
    * 优先推荐线程隔离
 * 线程隔离
    * 应用自身收到完全保护，不会收到其他依赖影响
    * 有效降低介入新服务的风险
    * 依赖服务出现问题，应用自身可以快速反应问题
    * 实时刷新动态属性减少依赖问题影响
 * 信号量隔离
    * 信号量隔离是轻量级的隔离术
    * 无网络开销的情况下推荐使用信号量隔离
    * 信号量通过计数器与请求线程进行对比进行限流的

> 降级处理
 * 触发原则
    * 除HystrixBadRequestException以外的异常
    * 运行超时或熔断器处于开启状态
    * 线程池或信号量已满
 * 快速失败
    * 提供了快速失败机制
    * 如果没有fallback，抛出异常
 
> 熔断器
 * 快照时间窗
 * 请求总数阈值
 * 错误百分比阈值
 * 状态
    * 熔断器开启: 所有请求都会进入fallback中
    * 熔断器半开启: 间歇性让请求触发run方法
    * 熔断器关闭: 正常处理业务请求
    * 默认情况下熔断器开启5秒后进入半开启状态
    
> Dashboard
  * http://localhost:8301/hystrix
  
> 