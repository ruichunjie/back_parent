package cn.film.back.user.controller;

import cn.film.back.user.service.UserService;
import cn.film.back.user.vo.UserVo;
import cn.film.back.utils.common.vo.BaseResponseVo;
import cn.film.back.utils.util.JwtTokenUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

/**
 * description: 用户服务
 * create_time: 2019/12/5
 * writed by xinyue
 */
@RestController
@RequestMapping(value = "/user")
@Slf4j
@Api(value = "用户服务")
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * 登陆
     * @param userVo
     * @return
     */
    @PostMapping(value = "/login")
    @ApiOperation(value = "登陆", notes = "登陆")
    public BaseResponseVo login(@ApiParam(value = "用户信息")@Valid UserVo userVo){

        String uuid = userService.checkUserLogin(userVo.getUsername(),userVo.getPassword());

        JwtTokenUtil jwtTokenUtil = new JwtTokenUtil();
        String randomKey =jwtTokenUtil.getRandomKey();
        String token = jwtTokenUtil.generateToken(uuid,randomKey);

        Map<String, String> result = new HashMap<>();
        result.put("randomKey",randomKey);
        result.put("token",token);
        return BaseResponseVo.success(result);
    }
}
