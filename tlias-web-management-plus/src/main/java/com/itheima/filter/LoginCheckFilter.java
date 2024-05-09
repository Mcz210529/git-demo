package com.itheima.filter;

import com.alibaba.fastjson2.JSONObject;
import com.itheima.pojo.Result;
import com.itheima.utils.JwtUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
//@WebFilter(urlPatterns = "/*")
public class LoginCheckFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        //1、获取请求的URL
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse resp = (HttpServletResponse) servletResponse;
        String url = req.getRequestURI();
        log.info("请求路径为:{}", url);

        //2、判断请求头是否包含login
        if (url.contains("login")) {
            log.info("登录操作，放行。。。");
            filterChain.doFilter(req, resp);
            return;
        }
        //3、获取请求头中的令牌
        String jwt = req.getHeader("token");
        //4、判断令牌是否存在
        if (!StringUtils.hasLength(jwt)) {
            log.info("token为空，返回登录界面");
            Result error = Result.error("NOT_LOGIN");
            String notLogin = JSONObject.toJSONString(error);
            resp.getWriter().write(notLogin);
            return;
        }
        //5、解析令牌
        try {
            JwtUtils.parseJwt(jwt);
        } catch (Exception e) {
            e.printStackTrace();
            log.info("令牌解析失败，返回登录界面");
            Result error = Result.error("NOT_LOGIN");
            String notLogin = JSONObject.toJSONString(error);
            resp.getWriter().write(notLogin);
        }
        //6放行
        filterChain.doFilter(req, resp);
    }
}
