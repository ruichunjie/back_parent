package cn.film.back.common.mapper;

import cn.film.back.common.BackCommonApplicationTest;
import cn.film.back.utils.util.MD5Util;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.junit.Test;

import javax.annotation.Resource;
import java.util.List;

/**
 * description: 用户测试
 * create_time: 2019/12/4
 * writed by xinyue
 */
public class UserTest extends BackCommonApplicationTest {

    @Resource
    private BackendUserMapper backendUserMapper;

    @Test
    public void add(){
//        for(int i=0;i<5;i++){
//            BackendUser user = BackendUser.builder()
//                    .userName("admin"+i).userPwd("asd"+i).userPhone("18518982015").build();
//            backendUserMapper.insert(user);
//        }
        BackendUser user = BackendUser.builder()
                    .userName("admin").userPwd(MD5Util.encrypt("123456")).userPhone("18518982015").build();
        backendUserMapper.insert(user);

    }

    @Test
    public void select(){
//        BackendUser backendUser = backendUserMapper.selectById(2);
//        System.out.println("对象:"+backendUser);
//

        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.like("user_name","admin");
        List<BackendUser> list = backendUserMapper.selectList(queryWrapper);
        list.stream().forEach(System.out::println);

    }

    @Test
    public void selectByPage(){

        Page<BackendUser> page = new Page<>(1,3);

        QueryWrapper wrapper = new QueryWrapper();
        wrapper.like("user_name","admin");
        IPage<BackendUser> pageInfo= backendUserMapper.selectPage(page,wrapper);
        pageInfo.getRecords().stream().forEach(System.out::println);
    }

    @Test
    public void update(){
//        BackendUser user = BackendUser.builder()
//                .uuid(2).userPwd("123456")
//                .userName("admin").build();
//        backendUserMapper.updateById(user);

        QueryWrapper wrapper = new QueryWrapper();
        wrapper.eq("user_name","admin4");

        BackendUser user = BackendUser.builder()
                .userName("admin").userPwd("admin")
                .build();
        backendUserMapper.update(user,wrapper);

    }

    @Test
    public void del(){
        backendUserMapper.deleteById(2);
    }

}
