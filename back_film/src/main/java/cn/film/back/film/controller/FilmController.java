package cn.film.back.film.controller;

import cn.film.back.common.util.PageUtil;
import cn.film.back.film.service.FilmService;
import cn.film.back.film.vo.ActorRespVo;
import cn.film.back.film.vo.FilmRespVo;
import cn.film.back.film.vo.FilmSavedReqVo;
import cn.film.back.utils.common.vo.BasePageVo;
import cn.film.back.utils.common.vo.BaseResponseVo;
import com.baomidou.mybatisplus.core.metadata.IPage;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * @description: 影院服务
 * @author: xinYue
 * @time: 2019/12/6 11:29
 */
@RestController
@RequestMapping(value = "/films")
@Slf4j
@Api(value = "影院服务")
public class FilmController {

    @Autowired
    private FilmService filmService;

    /**
     * 获取所有的演员列表
     * @param basePageVo
     * @return
     */
    @GetMapping(value = "/actors")
    @ApiOperation(value = "获取演员列表", notes = "获取演员列表")
    public BaseResponseVo actor(@Valid @ApiParam("分页请求实体") BasePageVo basePageVo){
        IPage<ActorRespVo> page = filmService.actor(basePageVo.getPageNum(),basePageVo.getPageSize());
        return BaseResponseVo.success(PageUtil.pageResult(page,"actors"));
    }

    /**
     * 获取所有的影片列表
     * @param basePageVo
     * @return
     */
    @GetMapping(value = "/")
    @ApiOperation(value = "获取所有的影片列表", notes = "获取所有的影片列表")
    public BaseResponseVo film(@Valid @ApiParam("分页请求实体")BasePageVo basePageVo){
        IPage<FilmRespVo> page = filmService.film(basePageVo.getPageNum(),basePageVo.getPageSize());
        return BaseResponseVo.success(PageUtil.pageResult(page,"films"));
    }

    /**
     * 根据ID获取详情
     * @param filmId
     * @return
     */
    @GetMapping(value = "/{filmId}")
    @ApiOperation(value = "获取影片详情", notes = "获取影片详情")
    public BaseResponseVo filmById(@ApiParam("主键ID")@PathVariable("filmId")Integer filmId){
        return BaseResponseVo.success(filmService.filmById(filmId));
    }

    /**
     * 添加影片
     * @param filmSavedReqVo
     * @return
     */
    @PostMapping(value = "/film:add")
    @ApiOperation(value = "添加影片", notes = "添加影片")
    public BaseResponseVo addFilm(@RequestBody FilmSavedReqVo filmSavedReqVo){
        filmService.saveFilmInfo(filmSavedReqVo);
        return BaseResponseVo.success();
    }
}
