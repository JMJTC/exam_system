package com.project.template.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.project.template.entity.Banner;

import java.util.Map;

public interface BannerService extends IService<Banner> {

    // 分页查询Banner
    Page<Banner> page(Map<String, Object> query, Integer pageNum, Integer pageSize);
}
