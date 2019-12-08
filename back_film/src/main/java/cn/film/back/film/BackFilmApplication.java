package cn.film.back.film;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication(scanBasePackages = "cn.film.back")
@EnableDiscoveryClient
@EnableFeignClients
public class BackFilmApplication {

    public static void main(String[] args) {
        SpringApplication.run(BackFilmApplication.class, args);
    }

}
