package cn.film.back.film.mapper;

import cn.film.back.film.entity.Film;
import cn.film.back.film.vo.FilmDetailRespVo;
import cn.film.back.film.vo.FilmRespVo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 * 影片主表 Mapper 接口
 * </p>
 *
 * @author xinyue
 * @since 2019-12-04
 */
public interface FilmMapper extends BaseMapper<Film> {

    /**获取所有的影片*/
    IPage<FilmRespVo> findAllFilm(Page<FilmRespVo> page);

    /**通过ID获取影片*/
    FilmDetailRespVo filmById(@Param("filmId")Integer filmId);

}
