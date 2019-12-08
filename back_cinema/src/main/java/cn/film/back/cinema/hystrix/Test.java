package cn.film.back.cinema.hystrix;

import com.netflix.hystrix.strategy.concurrency.HystrixRequestContext;
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
        testRd();
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

    public static void testToObserve() throws InterruptedException {
        Instant begin = Instant.now();
        CommandDemo commandDemo = new CommandDemo("observe");

        Observable<String> toObserve = commandDemo.toObservable();
        //阻塞式调用执行
        String result = toObserve.toBlocking().single();
        Instant end = Instant.now();
        System.out.println("result="+result+"时间:"+ Duration.between(begin,end).toMillis());
        //非阻塞式调用 是个纯后台的调用 前面必须有耗时的时间
        //如果用同一个commandDemo会报错 com.netflix.hystrix.exception.HystrixRuntimeException: CommandDemo command executed multiple times - this is not permitted.
        //用不同的commandDemo会只显示阻塞式调用 要显示非阻塞调用 在后面调用Thread.sleep()方法即可
        CommandDemo commandDemo1 = new CommandDemo("observe1");
        Observable<String> toObserve1 = commandDemo1.toObservable();
        toObserve1.subscribe(new Subscriber<String>() {
            @Override
            public void onCompleted() {
                System.out.println("on completed");
            }

            @Override
            public void onError(Throwable throwable) {
                System.err.println("on error"+throwable);
            }

            @Override
            public void onNext(String result) {
                System.out.println("on next, result="+result);
            }
        });
        Thread.sleep(3000);
    }

    public static void testRequestCache(){
        HystrixRequestContext context = HystrixRequestContext.initializeContext();
        Instant begin = Instant.now();
        CommandDemo commandDemo = new CommandDemo("command");
        CommandDemo commandDemo1 = new CommandDemo("command1");
        CommandDemo commandDemo2 = new CommandDemo("command");
        //同步执行
        String result = commandDemo.execute();
        String result1 = commandDemo1.execute();
        String result2 = commandDemo2.execute();
        Instant end = Instant.now();
        System.out.println("result="+result+"时间:"+ Duration.between(begin,end).toMillis());
        System.out.println("result="+result1+"时间:"+ Duration.between(begin,end).toMillis());
        System.out.println("result="+result2+"时间:"+ Duration.between(begin,end).toMillis());
        context.close();
    }

    public static void testThread() throws Exception {
        CommandDemo commandDemo1 = new CommandDemo("1");
        CommandDemo commandDemo2 = new CommandDemo("2");
        CommandDemo commandDemo3 = new CommandDemo("3");
        CommandDemo commandDemo4 = new CommandDemo("4");
        CommandDemo commandDemo5 = new CommandDemo("5");
        //异步执行
        Future<String> result1 = commandDemo1.queue();
        Future<String> result2 = commandDemo2.queue();
        Future<String> result3 = commandDemo3.queue();
        Future<String> result4 = commandDemo4.queue();
        Future<String> result5 = commandDemo5.queue();

        String r1 = result1.get();
        String r2 = result2.get();
        String r3 = result3.get();
        String r4 = result4.get();
        String r5 = result5.get();

        System.out.println(r1+r2+r3+r4+r5);

    }

    public static void testSemaphore() throws Exception {
        MyThread t1 = new MyThread("t1");
        MyThread t2 = new MyThread("t2");
        MyThread t3 = new MyThread("t3");
        MyThread t4 = new MyThread("t4");
        MyThread t5 = new MyThread("t5");

        t1.start();
        t2.start();
        t3.start();
        t4.start();
        t5.start();
        Thread.sleep(10000);
    }

    public static void testRd(){
        CommandDemo c1 = new CommandDemo("1");
        CommandDemo c2 = new CommandDemo("2");
        System.out.println(c1.execute());
        System.out.println(c2.execute());
    }
}

class MyThread extends Thread{
    private String name;

    public MyThread(String name){
        this.name =name;
    }

    @Override
    public void run() {
        CommandDemo commandDemo = new CommandDemo(name);
        System.out.println("commandDemo,result="+commandDemo.execute());
    }
}