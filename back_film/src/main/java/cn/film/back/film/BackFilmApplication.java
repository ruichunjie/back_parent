package cn.film.back.film;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("cn.film.back.film.mapper")
public class BackFilmApplication {

    public static void main(String[] args) {
        SpringApplication.run(BackFilmApplication.class, args);
    }

}
