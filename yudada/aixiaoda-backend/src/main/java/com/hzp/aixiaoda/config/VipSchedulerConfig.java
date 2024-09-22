package com.hzp.aixiaoda.config;

import io.reactivex.Scheduler;
import io.reactivex.schedulers.Schedulers;
import lombok.Data;
import org.jetbrains.annotations.NotNull;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @BelongsProject: aixiaoda-backend
 * @BelongsPackage: com.hzp.aixiaoda.config
 * @Author: ASUS
 * @CreateTime: 2024-09-03  08:46
 * @Description: Vip用户的线程池配置
 * @Version: 1.0
 */
@Configuration
@Data
public class VipSchedulerConfig {


    @Bean()
    public Scheduler vipScheduler(){
        //创建线程工厂
        ThreadFactory threadFactory = new ThreadFactory() {
            //创建并发的原子整数
           final AtomicInteger threadNumber = new AtomicInteger(1);
            @Override
            public Thread newThread(@NotNull Runnable r) {
                //自定义线程名称
                Thread thread = new Thread(r,"VIPThreadPool-"+threadNumber.getAndIncrement());
                thread.setDaemon(false);//非守护线程
                return thread;
            }
        };
        //创建线程池 第一个参数10:10个核心线程数  第二个参数是自定义创建线程的逻辑
        ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(10, threadFactory);
        return Schedulers.from(scheduledExecutorService);
    }


}
