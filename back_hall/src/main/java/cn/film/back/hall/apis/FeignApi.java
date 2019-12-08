package cn.film.back.hall.apis;

import cn.film.back.api.film.FilmFeignApi;
import cn.film.back.api.film.vo.FilmRespVo;
import cn.film.back.utils.common.vo.BaseResponseVo;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * description:
 * create_time: 2019/12/8
 * writed by xinyue
 */
@FeignClient(name = "")
public interface FeignApi extends FilmFeignApi {

}
