package com.project.template.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.project.template.dto.SysUserDTO;
import com.project.template.entity.Exam;
import com.project.template.entity.ExamQuestion;
import com.project.template.entity.Score;
import com.project.template.entity.SysUser;
import com.project.template.enums.RoleType;
import com.project.template.mapper.ExamMapper;
import com.project.template.service.ExamService;
import com.project.template.service.ScoreService;
import com.project.template.service.SysUserService;
import com.project.template.utils.UserThreadLocal;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


/**
 * @author Jerry
 * @date 2024/9/9
 */

@Service
public class ExamServiceImpl extends ServiceImpl<ExamMapper, Exam> implements ExamService {
    @Resource
    private SysUserService userService;
    @Resource
    private ScoreService scoreService;

    // 重写page方法，根据传入的参数进行分页查询
    @Override
    public Page<Exam> page(Map<String, Object> query, Integer pageNum, Integer pageSize) {
        LambdaQueryWrapper<Exam> wrapper = new LambdaQueryWrapper<>();
        wrapper.orderByDesc(Exam::getId);
        //条件模糊查询
        if (query.get("name") != null) {
            wrapper.like(Exam::getName, query.get("name"));
        }

        SysUserDTO currentUser = UserThreadLocal.getCurrentUser();
        //每个老师只能看到自己发布的试卷
        if (RoleType.TEACHER.getKey().equals(currentUser.getRoleType())) {
            wrapper.eq(Exam::getUserId, currentUser.getId());
        }
        Page<Exam> page = page(new Page<>(pageNum, pageSize), wrapper);
        List<Exam> list = page.getRecords();
        //判断用户有没有参考考试
        //通过开始结果去查
        list.forEach(item -> {
            SysUser user = userService.getById(item.getUserId());
            if (user != null) {
                item.setUser(user.getUsername());
            }
            // LambdaQueryWrapper<Object> result = new LambdaQueryWrapper<>();

            // 创建LambdaQueryWrapper对象
            LambdaQueryWrapper<Score> scoreLambdaQueryWrapper = new LambdaQueryWrapper<>();
            // 设置查询条件，用户ID等于当前用户的ID，考试ID等于item的ID
            scoreLambdaQueryWrapper.eq(Score::getUserId, currentUser.getId())
                    .eq(Score::getExamId, item.getId())
                    // 设置查询结果只返回一条记录
                    .last("limit 1");
            // 根据查询条件从scoreService中获取一条记录
            Score one = scoreService.getOne(scoreLambdaQueryWrapper);
            // 如果查询结果不为空，则设置item的isExam属性为"已考试"
            if (one != null) {
                item.setIsExam("已考试");
            // 否则设置item的isExam属性为"立即考试"
            } else {
                item.setIsExam("立即考试");
            }
        });
        return page;
    }
}
