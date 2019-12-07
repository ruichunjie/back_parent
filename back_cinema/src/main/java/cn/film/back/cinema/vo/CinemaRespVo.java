package cn.film.back.cinema.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @description:
 * @author: xinYue
 * @time: 2019/12/7 9:48
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CinemaRespVo {

    private String brandId;
    private String areaId;
    private String hallTypeIds;
    private String cinemaName;
    private String cinemaAddress;
    private String cinemaTele;
    private String cinemaImgAddress;
    private String cinemaPrice;
}
