package com.project.template.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.project.template.common.PageVO;
import com.project.template.common.Result;
import com.project.template.entity.Exam;
import com.project.template.entity.ExamQuestion;
import com.project.template.entity.Score;
import com.project.template.enums.RoleType;
import com.project.template.service.ExamService;
import com.project.template.service.ScoreService;
import com.project.template.utils.UserThreadLocal;
import com.project.template.utils.Utils;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.rmi.CORBA.Util;
import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/exam")
public class ExamController {

    @Resource
    private ExamService examService;
    @Resource
    private ScoreService scoreService;

    @ApiOperation(value = "列表",notes = "列表")
    // 定义一个GET请求，路径为/list，返回一个Result<List<Exam>>类型的结果
    @GetMapping("/list")
    private Result<List<Exam>> list(){
        // 创建一个LambdaQueryWrapper对象，用于查询ExamQuestion表
        LambdaQueryWrapper<ExamQuestion> wrapper = new LambdaQueryWrapper<>();
        if(RoleType.TEACHER.getKey().equals(Utils.getUser().getRoleType())){
            // 如果当前用户是教师，则查询userId等于当前用户id的记录
            // 设置查询条件，查询userId等于当前用户id的记录
            wrapper.eq(ExamQuestion::getUserId, Utils.getUser().getId());
        }
        // 返回查询结果
        return new Result().success(examService.list());
    }

    @GetMapping("/page")
    public Result<PageVO<Exam>> findPage(
            @RequestParam Map<String,Object> query,
            @RequestParam(defaultValue = "1") Integer pageNum,
            @RequestParam(defaultValue = "10") Integer pageSize
    ){
        Page<Exam> page= examService.page(query,pageNum,pageSize);
        return new Result().success(new PageVO<>(page));
    }

    @PostMapping("/add")
//    @Validated
    public Result add(@RequestBody Exam exam){
        //设置UserID
        exam.setUserId(UserThreadLocal.getCurrentUser().getId());
        examService.save(exam);
        return new Result<>().success();
    }

    @PutMapping("/update")
//    @Validated
    public Result updateById(@RequestBody Exam exam){
        examService.updateById(exam);
        return new Result<>().success();
    }

    @DeleteMapping("/delBatch")
    public Result delBatch(@RequestBody List<Integer> ids){
        //如果该考试有评分记录，则不能删除，需先删除评分记录，返回错误信息
        for(Integer id:ids){
            if(scoreService.count(new LambdaQueryWrapper<Score>().eq(Score::getExamId,id))>0) {
                return new Result().error("该考试有评分记录，不能删除");
            }
        }
        examService.removeByIds(ids);
        return new Result().success();
    }

}