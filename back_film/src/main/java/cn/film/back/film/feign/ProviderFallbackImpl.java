package cn.film.back.film.feign;

import org.springframework.stereotype.Service;

/**
 * description:
 * create_time: 2019/12/8
 * writed by xinyue
 */
@Service
public class ProviderFallbackImpl implements PrividerService {
    @Override
    public String invokerProviderController(String message) {
        return "fall back";
    }
}
