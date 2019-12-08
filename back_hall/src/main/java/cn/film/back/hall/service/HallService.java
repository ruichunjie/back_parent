package cn.film.back.hall.service;

import cn.film.back.api.film.FilmFeignApi;
import cn.film.back.api.film.vo.FilmRespVo;
import cn.film.back.hall.apis.FeignApi;
import cn.film.back.hall.entity.Field;
import cn.film.back.hall.entity.HallFilmInfo;
import cn.film.back.hall.mapper.FieldMapper;
import cn.film.back.hall.mapper.HallFilmInfoMapper;
import cn.film.back.hall.vo.HallSavedReqVo;
import cn.film.back.hall.vo.HallsRespVo;
import cn.film.back.utils.common.vo.BaseResponseVo;
import cn.film.back.utils.enums.BusinessEnum;
import cn.film.back.utils.exception.BusinessException;
import cn.film.back.utils.util.ToolUtils;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.util.Objects;

/**
 * @description: 播放厅服务
 * @author: xinYue
 * @time: 2019/12/7 10:28
 */
@Service
public class HallService {

    @Resource
    private FieldMapper fieldMapper;
    @Resource
    private HallFilmInfoMapper hallFilmInfoMapper;
    @Autowired
    private LoadBalancerClient eurekaClient;
    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private FeignApi feignApi;

    /**
     * 查询播放厅
     * @param pageNum
     * @param pageSize
     * @param cinemaId
     * @return
     */
    public IPage<HallsRespVo> getAll(long pageNum, long pageSize, Integer cinemaId){
        IPage<HallsRespVo> page = new Page<>(pageNum,pageSize);
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("cinema_id", cinemaId);
        IPage<HallsRespVo> result = fieldMapper.halls(page, queryWrapper);
        return result;
    }

    /**
     * 保存播放厅
     * @param reqVo
     */
    public void addHall(HallSavedReqVo reqVo) {
        // 播放厅的列表数据
        Field field =Field.builder()
                    .cinemaId(ToolUtils.str2Int(reqVo.getCinemaId()))
                    .filmId(ToolUtils.str2Int(reqVo.getFilmId()))
                    .beginTime(reqVo.getBeginTime())
                    .endTime(reqVo.getEndTime())
                    .hallId(ToolUtils.str2Int(reqVo.getHallTypeId()))
                    .hallName(reqVo.getHallName())
                    .price(ToolUtils.str2Int(reqVo.getFilmPrice()))
                    .build();

        fieldMapper.insert(field);
        // 播放厅对应的影片数据， 影片冗余数据， 缓存里有一份
        HallFilmInfo hallFilmInfo = filmInfo(reqVo.getFilmId());
        hallFilmInfoMapper.insert(hallFilmInfo);
    }

    /**
     * 获取播放厅对应的影片
     * @param filmId
     * @return
     */
//    private HallFilmInfo filmInfo(String filmId){
//
//        //1. 通过client 2. 配置RestTemplateBean 用@LoadBalance标注 restTemplate直接通过服务名获取
//        ServiceInstance choose = eurekaClient.choose("back-film");
//        String hostname = choose.getHost();
//        int port = choose.getPort();
//        String uri = "/films/"+filmId;
//        String url = "http://"+hostname+":"+port + uri;
//        // 通过restTemplate调用影片服务
//        JSONObject baseResponseVO = restTemplate.getForObject(url, JSONObject.class);
//        // 解析返回值
//        JSONObject dataJson = baseResponseVO.getJSONObject("data");
//        // 组织参数
//        HallFilmInfo hallFilmInfo = HallFilmInfo.builder()
//                                .filmId(dataJson.getIntValue("filmId"))
//                                .filmName(dataJson.getString("filmName"))
//                                .filmLength(dataJson.getString("filmLength"))
//                                .filmCats(dataJson.getString("filmCats"))
//                                .actors(dataJson.getString("actors"))
//                                .imgAddress(dataJson.getString("imgAddress"))
//                                .build();
//        return hallFilmInfo;
//    }
    private HallFilmInfo filmInfo(String filmId){
        BaseResponseVo<FilmRespVo> filmRespVoBaseResponseVo = feignApi.filmById(Integer.valueOf(filmId));
        FilmRespVo filmRespVo = filmRespVoBaseResponseVo.getData();
        if(Objects.isNull(filmRespVo)){
            throw new BusinessException(BusinessEnum.INTERNALSERVERERROR);
        }
        HallFilmInfo hallFilmInfo = HallFilmInfo.builder()
                        .filmId(Integer.valueOf(filmRespVo.getFilmId()))
                        .filmName(filmRespVo.getFilmName())
                        .filmLength(filmRespVo.getFilmLength())
                        .filmCats(filmRespVo.getFilmEnName())
                        .actors(filmRespVo.getFilmTime())
                        .imgAddress(filmRespVo.getMainImg())
                        .build();
        return hallFilmInfo;
    }
}
