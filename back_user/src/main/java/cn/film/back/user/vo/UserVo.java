package cn.film.back.user.vo;

import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;

/**
 * description: 登陆请求
 * create_time: 2019/12/5
 * writed by xinyue
 */
@Data
public class UserVo {

    @Valid
    @NotBlank(message = "用户名不能为空")
    private String username;

    @Valid
    @NotBlank(message = "密码不能为空")
    private String password;
}
