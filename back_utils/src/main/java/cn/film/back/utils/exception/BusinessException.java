package cn.film.back.utils.exception;

import cn.film.back.utils.interfaces.IBusinessEnum;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @description: 业务异常
 * @author: xinYue
 * @time: 2019/12/2 15:28
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class BusinessException extends RuntimeException implements IBusinessEnum {

    /**
     * 编码
     */
    private Integer code;

    /**
     * 描述
     */
    private String message;

    public BusinessException(String message) {
        super(message);
        this.message = message;
    }

    public BusinessException(Integer code, String msg) {
        super(msg);
        this.message = msg;
        this.code = code;
    }

    public BusinessException(IBusinessEnum businessEnum) {
        super(businessEnum.getMessage());
        this.message = businessEnum.getMessage();
        this.code = businessEnum.getCode();
    }

}
