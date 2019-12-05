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
    ;

    private Integer code;

    private String message;

}
