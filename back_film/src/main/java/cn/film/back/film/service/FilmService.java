package cn.film.back.film.service;

import cn.film.back.film.entity.Film;
import cn.film.back.film.entity.FilmActor;
import cn.film.back.film.entity.FilmInfo;
import cn.film.back.film.mapper.ActorMapper;
import cn.film.back.film.mapper.FilmActorMapper;
import cn.film.back.film.mapper.FilmInfoMapper;
import cn.film.back.film.mapper.FilmMapper;
import cn.film.back.film.vo.ActorRespVo;
import cn.film.back.film.vo.FilmDetailRespVo;
import cn.film.back.film.vo.FilmRespVo;
import cn.film.back.film.vo.FilmSavedReqVo;
import cn.film.back.utils.exception.BusinessException;
import cn.film.back.utils.util.ToolUtils;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static cn.film.back.utils.enums.BusinessEnum.NOCONSISTANT;

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
    @Autowired
    private FilmInfoMapper filmInfoMapper;
    @Autowired
    private FilmActorMapper filmActorMapper;

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
     * @param reqVo
     */
    @Transactional(rollbackFor = Exception.class)
    public void saveFilmInfo(FilmSavedReqVo reqVo){
        // 保存电影主表
        Film film = Film.builder()
                .filmName(reqVo.getFilmName())
                .filmType(ToolUtils.str2Int(reqVo.getFilmTypeId()))
                .imgAddress(reqVo.getMainImgAddress())
                .filmScore(reqVo.getFilmScore())
                .filmPresalenum(ToolUtils.str2Int(reqVo.getPreSaleNum()))
                .filmBoxOffice(ToolUtils.str2Int(reqVo.getBoxOffice()))
                .filmSource(ToolUtils.str2Int(reqVo.getFilmSourceId()))
                .filmCats(reqVo.getFilmCatIds())
                .filmArea(ToolUtils.str2Int(reqVo.getAreaId()))
                .filmDate(ToolUtils.str2Int(reqVo.getDateId()))
                .filmTime(ToolUtils.str2LocalDateTime(reqVo.getFilmTime()+" 00:00:00"))
                .filmStatus(ToolUtils.str2Int(reqVo.getFilmStatus())).build();

        filmMapper.insert(film);
        // 保存电影子表
        FilmInfo filmInfo = FilmInfo.builder()
                        .filmId(film.getUuid()+"")
                        .filmEnName(reqVo.getFilmEnName())
                        .filmScore(reqVo.getFilmScore())
                        .filmScoreNum(ToolUtils.str2Int(reqVo.getFilmScorers()))
                        .filmLength(ToolUtils.str2Int(reqVo.getFilmLength()))
                        .biography(reqVo.getBiography())
                        .directorId(ToolUtils.str2Int(reqVo.getDirectorId()))
                        .filmImgs(reqVo.getFilmImgs())
                        .build();


        filmInfoMapper.insert(filmInfo);

        String[] actorId = reqVo.getActIds().split("#");
        String[] roleNames = reqVo.getRoleNames().split("#");
        if(actorId.length != roleNames.length){
            throw new BusinessException(NOCONSISTANT);
        }

        for(int i=0;i<actorId.length;i++){
            // 保存演员映射表
            FilmActor filmActor = FilmActor.builder()
                                .filmId(film.getUuid())
                                .actorId(ToolUtils.str2Int(actorId[i]))
                                .roleName(roleNames[i])
                                .build();
            filmActorMapper.insert(filmActor);
        }
    }
}
