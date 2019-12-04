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
 * 演员表
 * </p>
 *
 * @author xinyue
 * @since 2019-12-04
 */
@TableName("cn_actor")
@Data
@Builder
public class Actor extends Model<Actor> {

    private static final long serialVersionUID = 1L;

    /**
     * 主键编号
     */
    @TableId(value = "UUID", type = IdType.AUTO)
    private Integer uuid;

    /**
     * 演员名称
     */
    private String actorName;

    /**
     * 演员图片位置
     */
    private String actorImg;

    @Override
    protected Serializable pkVal() {
        return null;
    }

    @Override
    public String toString() {
        return "Actor{" +
        ", uuid=" + uuid +
        ", actorName=" + actorName +
        ", actorImg=" + actorImg +
        "}";
    }
}
