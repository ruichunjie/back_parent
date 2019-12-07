package cn.film.back.hall.mapper;

import cn.film.back.hall.entity.Field;
import cn.film.back.hall.vo.HallsRespVo;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 * 放映场次表 Mapper 接口
 * </p>
 *
 * @author xinyue
 * @since 2019-12-04
 */
public interface FieldMapper extends BaseMapper<Field> {

    /**获取播放厅*/
    IPage<HallsRespVo> halls(IPage<HallsRespVo> page,  @Param("ew")QueryWrapper queryWrapper);
}
