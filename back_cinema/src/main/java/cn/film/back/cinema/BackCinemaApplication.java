package cn.film.back.cinema;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class BackCinemaApplication {

    public static void main(String[] args) {
        SpringApplication.run(BackCinemaApplication.class, args);
    }

}
