package cn.film.back.hall.vo;

import lombok.Data;

/**
 * @description: 查询返回值
 * @author: xinYue
 * @time: 2019/12/7 10:32
 */
@Data
public class HallsRespVo {

    private String cinemaName;
    private String hallName;
    private String filmName;
    private String hallTypeName;
    private String beginTime;
    private String endTime;
    private String filmPrice;

}
