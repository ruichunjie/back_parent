package cn.film.back.cinema.hystrix;

import com.netflix.hystrix.strategy.concurrency.HystrixRequestContext;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

/**
 * description:
 * create_time: 2019/12/8
 * writed by xinyue
 */
public class CollapserTest {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        HystrixRequestContext context = HystrixRequestContext.initializeContext();

        CommandCollapser c1 = new CommandCollapser(1);
        CommandCollapser c2 = new CommandCollapser(2);
        CommandCollapser c3 = new CommandCollapser(3);
        CommandCollapser c4 = new CommandCollapser(4);

        //默认足够的近 10ms
        Future<String> q1 = c1.queue();
        Thread.sleep(500);
        Future<String> q2 = c2.queue();
        Future<String> q3 = c3.queue();
        Future<String> q4 = c4.queue();

        String r1 = q1.get();
        String r2 = q2.get();
        String r3 = q3.get();
        String r4 = q4.get();

        System.out.println(r1+r2+r3+r4);
        context.close();
    }

}
