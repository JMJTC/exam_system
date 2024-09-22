package com.project.template.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.project.template.common.PageVO;
import com.project.template.common.Result;
import com.project.template.entity.Score;
import com.project.template.service.ScoreService;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;


/**
 *
 * @author Jerry
 * @date 2024/9/10
 */

@RestController
@RequestMapping("/score")
public class ScoreController {

    @Resource
    private ScoreService scoreService;

    @ApiOperation(value = "列表",notes = "列表")
    @GetMapping("/list")
    private Result<List<Score>> list(){
        return new Result().success(scoreService.list());
    }

    @GetMapping("/page")
    public Result<PageVO<Score>> findPage(
            @RequestParam Map<String,Object> query,
            @RequestParam(defaultValue = "1") Integer pageNum,
            @RequestParam(defaultValue = "10") Integer pageSize
    ){
        Page<Score> page= scoreService.page(query,pageNum,pageSize);
        return new Result().success(new PageVO<>(page));
    }

    @PostMapping("/add")
//    @Validated
    public Result add(@RequestBody Score score){
        scoreService.save(score);
        return new Result<>().success();
    }

    @PutMapping("/update")
//    @Validated
    public Result updateById(@RequestBody Score score){
        scoreService.updateById(score);
        return new Result<>().success();
    }

    @DeleteMapping("/delBatch")
    public Result delBatch(@RequestBody List<Integer> ids){
        scoreService.removeByIds(ids);
        return new Result().success();
    }

    @GetMapping("/getById")
    public Result getById(@RequestParam("id") Integer id){
        Score byId = scoreService.getById(id);
        return new Result().success(byId);
    }
}