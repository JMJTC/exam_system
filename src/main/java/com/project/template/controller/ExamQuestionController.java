package com.project.template.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.project.template.common.PageVO;
import com.project.template.common.Result;
import com.project.template.entity.ExamQuestion;
import com.project.template.service.ExamQuestionService;
import com.project.template.utils.UserThreadLocal;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/examQuestion")
public class ExamQuestionController {

    @Resource
    private ExamQuestionService examQuestionService;

    @ApiOperation(value = "列表",notes = "列表")
    @GetMapping("/list")
    private Result<List<ExamQuestion>> list(){
        return new Result().success(examQuestionService.list());
    }

    @GetMapping("/page")
    public Result<PageVO<ExamQuestion>> findPage(
            @RequestParam Map<String,Object> query,
            @RequestParam(defaultValue = "1") Integer pageNum,
            @RequestParam(defaultValue = "10") Integer pageSize
    ){
        Page<ExamQuestion> page= examQuestionService.page(query,pageNum,pageSize);
        return new Result().success(new PageVO<>(page));
    }

    @PostMapping("/add")
    // 添加一个POST请求，路径为/add
    public Result add(@RequestBody ExamQuestion examQuestion){
        // 接收一个ExamQuestion类型的参数
        examQuestion.setUserId(UserThreadLocal.getCurrentUser().getId());
        // 设置当前用户的ID
        examQuestionService.save(examQuestion);
        // 调用examQuestionService的save方法，保存examQuestion
        return new Result<>().success();
        // 返回一个成功的Result对象
    }

    @PutMapping("/update")
    public Result updateById(@RequestBody ExamQuestion examQuestion){
        examQuestionService.updateById(examQuestion);
        return new Result<>().success();
    }

    @DeleteMapping("/delBatch")
    public Result delBatch(@RequestBody List<Integer> ids){
        examQuestionService.removeByIds(ids);
        return new Result().success();
    }

    @GetMapping("/getQuestion")
    public Result getQuestion(@RequestParam("ids") Integer[] ids){
        List<ExamQuestion> list = new ArrayList<>();
        for(Integer id : ids){
            ExamQuestion byId = examQuestionService.getById(id);
            list.add(byId);
        }
        return new Result<>().success(list);
    }

}