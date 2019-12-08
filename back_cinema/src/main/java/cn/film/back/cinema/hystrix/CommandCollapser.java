package cn.film.back.cinema.hystrix;

import com.netflix.hystrix.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

/**
 * description:
 * create_time: 2019/12/8
 * writed by xinyue
 */
public class CommandCollapser extends HystrixCollapser<List<String>,String,Integer> {

    private Integer id;

    public CommandCollapser(Integer id){
        super(Setter
                .withCollapserKey(HystrixCollapserKey.Factory.asKey("abc"))
                .andCollapserPropertiesDefaults(HystrixCollapserProperties.defaultSetter()
                .withTimerDelayInMilliseconds(1000)));
        this.id = id;
    }

    @Override
    public Integer getRequestArgument() {
        return id;
    }

    @Override
    protected HystrixCommand<List<String>> createCommand(Collection<CollapsedRequest<String, Integer>> collection) {
        return new BatchCommand(collection);
    }

    @Override
    protected void mapResponseToRequests(List<String> strings, Collection<CollapsedRequest<String, Integer>> collection) {
        int count =0;
        Iterator<HystrixCollapser.CollapsedRequest<String, Integer>> iterator = collection.iterator();
        while(iterator.hasNext()){
            HystrixCollapser.CollapsedRequest<String, Integer> response = iterator.next();
            String result = strings.get(count++);
            response.setResponse(result);
        }
    }


}

class BatchCommand extends HystrixCommand<List<String>>{

    private Collection<HystrixCollapser.CollapsedRequest<String, Integer>> collection;

    protected BatchCommand(Collection<HystrixCollapser.CollapsedRequest<String, Integer>> collection) {
        super(Setter.withGroupKey(HystrixCommandGroupKey.Factory.asKey("ads")));
        this.collection = collection;
    }

    @Override
    protected List<String> run() throws Exception {
        System.out.println("current thread"+Thread.currentThread().getName());
        List<String> result = new ArrayList<>();
        Iterator<HystrixCollapser.CollapsedRequest<String, Integer>> iterator = collection.iterator();
        while(iterator.hasNext()){
            HystrixCollapser.CollapsedRequest<String, Integer> request = iterator.next();
            Integer argument = request.getArgument();
            result.add("返回"+argument.toString());
        }
        return result;
    }
}
