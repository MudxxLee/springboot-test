package com.laiwen.spring.aop;

import cn.hutool.json.JSONUtil;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * @author laiw
 * @date 2022/9/30 10:28
 */
@Aspect
@Component
public class AnnotationAopHandler {
    private static final Logger logger = LoggerFactory.getLogger(AnnotationAopHandler.class);

    @Pointcut("@annotation(com.laiwen.spring.aop.AnnotationLog)")
    public void pointCut() {

    }

    @Around("pointCut()")
    public Object handler(ProceedingJoinPoint joinPoint) throws Throwable {
        String className = joinPoint.getTarget().getClass().getSimpleName();
        String methodName = joinPoint.getSignature().getName();
        Object[] args = joinPoint.getArgs();
        logger.info("AOP_LOG start -----------------");

        String jsonString = JSONObject.toJSONString(args, SerializerFeature.IgnoreErrorGetter);
//        String jsonString = JSONUtil.toJsonStr(args);
        logger.info("AOP_LOG args={}", jsonString);
        Object result = null;
        try {
            result = joinPoint.proceed();
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        } finally {
            logger.info("AOP_LOG end -----------------");
        }
        return result;
    }

}
