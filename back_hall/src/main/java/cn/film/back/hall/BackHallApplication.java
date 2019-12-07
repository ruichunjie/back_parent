package cn.film.back.hall;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication(scanBasePackages = "cn.film.back")
@EnableDiscoveryClient
public class BackHallApplication {

    public static void main(String[] args) {
        SpringApplication.run(BackHallApplication.class, args);
    }

}
