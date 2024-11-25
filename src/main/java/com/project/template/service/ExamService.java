package com.project.template.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.project.template.common.Result;
import com.project.template.entity.Banner;
import com.project.template.entity.Exam;

import java.util.List;
import java.util.Map;

/**
 *
 * @author Jerry
 * @date 2024/9/9
 */

public interface ExamService extends IService<Exam> {

    // 分页查询Banner
    Page<Exam> page(Map<String, Object> query, Integer pageNum, Integer pageSize);
}



