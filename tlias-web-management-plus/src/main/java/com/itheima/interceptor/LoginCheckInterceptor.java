package com.itheima.interceptor;

import com.alibaba.fastjson2.JSONObject;
import com.itheima.pojo.Result;
import com.itheima.utils.JwtUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Slf4j
@Component
public class LoginCheckInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        System.out.println("preHandle.....");
        //1、获取请求的URL
        String url = request.getRequestURI();
        log.info("请求路径为：{}", url);

        //2、判断请求头是否包含login
        //3、获取请求头中的令牌
        //4、判断令牌是否存在
        String jwt = request.getHeader("token");
        if (!StringUtils.hasLength(jwt)) {
            log.info("token为空，返回登录界面");
            Result error = Result.error("NOT_LOGIN");
            String notLogin = JSONObject.toJSONString(error);
            response.getWriter().write(notLogin);

            return false;
        }

        //5、解析令牌
        try {
            JwtUtils.parseJwt(jwt);
        } catch (Exception e) {
            e.printStackTrace();
            log.info("令牌解析失败，返回登录界面");
            Result error = Result.error("NOT_LOGIN");
            String notLogin = JSONObject.toJSONString(error);
            response.getWriter().write(notLogin);

            return false;
        }
        //6放行

        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        System.out.println("postHandle..............");
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        System.out.println("afterCompletion.........");
    }
}
