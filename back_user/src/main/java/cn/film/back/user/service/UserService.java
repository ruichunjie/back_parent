package cn.film.back.user.service;

import cn.film.back.user.entity.BackendUser;
import cn.film.back.user.mapper.BackendUserMapper;
import cn.film.back.utils.exception.BusinessException;
import cn.film.back.utils.interfaces.SysLog;
import cn.film.back.utils.util.MD5Util;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;

import static cn.film.back.utils.enums.BusinessEnum.*;

/**
 * description: 用户服务
 * create_time: 2019/12/5
 * writed by xinyue
 */
@Service
public class UserService {

    @Autowired
    private BackendUserMapper backendUserMapper;

    /**
     * 验证登陆
     * @param username
     * @param password
     * @return
     */
    @SysLog
    public String checkUserLogin(String username,String password){

        //查询数据
        QueryWrapper wrapper = new QueryWrapper();
        wrapper.eq("user_name",username);
        List<BackendUser> list = backendUserMapper.selectList(wrapper);
        BackendUser backendUser = null;
        if(!CollectionUtils.isEmpty(list)){
            if(list.size() != 1){
                throw new BusinessException(COMMONNAME_FAILED);
            }
            backendUser = list.stream().findFirst().get();
        }else{
            throw new BusinessException(USERNAME_FAILED);
        }

        //验证密码
        String encryt = MD5Util.encrypt(password);
        if(!encryt.equals(backendUser.getUserPwd())){
            throw new BusinessException(PASSWORD_FAILED);
        }
        return backendUser.getUuid().toString();

    }


}
