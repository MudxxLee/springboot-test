package com.laiwen.config;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author laiw
 * @date 2022/11/10 17:02
 */
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface SpecifyDataSource {

    DataSourceTypeEnum value() default DataSourceTypeEnum.DEFAULT;

}
