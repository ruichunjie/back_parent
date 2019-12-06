package cn.film.back.common.util;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.google.common.collect.Maps;

import java.util.Map;

/**
 * @description: 分页组件
 * @author: xinYue
 * @time: 2019/12/6 11:23
 */
public class PageUtil {

    private PageUtil(){
        throw new RuntimeException("PageUtil工具类不允许实例化!");
    }

    /**
     *  对分页进行封装
     * @param page
     * @param title
     * @return
     */
    public static Map<String,Object> pageResult(IPage page, String title){
        Map<String,Object> result = Maps.newHashMap();

        result.put("totalSize",page.getTotal());
        result.put("totalPages",page.getPages());
        result.put("pageSize",page.getSize());
        result.put("nowPage",page.getCurrent());
        result.put(title, page.getRecords());

        return result;
    }
}
