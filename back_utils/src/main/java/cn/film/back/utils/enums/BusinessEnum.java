package cn.film.back.utils.enums;

import cn.film.back.utils.interfaces.IBusinessEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * description: 枚举
 * create_time: 2019/12/5
 * writed by xinyue
 */
@AllArgsConstructor
@Getter
public enum BusinessEnum implements IBusinessEnum {

    SUCCESS(200,"执行成功!"),
    ERROR(500,"执行失败!"),

    /**参数错误*/
    PRECONDITION_FAILED(412,"参数错误!"),


    /**自定义异常*/
    COMMONNAME_FAILED(1001,"数据库中有多个同名后台用户!"),
    USERNAME_FAILED(1002,"用户名输入有误!"),
    PASSWORD_FAILED(1002,"用户密码输入有误!"),
    ;
    private Integer code;

    private String message;

}
