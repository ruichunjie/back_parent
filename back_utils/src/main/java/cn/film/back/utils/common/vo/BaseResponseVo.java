package cn.film.back.utils.common.vo;

import cn.film.back.utils.interfaces.IBusinessEnum;
import lombok.Data;

import static cn.film.back.utils.enums.BusinessEnum.SUCCESS;

/**
 * description:
 * create_time: 2019/12/5
 * writed by xinyue
 */
@Data
public class BaseResponseVo<T> {

    private Integer code;

    private String message;

    private T data;

    private BaseResponseVo(){}

    public static  BaseResponseVo success(){
        return business(SUCCESS);
    }

    public static  BaseResponseVo success(Object data){
        BaseResponseVo vo = business(SUCCESS);
        vo.setData(data);
        return vo;
    }

    public static BaseResponseVo business(IBusinessEnum businessEnum){
        BaseResponseVo vo = new BaseResponseVo();
        vo.setCode(businessEnum.getCode());
        vo.setMessage(businessEnum.getMessage());
        return vo;
    }

    public static BaseResponseVo fail(Integer code, String message){
        BaseResponseVo vo = new BaseResponseVo();
        vo.setCode(code);
        vo.setMessage(message);
        return vo;
    }

}
