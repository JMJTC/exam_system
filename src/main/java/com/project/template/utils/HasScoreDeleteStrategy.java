package com.project.template.utils;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.project.template.entity.Score;
import com.project.template.service.ScoreService;

/**
 * @Author: jmjtc
 * @CreateTime: 2024-11-23
 * @Description: ,
 * @Version: 1.0
 */
public class HasScoreDeleteStrategy implements DeleteStrategy {
    private ScoreService scoreService;

    public HasScoreDeleteStrategy(ScoreService scoreService) {
        this.scoreService = scoreService;
    }

    @Override
    public boolean canDelete(Integer id) {
        return scoreService.count(new LambdaQueryWrapper<Score>().eq(Score::getExamId, id)) == 0;
    }
}
