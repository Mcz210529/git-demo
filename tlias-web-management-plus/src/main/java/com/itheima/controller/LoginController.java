package com.itheima.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.itheima.pojo.Emp;
import com.itheima.pojo.Result;
import com.itheima.service.EmpService;
import com.itheima.utils.JwtUtils;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;


@RestController
@Slf4j
public class LoginController {
    @Autowired
    private EmpService empService;
    @Autowired
    private JwtUtils jwtUtils;

    @PostMapping("/login")
    public Result login(@RequestBody Emp emp) {
        log.info("登录员工信息{}", emp);
        String password =emp.getPassword();
        password = DigestUtils.md5DigestAsHex(password.getBytes());
        //2、根据页面提交的用户名username查询数据库
        LambdaQueryWrapper<Emp> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Emp::getUsername, emp.getUsername());
        Emp emp1 = empService.getOne(queryWrapper);
        if (emp1 != null) {

            Map<String, Object> claims = new HashMap<>();
            claims.put("id", emp1.getId());
            claims.put("name", emp1.getName());
            claims.put("username", emp1.getUsername());
            String jwt = jwtUtils.genJwt(claims);
            return Result.success(jwt);
        }
        return Result.error("信息不存在");
    }
}
