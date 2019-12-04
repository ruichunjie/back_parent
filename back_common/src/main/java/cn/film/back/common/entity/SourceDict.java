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
 * 区域信息表
 * </p>
 *
 * @author xinyue
 * @since 2019-12-04
 */
@TableName("cn_source_dict")
@Data
@Builder
public class SourceDict extends Model<SourceDict> {

    private static final long serialVersionUID = 1L;

    /**
     * 主键编号
     */
    @TableId(value = "UUID", type = IdType.AUTO)
    private Integer uuid;

    /**
     * 显示名称
     */
    private String showName;

    @Override
    protected Serializable pkVal() {
        return null;
    }

    @Override
    public String toString() {
        return "SourceDict{" +
        ", uuid=" + uuid +
        ", showName=" + showName +
        "}";
    }
}
