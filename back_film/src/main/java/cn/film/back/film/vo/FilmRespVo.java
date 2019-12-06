package cn.film.back.film.vo;

import lombok.Data;

/**
 * @description: 所有的影片
 * @author: xinYue
 * @time: 2019/12/6 12:37
 */
@Data
public class FilmRespVo {
    private String filmId;
    private String filmStatus;
    private String filmName;
    private String filmEnName;
    private String filmScore;
    private String preSaleNum;
    private String boxOffice;
    private String filmTime;
    private String filmLength;
    private String mainImg;
}
