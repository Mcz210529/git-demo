package com.example.controller;

import com.example.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class LoginController {

    // 显示登录页面
    @GetMapping("/login")
    public String showLoginForm() {
        return "login.html";
    }

    // 处理登录请求
    @PostMapping("/login")
    public String login(@RequestParam String username, @RequestParam String password, Model model) {
        // 这里可以添加验证逻辑，比如查询数据库进行用户验证
        // 这里只是一个示例，实际情况需要根据具体需求进行处理

        // 假设用户名为 "admin"，密码为 "password" 为有效用户
        if ("admin".equals(username) && "password".equals(password)) {
            // 登录成功，跳转到欢迎页面
            model.addAttribute("username", username);
            return "welcome";
        } else {
            // 登录失败，返回登录页面并显示错误信息
            model.addAttribute("error", "Invalid username or password.");
            return "login";
        }
    }

}

