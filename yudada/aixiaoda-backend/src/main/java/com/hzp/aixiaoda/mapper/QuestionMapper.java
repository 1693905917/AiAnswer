package com.hzp.aixiaoda.mapper;

import com.hzp.aixiaoda.model.entity.Question;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
* @author 李鱼皮
* @description 针对表【question(题目)】的数据库操作Mapper
* @createDate 2024-05-09 20:41:03
* @Entity com.hzp.aixiaoda.model.entity.Question
*/

@Mapper
public interface QuestionMapper extends BaseMapper<Question> {

}




