package com.project.template.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.project.template.entity.Banner;
import com.project.template.entity.ExamQuestion;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author jmjtc
 */
@Mapper
// 定义一个接口，继承BaseMapper，用于操作Banner实体类
public interface ExamQuestionMapper extends BaseMapper <ExamQuestion>{

}
