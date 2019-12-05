### Eureka核心特性
#### 服务注册
 1. Eureka Client在第一次心跳时向Eureka Server 注册  
 2. 注册会提供诸多自身元数据: 主机名， 端口， 健康指标URL等

#### 服务续约
 1. Eureka Client通过发送心跳进行续约
 2. 默认情况下每30秒发送一次心跳  
 3. 如果90秒内Eureka Server未收到续约，则进行服务剔除
 
#### 服务下线
 1. Eureka Client优雅退出时，会发送cancel命令  
 2. Eureka Server收到cancel命令后会删除该节点

#### 获取注册列表信息
 1. Eureka Client会缓存由Server获取注册表信息  
 2. Eureka Client会定期更新注册表信息(默认30秒)  
 3. Eureka Client会处理注册表的合并

### 重点
#### 多注册中心的比较
 1. 分布式基础 CAP理论   
    一致性: Consistancy   
    可用性: Avaliable  
    分区容错性: Partition tolerance   
    在任何一个时刻内，不可能有系统同时满足CAP  
    Eureka AP  
    Zookeeper CP 
 
#### Eureka注册慢
 1. 注册慢的根本原因在于Eureka的AP特性  
 2. Eureka Client延迟注册, 默认30秒
 3. Euraka Server响应缓存，默认30秒  
 4. Eureka Server缓存刷新, 默认30秒
 
#### Eureka自我保护
 1. Eureka Server会自动更新续约更新阀值  
 2. Eureka Server续约更新频率低于阀值则进入保护模式
 3. 保护模式下Eureka Server不会剔除任何注册信息