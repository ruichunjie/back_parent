package cn.film.back.cinema.hystrix;

import rx.Observable;
import rx.Subscriber;

import java.time.Duration;
import java.time.Instant;
import java.util.concurrent.Future;

/**
 * description:
 * create_time: 2019/12/8
 * writed by xinyue
 */
public class ObservableCommandTest {

    public static void main(String[] args) throws Exception {
        testObserve();
    }

    public static void testObserve() throws InterruptedException {
        Instant begin = Instant.now();
        ObservableCommandDemo commandDemo = new ObservableCommandDemo("observe");

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
                System.err.println("on error,"+throwable);
            }

            @Override
            public void onNext(String result) {
                System.out.println("on next, result="+result);
            }
        });
        Thread.sleep(3000);
    }

    public static void testToObserve() throws InterruptedException {
        Instant begin = Instant.now();
        ObservableCommandDemo observableCommandDemo = new ObservableCommandDemo("observe");

        Observable<String> toObserve = observableCommandDemo.toObservable();
        //阻塞式调用执行
        String result = toObserve.toBlocking().single();
        Instant end = Instant.now();
        System.out.println("result="+result+"时间:"+ Duration.between(begin,end).toMillis());
        //非阻塞式调用 是个纯后台的调用 前面必须有耗时的时间
        //如果用同一个commandDemo会报错 com.netflix.hystrix.exception.HystrixRuntimeException: CommandDemo command executed multiple times - this is not permitted.
        //用不同的commandDemo会只显示阻塞式调用 要显示非阻塞调用 在后面调用Thread.sleep()方法即可
        ObservableCommandDemo commandDemo1 = new ObservableCommandDemo("observe1");
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

}
