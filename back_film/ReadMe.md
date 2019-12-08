# Feign

|Rest框架  |适用场景   |请求映射注解 |请求参数|
|---      |---     |----| ----|
|Feign    |客户端声明|@RequestLine|@Param
|Spring Cloud Open Feign |客户端声明|@RequestMapping|@RequestParam
|JAX-RS   | 客户端服务端声明|@Path| @*Param
|Spring Web MVC|服务端声明|@RequestMapping|@RequestParam

Spring Cloud Open Feign 利用Feign高扩展性，使用标准Spring Web MVC注解
来声明客户端Java接口   
注解扩展性: -Feign  
HTTP 请求处理: -Feign  
Rest请求元信息解析: -Feign  
Spring Cloud Open Feign:  
提供Spring Web Mvc注解处理  
提供Feign自动装配  

### Feign 
#### 扩展支持
 * 内建Feign注解
 * JAX-RX 1/2注解
 * JAXB
 * OkHttp
#### 基本操作
  * 增加依赖  
    org.springframework.cloud:spring-cloud-starter-openfeign
  * 激活Feign客户端
    @EnableFeignClients
  * 定义Feign的接口  
  ```
    @FeignClient("stores")
    public interface storeClient{
         @RequestMapping
         List<Store> getStores();
            
         @RequestMapping(consumes="application/json")
         Store update();
    }
  ```
#### 核心 - 实现细节
  * @EnableFeignClients -> FeignClientsRegister
    主要工作 注册默认配置 注册所有标注@FeignClient配置类
    FeignClientsRegistrar # registerDefaultConfiguration
  * Spring Web MVC 注解元信息解析
     * Contract 提供Feign接口方法与Rest请求元信息契约
        * Feign内建实现
           * @RequestLine
           * @Headers
        * JAX-RS1/2实现
           * @Path
           * @PathParam
           * @HeaderParam
        * Spring Web Mvc实现
  * 通过@FeignClient生成代理对象的方法调用实现HTTP调用  
  * 通过SpringDecoder实现Response与接口返回类型的反序列化  
  * 负载均衡 
    * Spring Cloud 替换了 Client的实现,用LoadbalancerFeignClient
  * 重试
    * Spring Cloud会在外部包装Feign接口
  * 熔断
    * HystrixFeign
    
> feign 支持Spring MVC
1. 支持 5种注解 @RequestMapping @RequestParam @PathVariable @RequestHeader @RequestBody
2. 不支持 @GetMapping @PostMapping
3.@RequestParam 一定要加上 
4. @FeignClient 
        path 公共url部分 
        primary 优先级
5. feign 内的配置

| 配置类型 | 默认配置|
|---      |---     |
|feignDecoder | ResponseEntityDecoder|
|feignEncoder | SpringEncoder|
|feignLogger| Slf4jLogger|
|feignContract| SpringMvcContract|
|feignBuilder| HystrixFeign.Builder|
|feignClient| LoadBalancerFeignClient或者feignClient

6.自定义配置要在@SpringApplication之外

> hystrixFeign
1. feign.hystrix.enabled=true

>优化
1. feign 默认使用JDK自带的HTTP实现,详见FeignRibbonCLientAutoConfiguration,
更换HTTP实现， 目前Apache HTTPClient是很好的选择
````
 <dependency>
            <groupId>io.github.openfeign</groupId>
            <artifactId>feign-httpclient</artifactId>
        </dependency>
````
feign.httpclient.enable=true  
2.HTTP解压缩,feign可以支持GZip的请求  
feign.compression.request.enabled=true  
feign.compression.request.mime-types=text/xml,application/xml,application/json  
feign.compression.request.min-request-size=2048  
feign.compression.response.enabled=true

>继承
1. 接口复用最多只能有一层
