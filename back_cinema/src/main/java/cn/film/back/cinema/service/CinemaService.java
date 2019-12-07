package cn.film.back.cinema.service;

import cn.film.back.cinema.entity.Cinema;
import cn.film.back.cinema.mapper.CinemaMapper;
import cn.film.back.cinema.vo.CinemaRespVo;
import cn.film.back.cinema.vo.CinemaSavedReqVo;
import cn.film.back.utils.util.ToolUtils;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @description: 电影服务
 * @author: xinYue
 * @time: 2019/12/7 9:46
 */
@Service
public class CinemaService {

    @Autowired
    private CinemaMapper cinemaMapper;

    /**
     * 查询
     * @param pageNum
     * @param pageSize
     * @return
     */
    public IPage<CinemaRespVo> cinema(Integer pageNum, Integer pageSize) {
        // 查询
        IPage<Cinema> cinemaIPage = cinemaMapper.selectPage(new Page<>(pageNum,pageSize), null);

        //组装对象 todo 此时转化需要核实
        IPage<CinemaRespVo> cinemaRespVoPage = new Page<>();
        List<CinemaRespVo> list = cinemaIPage.getRecords().stream().map(e->cinemaEntity2Vo(e)).collect(Collectors.toList());
        cinemaRespVoPage.setRecords(list);
        cinemaRespVoPage.setCurrent(cinemaIPage.getCurrent());
        cinemaRespVoPage.setPages(cinemaIPage.getPages());
        cinemaRespVoPage.setSize(cinemaIPage.getSize());
        cinemaRespVoPage.setTotal(cinemaIPage.getTotal());
        return cinemaRespVoPage;
    }

    /**
     * 转换
     * @param cinema
     * @return
     */
    private CinemaRespVo cinemaEntity2Vo(Cinema cinema){
        CinemaRespVo cinemaRespVo = CinemaRespVo.builder()
                                .areaId(cinema.getAreaId()+"")
                                .brandId(cinema.getBrandId()+"")
                                .cinemaAddress(cinema.getCinemaAddress())
                                .cinemaImgAddress(cinema.getImgAddress())
                                .cinemaName(cinema.getCinemaName())
                                .cinemaPrice(cinema.getMinimumPrice()+"")
                                .cinemaTele(cinema.getHallIds())
                                .build();
        return cinemaRespVo;
    }

    /**
     * 保存 todo 核实参数
     * @param reqVo
     */
    public void saveCinema(CinemaSavedReqVo reqVo) {
        Cinema cinema = Cinema.builder()
                    .cinemaName(reqVo.getCinemaName())
                    .cinemaAddress(reqVo.getCinemaAddress())
                    .cinemaPhone("")
                    .areaId(ToolUtils.str2Int(reqVo.getAreaId()))
                    .brandId(ToolUtils.str2Int(reqVo.getBrandId()))
                    .hallIds("")
                    .minimumPrice(0)
                    .build();
        cinemaMapper.insert(cinema);
    }
}
