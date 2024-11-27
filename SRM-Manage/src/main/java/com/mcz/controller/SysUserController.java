package com.mcz.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mcz.pojo.Result;
import com.mcz.pojo.SysUser;
import com.mcz.service.ISysUserService;
import com.mcz.utils.JwtUtil;
import jakarta.validation.constraints.Pattern;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author mcz
 * @since 2024-11-27
 */
@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class SysUserController {
    private final ISysUserService userService;
    private final StringRedisTemplate stringRedisTemplate;

    /**
     * 登录接口
     *
     * @param username
     * @param password
     * @return
     */
    @PostMapping("/login")
    public Result<String> login(@Pattern(regexp = "^\\S{5,16}$", message = "用户名必须是5到16个非空字符") String username, @Pattern(regexp = "^\\S{5,16}$", message = "密码必须是5到16个非空字符") String password) {
        SysUser user = userService.findByUsername(username);
        if (user == null) {
            return Result.error("用户名不存在");
        }

        if (userService.login(password, username) != null) {
            Map<String, Object> claims = new HashMap<>();
            claims.put("id", user.getUuid());
            claims.put("username", user.getUsername());

            String token = JwtUtil.createToken(claims);
            System.out.println(token);
            ValueOperations<String, String> ops = stringRedisTemplate.opsForValue();
            ops.set(token, token, 10, TimeUnit.MINUTES);
            return Result.success(token);
        }
        return Result.error("密码错误");

    }

    /**
     * 分页查询
     */
    @GetMapping("/page")
    public Result<Page<SysUser>> list(Integer pageNum, Integer pageSize,
                                      @RequestParam(required = false) String name,
                                      @RequestParam(required = false) String username,
                                      @RequestParam(required = false) String vendorCode,
                                      @RequestParam(required = false) String company) {

        Page<SysUser> articlePage = userService.pageUser(pageNum, pageSize, name, username, vendorCode, company);
        return Result.success(articlePage);

    }

    @PutMapping("/update")
    public Result<String> update(@RequestBody @Validated SysUser user) {
        user.setUpdateTime(LocalDateTime.now());
        userService.updateById(user);
        return Result.success();
    }

    @DeleteMapping("/delete/{id}")
    public Result<String> delete(@PathVariable String id) {
        userService.removeById(id);
        return Result.success();
    }

    @PostMapping("/add")
    public Result<String> add(@RequestBody @Validated SysUser user) {
        user.setCreateTime(LocalDateTime.now());
        userService.save(user);
        return Result.success();
    }
}
