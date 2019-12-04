package cn.film.back.common.entity;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;
/**
 * <p>
 * 影厅电影信息表
 * </p>
 *
 * @author xinyue
 * @since 2019-12-04
 */
@TableName("cn_hall_film_info")
@Data
@Builder
public class HallFilmInfo extends Model<HallFilmInfo> {

    private static final long serialVersionUID = 1L;

    /**
     * 主键编号
     */
    @TableId(value = "UUID", type = IdType.AUTO)
    private Integer uuid;

    /**
     * 电影编号
     */
    private Integer filmId;

    /**
     * 电影名称
     */
    private String filmName;

    /**
     * 电影时长
     */
    private String filmLength;

    /**
     * 电影类型
     */
    private String filmCats;

    /**
     * 电影语言
     */
    private String filmLanguage;

    /**
     * 演员列表
     */
    private String actors;

    /**
     * 图片地址
     */
    private String imgAddress;

    @Override
    protected Serializable pkVal() {
        return null;
    }

    @Override
    public String toString() {
        return "HallFilmInfo{" +
        ", uuid=" + uuid +
        ", filmId=" + filmId +
        ", filmName=" + filmName +
        ", filmLength=" + filmLength +
        ", filmCats=" + filmCats +
        ", filmLanguage=" + filmLanguage +
        ", actors=" + actors +
        ", imgAddress=" + imgAddress +
        "}";
    }
}
