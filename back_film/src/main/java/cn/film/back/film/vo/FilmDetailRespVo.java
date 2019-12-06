package cn.film.back.film.vo;

import lombok.Data;

/**
 * @description: 根据主键获取的影片信息
 * @author: xinYue
 * @time: 2019/12/6 13:44
 */
@Data
public class FilmDetailRespVo {
    private String filmId;
    private String filmName;
    private String filmLength;
    private String filmCats;
    private String actors;
    private String imgAddress;
    private String subAddress;
}
