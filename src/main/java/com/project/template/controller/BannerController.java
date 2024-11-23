package com.project.template.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.project.template.common.PageVO;
import com.project.template.common.Result;
import com.project.template.entity.Banner;
import com.project.template.service.BannerService;
import io.swagger.annotations.ApiOperation;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/banner")
public class BannerController {

    // 注入BannerService
    @Resource
    private BannerService bannerService;

    // 列表
    @ApiOperation(value = "列表",notes = "列表")
    @GetMapping("/list")
    private Result<List<Banner>> list(){
        // 调用BannerService的list方法获取列表
        return new Result().success(bannerService.list());
    }

    // 分页查询
    @GetMapping("/page")
    public Result<PageVO<Banner>> findPage(
            @RequestParam Map<String,Object> query,
            @RequestParam(defaultValue = "1") Integer pageNum,
            @RequestParam(defaultValue = "10") Integer pageSize
    ){
        // 调用BannerService的page方法进行分页查询
        Page<Banner> page= bannerService.page(query,pageNum,pageSize);
        // 返回分页结果
        return new Result().success(new PageVO<>(page));
    }

    // 添加
    @PostMapping("/add")
    public Result add(@RequestBody Banner banner){
        // 调用BannerService的save方法保存banner
        bannerService.save(banner);
        // 返回成功结果
        return new Result<>().success();
    }

    // 更新
    @PutMapping("/update")
    public Result updateById(@RequestBody Banner banner){
        // 调用BannerService的updateById方法更新banner
        bannerService.updateById(banner);
        // 返回成功结果
        return new Result<>().success();
    }

    // 批量删除
    @DeleteMapping("/delBatch")
    public Result delBatch(@RequestBody List<Integer> ids){
        // 调用BannerService的removeByIds方法批量删除banner
        bannerService.removeByIds(ids);
        // 返回成功结果
        return new Result().success();
    }

}
