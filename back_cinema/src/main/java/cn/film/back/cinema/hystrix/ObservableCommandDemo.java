package cn.film.back.cinema.hystrix;

import com.netflix.hystrix.HystrixCommandGroupKey;
import com.netflix.hystrix.HystrixObservableCommand;
import lombok.Data;
import rx.Observable;
import rx.Subscriber;
import rx.schedulers.Schedulers;

/**
 * description:
 * create_time: 2019/12/8
 * writed by xinyue
 */
@Data
public class ObservableCommandDemo extends HystrixObservableCommand<String> {

    private String name;

    protected ObservableCommandDemo(String  name) {
        super(Setter.withGroupKey(HystrixCommandGroupKey.Factory.asKey("ObserveCommandDemo")));
        this.name =name;
    }

    @Override
    protected Observable<String> construct() {
        System.out.println("当前线程:"+Thread.currentThread().getName());

        return Observable.create(new Observable.OnSubscribe<String>(){

            @Override
            public void call(Subscriber<? super String> subscriber) {
                //业务处理 可以写很多onNext 用于批量处理
                subscriber.onNext("");
                subscriber.onCompleted();
            }
        }).subscribeOn(Schedulers.io());
    }
}
