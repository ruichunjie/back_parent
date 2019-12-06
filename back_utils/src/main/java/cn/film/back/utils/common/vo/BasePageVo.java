package cn.film.back.utils.common.vo;

import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

/**
 * @description: 分页
 * @author: xinYue
 * @time: 2019/12/6 11:30
 */
@Data
public class BasePageVo {

    @NotNull(message = "当前页数不可为空")
    @Min(value = 1, message = "当前页数最少为1")
    private Integer pageNum =1;

    @NotNull(message = "每页数目不可为空")
    @Min(value = 1, message = "每页数目最少为1")
    private Integer pageSize =1;

}
