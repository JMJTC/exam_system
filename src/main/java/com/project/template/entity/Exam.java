package com.project.template.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.models.auth.In;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
 * @author jmjtc
 */
@Getter
@Setter
public class Exam extends BaseEntity{
    // 考试名称
    private String name;
    // 考试图片
    private String img;
    // 考试开始时间
    private Date startTime;
    // 考试结束时间
    private Date endTime;
    // 考试题目
    private String question;
    // 考试分数
    private Integer score;
    // 用户ID
    private Integer userId;
    // 是否考试
    @TableField(exist = false)//用于标识某个字段在数据库表中不存在。
    private String isExam;
    // 用户
    @TableField(exist = false)
    private String user;
}
