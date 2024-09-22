package com.project.template.entity;


import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author Jerry
 * @date 2024/9/10
 */

@Getter
@Setter
public class Score extends BaseEntity {
    private Integer userId;

    private Integer teacherId;

    private Integer examId;

    private Integer score;

    private String userAnswers;

    private String name;

    private String ids;

    private String isScore;

    @TableField(exist = false)
    private String user;
}
