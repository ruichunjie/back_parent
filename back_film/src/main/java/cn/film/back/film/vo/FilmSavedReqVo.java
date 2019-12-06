package cn.film.back.film.vo;

import lombok.Data;

/**
 * @description: 保存电影信息对象
 * @author: xinYue
 * @time: 2019/12/6 14:28
 */
@Data
public class FilmSavedReqVo {

    private String filmStatus;
    private String filmName;
    private String filmEnName;
    private String mainImgAddress;
    private String filmScore;
    private String filmScorers;
    private String preSaleNum;
    private String boxOffice;
    private String filmTypeId;
    private String filmSourceId;
    private String filmCatIds;
    private String areaId;
    private String dateId;
    private String filmTime;
    private String directorId;
    private String actIds;
    private String roleNames;
    private String filmLength;
    private String biography;
    private String filmImgs;

}
