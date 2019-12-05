package cn.film.back.utils.interfaces;

/**
 * @description: 业务异常
 * @author: xinYue
 * @time: 2019/12/2 15:36
 */
public interface IBusinessExceptionEnum {

    /**
     * 获取错误编码
     *
     * @return
     */
    Integer getCode();

    /**
     * 错误描述
     *
     * @return
     */
    String getMsg();

}
