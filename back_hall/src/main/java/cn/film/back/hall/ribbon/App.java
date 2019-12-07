package cn.film.back.hall.ribbon;

import com.netflix.client.ClientFactory;
import com.netflix.client.http.HttpRequest;
import com.netflix.client.http.HttpResponse;
import com.netflix.config.ConfigurationManager;
import com.netflix.loadbalancer.ZoneAwareLoadBalancer;
import com.netflix.niws.client.http.RestClient;

import java.net.URI;

/**
 * @description: 测试负载均衡
 * @author: xinYue
 * @time: 2019/12/7 11:20
 */
public class App {

    public static void main(String[] args) throws Exception {
        //读取配置文件
        ConfigurationManager.loadPropertiesFromResources("balance.properties");

        // 构建一个HttpClient，serverList固定的情况
        RestClient client = (RestClient) ClientFactory.getNamedClient("client");
        HttpRequest request = HttpRequest.newBuilder().uri(new URI("/")).build();

//        for (int i = 0; i < 5; i++)  {
//            HttpResponse response = client.executeWithLoadBalancer(request);
//            System.err.println("Status code for " + response.getRequestedURI() + "  :" + response.getStatus());
//        }

        ZoneAwareLoadBalancer lb = (ZoneAwareLoadBalancer) client.getLoadBalancer();
        System.err.println(lb.getLoadBalancerStats());
        ConfigurationManager.getConfigInstance().setProperty(
                "client.ribbon.listOfServers", "www.qq.com:80,www.taobao.com:80");
        Thread.sleep(3000);

        for (int i = 0; i < 5; i++)  {
            HttpResponse response = client.executeWithLoadBalancer(request);
            System.err.println("Status code for " + response.getRequestedURI() + "  : " + response.getStatus());
        }
        System.out.println(lb.getLoadBalancerStats());

    }

}
