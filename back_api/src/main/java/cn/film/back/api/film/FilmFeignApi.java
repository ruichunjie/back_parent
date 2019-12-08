package cn.film.back.api.film;

import cn.film.back.api.film.vo.FilmRespVo;
import cn.film.back.utils.common.vo.BaseResponseVo;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * description: FilmFeignApi 对外暴露的接口服务
 * create_time: 2019/12/8
 * writed by xinyue
 */
public interface FilmFeignApi {

    @RequestMapping (value = "/{filmId}")
    BaseResponseVo<FilmRespVo> filmById(@PathVariable("filmId")Integer filmId);


}
