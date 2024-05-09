package com.itheima.log;

import com.alibaba.fastjson2.JSONObject;
import com.itheima.mapper.OperateLogMapper;
import com.itheima.pojo.OperateLog;
import com.itheima.utils.JwtUtils;
import io.jsonwebtoken.Claims;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.Arrays;

@Slf4j
@Component
@Aspect
public class LogAspect {
    @Autowired
    private HttpServletRequest request;
    @Autowired
    private OperateLogMapper operateLogMapper;

    @Around("@annotation(com.itheima.anno.Log)")

    public Object recording(ProceedingJoinPoint joinPoint) throws Throwable {
        //操作人id-获取请求头中的令牌就行
        String jwt = request.getHeader("token");
        Claims claims = JwtUtils.parseJwt(jwt);
        Integer operateUser = (Integer) claims.get("id");

        //操作人时间
        LocalDateTime operateTime = LocalDateTime.now();

        //操作类名
        String className = joinPoint.getTarget().getClass().getName();

        //操作方法名
        String methodName = joinPoint.getSignature().getName();

        //操作方法参数
        Object[] args = joinPoint.getArgs();
        String methodParams = Arrays.toString(args);
        long begin = System.currentTimeMillis();
        //调出原始目标方法运行
        Object result = joinPoint.proceed();
        long end = System.currentTimeMillis();
        //方法返回值
        String returnValue = JSONObject.toJSONString(result);
        //操作耗时
        long costTime = end - begin;
        log.info("操作耗时：{}", costTime);
        //记录操作日志

        OperateLog operateLog = new OperateLog(null, operateUser, operateTime, className, methodName, methodParams, returnValue, costTime);
        operateLogMapper.insert(operateLog);
        log.info("aop记录操作日志:{}", operateLog);
        return result;
    }
}
