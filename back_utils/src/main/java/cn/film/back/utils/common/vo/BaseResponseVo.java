package cn.film.back.utils.common.vo;

import cn.film.back.utils.interfaces.IBusinessEnum;
import lombok.Builder;
import lombok.Data;

import static cn.film.back.utils.enums.BusinessEnum.SUCCESS;

/**
 * description:
 * create_time: 2019/12/5
 * writed by xinyue
 */
@Data
@Builder
public class BaseResponseVo<T> {

    private Integer code;

    private String message;

    private T data;

    public BaseResponseVo success(){
        return business(SUCCESS);
    }

    public BaseResponseVo business(IBusinessEnum businessEnum){
        BaseResponseVo vo = BaseResponseVo.builder()
                .code(businessEnum.getCode())
                .message(businessEnum.getMessage())
                .build();
        return vo;
    }

}
