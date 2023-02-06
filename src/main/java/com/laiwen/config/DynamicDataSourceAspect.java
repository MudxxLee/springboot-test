package com.laiwen.config;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.util.Objects;

/**
 * @author laiw
 * @date 2022/11/10 17:05
 */
@Aspect
@Component
public class DynamicDataSourceAspect {

    @Pointcut("@annotation(com.laiwen.config.SpecifyDataSource)")
    public void dataSourcePointCut() {

    }

    @Around("dataSourcePointCut()")
    public Object around(ProceedingJoinPoint point) throws Throwable {
        SpecifyDataSource ds = getSpecifyDataSourceAnnotation(point);
        if (ds == null) {
            DynamicDataSource.setDataSource(DataSourceTypeEnum.DEFAULT.getName());
        } else {
            DynamicDataSource.setDataSource(ds.value().getName());
        }
        try {
            return point.proceed();
        } finally {
            DynamicDataSource.clearDataSource();
        }
    }

    /**
     * 根据类或方法获取数据源注解
     */
    private SpecifyDataSource getSpecifyDataSourceAnnotation(ProceedingJoinPoint joinPoint) {
        Class<?> targetClass = joinPoint.getTarget().getClass();
        SpecifyDataSource dsAnnotation = targetClass.getAnnotation(SpecifyDataSource.class);
        // 先判断类的注解，再判断方法注解
        if (Objects.nonNull(dsAnnotation)) {
            return dsAnnotation;
        } else {
            MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
            return methodSignature.getMethod().getAnnotation(SpecifyDataSource.class);
        }
    }

}
