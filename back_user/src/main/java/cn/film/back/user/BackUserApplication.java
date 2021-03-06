package cn.film.back.user;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication(scanBasePackages = "cn.film.back")
@EnableDiscoveryClient
public class BackUserApplication {
    public static void main(String[] args) {
        SpringApplication.run(BackUserApplication.class, args);
    }

}
