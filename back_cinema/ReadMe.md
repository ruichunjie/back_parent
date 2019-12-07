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
     3.
