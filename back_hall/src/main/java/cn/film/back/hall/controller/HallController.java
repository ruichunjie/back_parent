package cn.film.back.hall.controller;

import cn.film.back.common.util.PageUtil;
import cn.film.back.hall.service.HallService;
import cn.film.back.hall.vo.HallSavedReqVo;
import cn.film.back.hall.vo.HallsRespVo;
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
import javax.validation.constraints.NotNull;

/**
 * @description: 播放厅服务
 * @author: xinYue
 * @time: 2019/12/7 10:26
 */
@RestController
@RequestMapping(value = "/halls")
@Slf4j
@Api(value = "播放厅服务")
public class HallController {

    @Autowired
    private HallService hallService;

    /**
     * 查询播放厅
     * @param basePageVo
     * @param cinemaId
     * @return
     */
    @GetMapping(value = "/")
    @ApiOperation(value = "获取所有的播放厅", notes = "获取所有的播放厅")
    public BaseResponseVo getAllHall(@ApiParam("分页实体") @Valid BasePageVo basePageVo,
                                     @ApiParam("影院ID") @Valid @NotNull(message = "影院ID不为空") Integer cinemaId){
        IPage<HallsRespVo> page = hallService.getAll(basePageVo.getPageNum(),basePageVo.getPageSize(),cinemaId);
        return BaseResponseVo.success(PageUtil.pageResult(page,"halls"));
    }

    /**
     * 保存播放厅
     * @param hallSavedReqVo
     * @return
     */
    @PostMapping("/hall:add")
    @ApiOperation(value = "保存播放厅", notes = "保存播放厅")
    public BaseResponseVo addHall(@ApiParam("播放厅保存实体")@RequestBody @Valid HallSavedReqVo hallSavedReqVo){
        hallService.addHall(hallSavedReqVo);
        return BaseResponseVo.success();
    }

}
