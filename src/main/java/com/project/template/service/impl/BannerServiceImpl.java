package com.project.template.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.project.template.entity.Banner;
import com.project.template.mapper.BannerMapper;
import com.project.template.service.BannerService;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class BannerServiceImpl extends ServiceImpl<BannerMapper, Banner> implements BannerService{

    // 重写page方法，根据传入的参数进行分页查询
    @Override
    public Page<Banner> page(Map<String, Object> query, Integer pageNum, Integer pageSize) {
        // 创建LambdaQueryWrapper对象，用于构建查询条件
        LambdaQueryWrapper<Banner> wrapper = new LambdaQueryWrapper<>();
        // 按照id降序排列
        wrapper.orderByDesc(Banner::getId);
        System.out.println(query.get("name"));

        //条件模糊查询
        if(query.get("name") != null){
            wrapper.like(Banner::getName,query.get("name"));
        }
        // 调用父类的page方法，传入分页参数和查询条件，返回分页结果
        return page(new Page<>(pageNum, pageSize), wrapper);
    }
}
