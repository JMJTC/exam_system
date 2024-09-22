package com.project.template.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.project.template.entity.Banner;
import com.project.template.entity.ExamQuestion;

import java.util.Map;

public interface ExamQuestionService extends IService<ExamQuestion> {

    // 分页查询Banner
    Page<ExamQuestion> page(Map<String, Object> query, Integer pageNum, Integer pageSize);
}
