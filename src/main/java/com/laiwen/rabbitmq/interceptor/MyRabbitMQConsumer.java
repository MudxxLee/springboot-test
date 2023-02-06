package com.laiwen.rabbitmq.interceptor;

import java.lang.annotation.*;

/**
 * @author laiw
 * @date 2022/9/8
 */
@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface MyRabbitMQConsumer {
    String value() default "";
}