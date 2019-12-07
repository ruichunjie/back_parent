package cn.film.back.film.entity;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
/**
 * <p>
 * 影片与演员映射表
 * </p>
 *
 * @author xinyue
 * @since 2019-12-04
 */
@TableName("cn_film_actor")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class FilmActor extends Model<FilmActor> {

    private static final long serialVersionUID = 1L;

    /**
     * 主键编号
     */
    @TableId(value = "UUID", type = IdType.AUTO)
    private Integer uuid;

    /**
     * 影片编号,对应mooc_film_t
     */
    private Integer filmId;

    /**
     * 演员编号,对应mooc_actor_t
     */
    private Integer actorId;

    /**
     * 角色名称
     */
    private String roleName;

    @Override
    protected Serializable pkVal() {
        return null;
    }

    @Override
    public String toString() {
        return "FilmActor{" +
        ", uuid=" + uuid +
        ", filmId=" + filmId +
        ", actorId=" + actorId +
        ", roleName=" + roleName +
        "}";
    }
}
