package cn.film.back.cinema.hystrix;

import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;
import lombok.Data;

/**
 * @description: Command
 * @author: xinYue
 * @time: 2019/12/7 15:33
 */
@Data
public class CommandDemo extends HystrixCommand<String> {

    private String name;

    protected CommandDemo(String name) {
        super(Setter
                .withGroupKey(HystrixCommandGroupKey.Factory.asKey(name)));
        this.name = name;
    }

    /**
     * 单次请求调用业务
     * @return
     * @throws Exception
     */
    @Override
    protected String run() throws Exception {
        System.out.println("这是demo方法");
        Thread.sleep(500);
        System.out.println("当前线程:"+ Thread.currentThread().getName());
        return name;
    }
}
