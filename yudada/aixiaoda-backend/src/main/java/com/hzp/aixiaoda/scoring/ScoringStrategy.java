package com.hzp.aixiaoda.scoring;

import com.hzp.aixiaoda.model.entity.UserAnswer;
import com.hzp.aixiaoda.model.entity.App;

import java.util.List;

/**
 * 评分策略
 *
 * @author <a href="https://github.com/liyupi">程序员鱼皮</a>
 * @from <a href="https://www.code-nav.cn">编程导航学习圈</a>
 */
public interface ScoringStrategy {

    /**
     * 执行评分
     *
     * @param choices
     * @param app
     * @return
     * @throws Exception
     */
    UserAnswer doScore(List<String> choices, App app) throws Exception;
}