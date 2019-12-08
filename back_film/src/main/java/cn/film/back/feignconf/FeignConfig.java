package cn.film.back.feignconf;

import feign.Contract;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * description:
 * create_time: 2019/12/8
 * writed by xinyue
 */
@Configuration
public class FeignConfig {

    @Bean
    public Contract contract(){
        return new feign.Contract.Default();
    }
}
