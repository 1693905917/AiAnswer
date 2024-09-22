package com.hzp.aixiaoda.scoring;

import org.springframework.stereotype.Component;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
//标识注解可以使用的范围，例如使用在方法、字段、构造方法上。ElementType.TYPE：允许被修饰的注解作用在：类、接口、枚举上；
@Target(ElementType.TYPE)
//标识注解的生命周期 RetentionPolicy.RUNTIME：始终不会丢弃，程序运行期也保留此注解，自定义注解通常使用这种方式，因此可以通过反射获取到注解配置的属性值。
@Retention(RetentionPolicy.RUNTIME)
@Component
public @interface ScoringStrategyConfig {

    /**
     * 应用类型
     * @return
     */
    int appType();

    /**
     * 评分策略
     * @return
     */
    int scoringStrategy();
}