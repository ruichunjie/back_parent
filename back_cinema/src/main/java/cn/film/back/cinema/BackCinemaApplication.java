package cn.film.back.cinema;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;

@SpringBootApplication(scanBasePackages = "cn.film.back")
@EnableEurekaClient
@EnableHystrix
@EnableHystrixDashboard
public class BackCinemaApplication {

    public static void main(String[] args) {
        SpringApplication.run(BackCinemaApplication.class, args);
    }

}
