package cn.film.back.hall.entity;
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
 * 放映场次表
 * </p>
 *
 * @author xinyue
 * @since 2019-12-04
 */
@TableName("cn_field")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Field extends Model<Field> {

    private static final long serialVersionUID = 1L;

    /**
     * 主键编号
     */
    @TableId(value = "UUID", type = IdType.AUTO)
    private Integer uuid;

    /**
     * 影院编号
     */
    private Integer cinemaId;

    /**
     * 电影编号
     */
    private Integer filmId;

    /**
     * 开始时间
     */
    private String beginTime;

    /**
     * 结束时间
     */
    private String endTime;

    /**
     * 放映厅类型编号
     */
    private Integer hallId;

    /**
     * 放映厅名称
     */
    private String hallName;

    /**
     * 票价
     */
    private Integer price;

    @Override
    protected Serializable pkVal() {
        return null;
    }

    @Override
    public String toString() {
        return "Field{" +
        ", uuid=" + uuid +
        ", cinemaId=" + cinemaId +
        ", filmId=" + filmId +
        ", beginTime=" + beginTime +
        ", endTime=" + endTime +
        ", hallId=" + hallId +
        ", hallName=" + hallName +
        ", price=" + price +
        "}";
    }
}
