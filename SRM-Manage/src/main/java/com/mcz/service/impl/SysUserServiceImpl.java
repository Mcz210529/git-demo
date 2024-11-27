package com.mcz.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mcz.pojo.SysUser;
import com.mcz.mapper.SysUserMapper;
import com.mcz.service.ISysUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mcz.utils.BCryptUtil;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author mcz
 * @since 2024-11-27
 */
@Service
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUser> implements ISysUserService {

    @Override
    public SysUser findByUsername(String username) {
        return query().eq("username", username).one();
    }

    @Override
    public SysUser login(String password, String username) {
        SysUser user = query().eq("username", username).one();
        return (user != null && BCryptUtil.checkPassword(password, user.getPassword())) ? user : null;
    }


    @Override
    public Page<SysUser> pageUser(Integer pageNum, Integer pageSize, String name, String username, String vendorCode, String company) {
        LambdaQueryWrapper<SysUser> wrapper = new LambdaQueryWrapper<>();
        if (name != null && !StringUtils.isEmpty(name)) {
            wrapper.like(SysUser::getName, name);
        }
        if (username != null && !StringUtils.isEmpty(username)) {
            wrapper.like(SysUser::getUsername, username);
        }
        if (vendorCode != null && !StringUtils.isEmpty(vendorCode)) {
            wrapper.eq(SysUser::getVendorCode, vendorCode);
        }
        if (company != null && !StringUtils.isEmpty(company)) {
            wrapper.eq(SysUser::getCompany, company);
        }


        return this.page(new Page<>(pageNum, pageSize), wrapper);
    }
}
