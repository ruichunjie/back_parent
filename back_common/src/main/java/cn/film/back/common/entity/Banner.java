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
 * banner信息表
 * </p>
 *
 * @author xinyue
 * @since 2019-12-04
 */
@TableName("cn_banner")
@Data
@Builder
public class Banner extends Model<Banner> {

    private static final long serialVersionUID = 1L;

    /**
     * 主键编号
     */
    @TableId(value = "UUID", type = IdType.AUTO)
    private Integer uuid;

    /**
     * banner图存放路径
     */
    private String bannerAddress;

    /**
     * banner点击跳转url
     */
    private String bannerUrl;

    /**
     * 是否弃用 0-失效,1-失效
     */
    private Integer isValid;

    @Override
    protected Serializable pkVal() {
        return null;
    }

    @Override
    public String toString() {
        return "Banner{" +
        ", uuid=" + uuid +
        ", bannerAddress=" + bannerAddress +
        ", bannerUrl=" + bannerUrl +
        ", isValid=" + isValid +
        "}";
    }
}
