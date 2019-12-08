package cn.film.back.film.feign;

import cn.film.back.feignconf.FeignConfig;
import feign.RequestLine;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * description:
 * create_time: 2019/12/8
 * writed by xinyue
 */
@FeignClient(name = "providerTest",
       fallbackFactory = FallbackFactory.class
       // fallback = ProviderFallbackImpl.class
        //, url = "http://localhost:7101"
       // ,configuration = FeignConfig.class
)
public interface PrividerService {

    @RequestMapping(value = "/a",method = RequestMethod.GET)
    String invokerProviderController(@RequestParam("message") String message);

//    @RequestLine("GET /say/hello?message={message}")
//    String invokerProvider(@RequestParam("message") String message);
}
