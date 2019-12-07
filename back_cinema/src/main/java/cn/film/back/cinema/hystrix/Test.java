package cn.film.back.cinema.hystrix;

import rx.Observable;
import rx.Subscriber;

import java.time.Duration;
import java.time.Instant;
import java.util.concurrent.Future;

/**
 * @description:
 * @author: xinYue
 * @time: 2019/12/7 15:43
 */
public class Test {

    public static void main(String[] args) throws Exception {
        testObserve();
    }

    public static void testExecute(){
        Instant begin = Instant.now();
        CommandDemo commandDemo = new CommandDemo("command");
        //同步执行
        String result = commandDemo.execute();
        Instant end = Instant.now();
        System.out.println("result="+result+"时间:"+ Duration.between(begin,end).toMillis());
    }

    public static void testQueue() throws Exception {
        Instant begin = Instant.now();
        CommandDemo commandDemo = new CommandDemo("queue");
        //异步执行
        Future<String> result = commandDemo.queue();
        Instant end = Instant.now();
        System.out.println("result0="+result+"时间:"+ Duration.between(begin,end).toMillis());
        Instant endGet = Instant.now();
        System.out.println("result="+result.get()+"时间:"+ Duration.between(begin,endGet).toMillis());
    }

    public static void testObserve(){
        Instant begin = Instant.now();
        CommandDemo commandDemo = new CommandDemo("observe");

        Observable<String> observe = commandDemo.observe();
        //阻塞式调用执行
        String result = observe.toBlocking().single();
        Instant end = Instant.now();
        System.out.println("result="+result+"时间:"+ Duration.between(begin,end).toMillis());
        //非阻塞式调用 是个纯后台的调用 前面必须有耗时的时间
        observe.subscribe(new Subscriber<String>() {
            @Override
            public void onCompleted() {
                System.out.println("on completed");
            }

            @Override
            public void onError(Throwable throwable) {
                System.err.println("on error");
            }

            @Override
            public void onNext(String result) {
                System.out.println("on next, result="+result);
            }
        });
    }

    public static void testToObserve(){
        Instant begin = Instant.now();
        CommandDemo commandDemo = new CommandDemo("observe");

        Observable<String> toObserve = commandDemo.toObservable();
        //阻塞式调用执行
        String result = toObserve.toBlocking().single();
        Instant end = Instant.now();
        System.out.println("result="+result+"时间:"+ Duration.between(begin,end).toMillis());
        //非阻塞式调用 是个纯后台的调用 前面必须有耗时的时间
        toObserve.subscribe(new Subscriber<String>() {
            @Override
            public void onCompleted() {
                System.out.println("on completed");
            }

            @Override
            public void onError(Throwable throwable) {
                System.err.println("on error");
            }

            @Override
            public void onNext(String result) {
                System.out.println("on next, result="+result);
            }
        });
    }

}
