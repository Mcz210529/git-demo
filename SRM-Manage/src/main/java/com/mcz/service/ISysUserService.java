package com.mcz.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mcz.pojo.SysUser;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author mcz
 * @since 2024-11-27
 */
public interface ISysUserService extends IService<SysUser> {

    SysUser findByUsername(String username);

    SysUser login(String password, String username);


    Page<SysUser> pageUser(Integer pageNum, Integer pageSize, String name, String username, String vendorCode, String company);
}
