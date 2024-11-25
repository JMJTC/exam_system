package com.project.template.utils;

/**
 * @Author: jmjtc
 * @CreateTime: 2024-11-23
 * @Description: ,
 * @Version: 1.0
 */
public class NoScoreDeleteStrategy implements DeleteStrategy {
    @Override
    public boolean canDelete(Integer id) {
        return true;
    }
}
