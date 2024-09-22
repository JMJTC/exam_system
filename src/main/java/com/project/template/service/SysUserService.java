package com.project.template.service;

import com.project.template.dto.EditPasswordDTO;
import com.project.template.dto.UserLoginDTO;
import com.project.template.dto.UserRegisterDTO;
import com.project.template.entity.SysUser;
import com.baomidou.mybatisplus.extension.service.IService;
import com.project.template.vo.SysUserLoginVO;

/**
 * <p>
 * 用户信息表 服务类
 * </p>
 *
 * @author B站：程序员瑞哥
 * 
 */
public interface SysUserService extends IService<SysUser> {

    void register(UserRegisterDTO userRegisterDTO);

    void editPassword(EditPasswordDTO dto);

    SysUserLoginVO login(UserLoginDTO userLoginDTO);

    void insertOrUpdate(SysUser user);

    String resetPassword(SysUser user);
}
