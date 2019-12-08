package cn.film.back.film.feign;

import cn.film.back.film.controller.FilmController;
import org.springframework.stereotype.Service;

/**
 * description:
 * create_time: 2019/12/8
 * writed by xinyue
 */
@Service
public class FallbackFactory implements feign.hystrix.FallbackFactory {

    @Override
    public PrividerService create(Throwable throwable) {
        return new PrividerService() {
            @Override
            public String invokerProviderController(String message) {
                return "fall back";
            }
        };
    }
}
