package cn.film.back.hall.ribbon;

import com.netflix.loadbalancer.IPing;
import com.netflix.loadbalancer.Server;

/**
 * @description:
 * @author: xinYue
 * @time: 2019/12/7 13:54
 */
public class MyIPing implements IPing {

    @Override
    public boolean isAlive(Server server) {
        return false;
    }
}
