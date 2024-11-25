package com.project.template.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.project.template.entity.Exam;

/**
 * @Author: jmjtc
 * @CreateTime: 2024-11-24
 * @Description: Decorator
 * @Version: 1.0
 */

public abstract class ExamServiceDecorator implements ExamService {
    private static final Logger logger = LoggerFactory.getLogger(ExamServiceDecorator.class);
    private final ExamService examService;
    public ExamServiceDecorator(ExamService examService) {
        this.examService = examService;
    }
    public void submitExam(Exam exam) {
        //添加日志记录
        logger.info("提交考试信息前，记录日志");
        // 调用被装饰的ExamService的save方法保存考试信息
        examService.save(exam);
    }
}

