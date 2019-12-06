package cn.film.back.film.service;

import cn.film.back.film.mapper.ActorMapper;
import cn.film.back.film.mapper.FilmMapper;
import cn.film.back.film.vo.ActorRespVo;
import cn.film.back.film.vo.FilmDetailRespVo;
import cn.film.back.film.vo.FilmRespVo;
import cn.film.back.film.vo.FilmSavedReqVo;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @description: 影院服务
 * @author: xinYue
 * @time: 2019/12/6 11:35
 */
@Service
public class FilmService {

    @Autowired
    private ActorMapper actorMapper;
    @Autowired
    private FilmMapper filmMapper;

    /**
     * 获取所有演员
     * @param nowPage
     * @param pageSize
     * @return
     */
    public IPage<ActorRespVo> actor(long nowPage, long pageSize){
        return actorMapper.findAllActor(new Page<>(nowPage,pageSize));
    }

    /**
     * 获取所有的影片
     * @param nowPage
     * @param pageSize
     * @return
     */
    public IPage<FilmRespVo> film(long nowPage, long pageSize){
        return filmMapper.findAllFilm(new Page<>(nowPage,pageSize));
    }

    /**
     * 根据ID获取详情
     * @param filmId
     * @return
     */
    public FilmDetailRespVo filmById(Integer filmId){
        return filmMapper.filmById(filmId);
    }

    /**
     * 保存影片信息
     * @param filmSavedReqVo
     */
    public void saveFilmInfo(FilmSavedReqVo filmSavedReqVo){

    }
}
