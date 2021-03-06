package cn.film.back.cinema.controller;

import cn.film.back.cinema.service.CinemaService;
import cn.film.back.cinema.vo.CinemaRespVo;
import cn.film.back.cinema.vo.CinemaSavedReqVo;
import cn.film.back.common.util.PageUtil;
import cn.film.back.utils.common.vo.BasePageVo;
import cn.film.back.utils.common.vo.BaseResponseVo;
import cn.film.back.utils.exception.BusinessException;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * @description: 电影院服务
 * @author: xinYue
 * @time: 2019/12/7 9:45
 */
@RestController
@RequestMapping(value = "/cinemas")
@Slf4j
@Api(value = "电影院服务")
public class CinemaController {

    @Autowired
    private CinemaService cinemaService;

    public BaseResponseVo fallbackMethod(BasePageVo basePageVo){
        return BaseResponseVo.success();
    }

    /**
     * 获取所有的电影院
     * @param basePageVo
     * @return
     */
    @HystrixCommand(fallbackMethod = "fallbackMethod",
            commandProperties = {
                    @HystrixProperty(name = "execution.isolation.strategy", value = "THREAD"),
                    @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value= "4000"),
                    @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold", value = "10"),
                    @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage", value = "50")
            },
            threadPoolProperties = {
                    @HystrixProperty(name = "coreSize", value = "1"),
                    @HystrixProperty(name = "maxQueueSize", value = "10"),
                    @HystrixProperty(name = "keepAliveTimeMinutes", value = "1000"),
                    @HystrixProperty(name = "queueSizeRejectionThreshold", value = "8"),
                    @HystrixProperty(name = "metrics.rollingStats.numBuckets", value = "12"),
                    @HystrixProperty(name = "metrics.rollingStats.timeInMilliseconds", value = "1500")
            },
    ignoreExceptions = BusinessException.class)
    @RequestMapping(value = "",method = RequestMethod.GET)
    @ApiOperation(value = "获取所有的电影院", notes = "获取所有的电影院")
    public BaseResponseVo cinemas(@ApiParam("分页对象")  @Valid BasePageVo basePageVo){
        IPage<CinemaRespVo> page = cinemaService.cinema(basePageVo.getPageNum(), basePageVo.getPageSize());
        return BaseResponseVo.success(PageUtil.pageResult(page, "cinemas"));
    }

    /**
     * 新增电影院
     * @param cinemaSavedReqVo
     * @return
     */
    @RequestMapping(value = "/cinema:ad",method = RequestMethod.POST)
    @ApiOperation(value = "新增电影院", notes = "新增电影院")
    public BaseResponseVo saveCinemas(@ApiParam("新增的对象")  @RequestBody CinemaSavedReqVo cinemaSavedReqVo){
        cinemaService.saveCinema(cinemaSavedReqVo);
        return BaseResponseVo.success();
    }

}
