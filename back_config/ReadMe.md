### 配置注意要点
1. 配置好pom文件后注意配置@EnableConfigServer
2.在配置文件上根据版本的不同配置
```$xslt
         management:
           endpoints:
             web:
               exposure:
                 exclude: ""
```
   或者
   ```$xslt
            management:
              endpoints:
                web:
                  expose: "*"
   ```
3.git上 刷新配置要配置url+"/monitor"，
本质上是对"/bus/refresh"的访问

4.客户端要同步刷新的话 对property配置类加上@RefreshScope

### 本质
> 配置来源
* 核心APi
1. BootstrapApplicationListener
2. 对@RefreshScope的对象的scope改为Refresh的， 配置修改后将对这些对象destory() 
    destroy()非常危险
