package cn.film.back.utils.exception;

import cn.film.back.utils.interfaces.IBusinessExceptionEnum;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @description: 业务异常
 * @author: xinYue
 * @time: 2019/12/2 15:28
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class BusinessException extends RuntimeException implements IBusinessExceptionEnum {

    /**
     * 编码
     */
    private Integer code;

    /**
     * 描述
     */
    private String msg;

    public BusinessException(String message) {
        super(message);
        this.msg = message;
    }

    public BusinessException(Integer code, String msg) {
        super(msg);
        this.msg = msg;
        this.code = code;
    }

    public BusinessException(IBusinessExceptionEnum exceptionEnum) {
        super(exceptionEnum.getMsg());
        this.msg = exceptionEnum.getMsg();
        this.code = exceptionEnum.getCode();
    }

}
