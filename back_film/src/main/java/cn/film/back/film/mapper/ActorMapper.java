package cn.film.back.film.mapper;

import cn.film.back.film.entity.Actor;
import cn.film.back.film.vo.ActorRespVo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

/**
 * <p>
 * 演员表 Mapper 接口
 * </p>
 *
 * @author xinyue
 * @since 2019-12-04
 */
public interface ActorMapper extends BaseMapper<Actor> {

    /**查询所有的演员*/
    IPage<ActorRespVo> findAllActor(Page<ActorRespVo> page);
}
