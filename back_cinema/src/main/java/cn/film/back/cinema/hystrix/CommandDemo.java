package cn.film.back.cinema.hystrix;

import com.netflix.hystrix.*;
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
                .withGroupKey(HystrixCommandGroupKey.Factory.asKey(name))
                .andThreadPoolKey(HystrixThreadPoolKey.Factory.asKey("command"))
                .andThreadPoolPropertiesDefaults(HystrixThreadPoolProperties.defaultSetter()
                        .withCoreSize(2)
                        .withMaximumSize(3)
                        .withMaxQueueSize(2)
                        .withAllowMaximumSizeToDivergeFromCoreSize(true))
                .andCommandPropertiesDefaults(HystrixCommandProperties.defaultSetter().withRequestCacheEnabled(false)
                .withExecutionIsolationStrategy(HystrixCommandProperties.ExecutionIsolationStrategy.SEMAPHORE)
                .withExecutionIsolationSemaphoreMaxConcurrentRequests(2)
                .withFallbackIsolationSemaphoreMaxConcurrentRequests(2)
              //  .withCircuitBreakerForceOpen(true)
                .withCircuitBreakerRequestVolumeThreshold(2)
                .withCircuitBreakerErrorThresholdPercentage(50)));
        this.name = name;
    }

    @Override
    protected String getCacheKey() {
        return String.valueOf(name);
    }

    /**
     * 单次请求调用业务
     * @return
     * @throws Exception
     */
    @Override
    protected String run() throws Exception {
        System.out.println("这是demo方法");
        if(name.startsWith("1")){
            int i =3/0;
        }
        Thread.sleep(300);
        System.out.println("当前线程:"+ Thread.currentThread().getName());
        return name;
    }

    @Override
    protected String getFallback() {
        String result = "fall back"+name;
        return result;
    }
}
