package cn.film.back.hall.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @description:
 * @author: xinYue
 * @time: 2019/12/7 10:49
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class HallSavedReqVo {

    private String cinemaId;
    private String filmId;
    private String hallTypeId;
    private String beginTime;
    private String endTime;
    private String filmPrice;
    private String hallName;

}
