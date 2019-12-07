### 负载均衡 Ribbon
> server list 
1. 配置 在DefaultClientConfigImpl里
2. 默认采用轮询 
3. 如果不想使用eureka 配置 ribbon.eureka.enable =false即可

> IRule
1. IRule常使用BestAvailableRule和WeightedResponseTimeRule
2. 常用算法: 
    * RoundRobinRule 轮询
    * RandomRule 随机
    * AvailabilityFilteringRule 可用过滤规则
    * WeightedResponseTimeRule根据平均相应时间计算比重 
    * RetryRule 遵循轮询算法， 但会对失败的请求重试 
    * BestAvailableRule 结合了可用过滤规则和响应时长规则
    * ZoneAvoidanceRule 复合判断了服务所在区域性能和可用性选择服务器 
3.自定义及切换 
    参照RestConfig 和 MyIRule 
    
> IPing
1. 探测服务存活状态,保证服务可用
2. 常见: NIWSDiscoveryClient PingUrl
3. 实现：
    * NIWSDiscoveryClient 不进行探测, 根据Eureka Client反馈判断存活
    * PingUrl 使用HttpClient对PingUrl进行Ping操作
    * DummyPing "人性本善", 默认返回true  
    * NoOPPing 永远返回true
