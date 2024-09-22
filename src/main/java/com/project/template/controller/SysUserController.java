package com.project.template.controller;


import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.project.template.common.PageVO;
import com.project.template.common.Result;
import com.project.template.dto.*;
import com.project.template.entity.SysUser;
import com.project.template.enums.RoleType;
import com.project.template.service.SysUserService;
import com.project.template.utils.Utils;
import com.project.template.vo.SysUserListVO;
import com.project.template.vo.SysUserVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 用户信息表 前端控制器
 * </p>
 *
 * @author B站：程序员瑞哥
 *
 */
@Api(value = "用户模块", tags = "用户模块")

@RestController
@RequestMapping("/sysUser")
public class SysUserController{

    @Resource
    SysUserService sysUserService;

    /**
     * 列表
     * @return
     */
    @ApiOperation(value = "用户列表", notes = "")
    @GetMapping("/list")
    public Result<List<SysUser>> list() {
        return new Result<>().success(sysUserService.list());
    }

    /**
     * 分页查询
     * @param user
     * @param pageNum
     * @param pageSize
     * @return
     */
    @ApiOperation(value = "分页查询", notes = "分页查询")
    @GetMapping("/page")
    public Result<PageVO<SysUser>> findPage(@RequestParam(defaultValue = "") String user,
                                            @RequestParam(defaultValue = "1") Integer pageNum,
                                            @RequestParam(defaultValue = "10") Integer pageSize) {
        LambdaQueryWrapper<SysUser> wrapper = new LambdaQueryWrapper<>();
        //查出的数据根据id降序排列
        wrapper.orderByDesc(SysUser::getId);

        Page<SysUser> page = sysUserService.page(new Page<>(pageNum,pageSize),wrapper);
        Page<SysUserListVO> voPage = new Page<>();
        BeanUtil.copyProperties(page,voPage);
        List<SysUser> records = page.getRecords();
        List<SysUserListVO> listVOList = new ArrayList<>();
        records.forEach(item->{
            SysUserListVO sysUserListVO = new SysUserListVO();
            BeanUtil.copyProperties(item,sysUserListVO);
            listVOList.add(sysUserListVO);
        });
        voPage.setRecords(listVOList);
        return new Result<>().success(new PageVO<>(voPage));
    }

    /**
     * 数据新增
     * @param user
     * @return
     */
    @ApiOperation(value = "数据新增", notes = "数据新增")
    @PostMapping("/add")
    public Result add(@Validated @RequestBody SysUser user) {
        sysUserService.insertOrUpdate(user);
        return new Result<>().success();
    }

    /**
     * 数据更新
     * @param user
     * @return
     */
    @ApiOperation(value = "数据更新", notes = "数据更新")
    @PutMapping("/update")
    public Result updateById(@Validated @RequestBody SysUser user) {
        sysUserService.insertOrUpdate(user);
        return new Result<>().success();
    }

    /**
     * 根据id删除
     * @param ids
     * @return
     */
    @ApiOperation(value = "数据根据id批量删除", notes = "数据根据id批量删除")
    @DeleteMapping("/delBatch/{ids}")
    public Result delBatch(@PathVariable List<Integer> ids) {
        sysUserService.removeByIds(ids);
        return new Result<>().success();
    }

    /**
     * 根据id查询
     * @param id
     * @return
     */
    @GetMapping("/getById")
    public Result<SysUserVO> getById(@RequestParam("id") Integer id) {
        SysUser byId = sysUserService.getById(id);
        SysUserVO userVO = new SysUserVO();
        BeanUtils.copyProperties(byId, userVO);
        return new Result<>().success(userVO);
    }

    /**
     * 用户登录
     * @param userLoginDTO
     * @return
     */
    @PostMapping("/login")
    public Result login(@Validated @RequestBody UserLoginDTO userLoginDTO) {
        return new Result<>().success(sysUserService.login(userLoginDTO));
    }

    /**
     * 用户注册
     * @param userRegisterDTO
     * @return
     */
    @PostMapping("/register")
    public Result register(@Validated @RequestBody UserRegisterDTO userRegisterDTO) {
        sysUserService.register(userRegisterDTO);
        return new Result<>().success();
    }

    /**
     * 修改密码
     * @param dto
     * @return
     */
    @PutMapping("/editPassword")
    public Result editPassword(@RequestBody EditPasswordDTO dto){
        sysUserService.editPassword(dto);
        return new Result<>().success();
    }

    /**
     * 数据更新
     * @param user
     * @return
     */
    @ApiOperation(value = "密码重置", notes = "密码重置")
    @PutMapping("/resetPassword")
    public Result resetPassword(@RequestBody SysUser user) {
        return new Result<>().success(sysUserService.resetPassword(user));
    }

}
