package com.project.template.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.project.template.dto.SysUserDTO;
import com.project.template.entity.ExamQuestion;
import com.project.template.enums.RoleType;
import com.project.template.mapper.ExamQuestionMapper;
import com.project.template.service.ExamQuestionService;
import com.project.template.utils.UserThreadLocal;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 *
 * @author Jerry
 * @date 2024/9/8
 */

@Service
public class ExamQuestionServiceImpl extends ServiceImpl<ExamQuestionMapper, ExamQuestion> implements ExamQuestionService{

    // 重写page方法，根据传入的参数进行分页查询
    @Override
    public Page<ExamQuestion> page(Map<String, Object> query, Integer pageNum, Integer pageSize) {
        // 创建LambdaQueryWrapper对象，用于构建查询条件
        LambdaQueryWrapper<ExamQuestion> wrapper = new LambdaQueryWrapper<>();
        // 按照id降序排列
        wrapper.orderByDesc(ExamQuestion::getId);
        System.out.println(query.get("name"));

        //老师只能看到自己的试题
        SysUserDTO currentUser = UserThreadLocal.getCurrentUser();
        if(RoleType.TEACHER.getKey().equals(currentUser.getRoleType())){
            wrapper.eq(ExamQuestion::getUserId,currentUser.getId());
        }

        //条件模糊查询
        if(query.get("name") != null){
            wrapper.like(ExamQuestion::getName,query.get("name"));
        }
        // 调用父类的page方法，传入分页参数和查询条件，返回分页结果
        return page(new Page<>(pageNum, pageSize), wrapper);
    }
}
