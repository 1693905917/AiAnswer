package com.hzp.aixiaoda;

import io.reactivex.Flowable;
import io.reactivex.schedulers.Schedulers;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.concurrent.TimeUnit;

/**
 * @BelongsProject: aixiaoda-backend
 * @BelongsPackage: com.hzp.aixiaoda
 * @Author: ASUS
 * @CreateTime: 2024-09-01  16:07
 * @Description: RxJava测试
 * @Version: 1.0
 */
@SpringBootTest
public class RxJavaTest {

    @Test
    public void test() throws InterruptedException {
        //创建数据流
        Flowable<Long> flowable = Flowable.interval(1, TimeUnit.SECONDS)//每个1s发送一个数据
                .map(i -> i + 1)//map:对数据进行处理，也就是执行对应的逻辑操作
                .subscribeOn(Schedulers.io());//subscribeOn:指定 subscribe() 发生在 IO 线程 指定执行操作用的线程池

        //订阅 Flowable流,也就是观察者模式,并且打印出每个接收到数字
        flowable.observeOn(Schedulers.io())//observeOn:指定 Subscriber 的线程,即上面操作的结果
                .doOnNext(i -> System.out.println("接收到的数据:" + i))//doOnNext:对数据进行处理，也就是执行对应的逻辑操作
                .subscribe();//subscribe:订阅
        //主线程睡眠，以便观察到结果
        Thread.sleep(10000L);
    }
}
