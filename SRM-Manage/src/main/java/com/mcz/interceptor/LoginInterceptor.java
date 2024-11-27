package com.mcz.interceptor;

import com.mcz.utils.JwtUtil;
import com.mcz.utils.ThreadLocalUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import java.util.Map;

@Component
public class LoginInterceptor implements HandlerInterceptor {
    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String token = request.getHeader("Authorization");

        try {
            ValueOperations<String, String> ops = stringRedisTemplate.opsForValue();
            String s = ops.get(token);
            if (s == null) {
                throw new RuntimeException("token不存在");
            }
            Map<String, Object> map = JwtUtil.parseToken(token);
            ThreadLocalUtil.set(map);
            return true;
        } catch (Exception e) {
            response.setStatus(401);
            return false;

        }
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        ThreadLocalUtil.remove();
    }
}
