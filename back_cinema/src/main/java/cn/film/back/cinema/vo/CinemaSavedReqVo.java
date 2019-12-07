package cn.film.back.cinema.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @description: 电影员保存实体
 * @author: xinYue
 * @time: 2019/12/7 10:08
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CinemaSavedReqVo {

    private String brandId;
    private String areaId;
    private String hallTypeIds;
    private String cinemaName;
    private String cinemaAddress;
    private String cinemaTele;
    private String cinemaImgAddress;
    private String cinemaPrice;

}
