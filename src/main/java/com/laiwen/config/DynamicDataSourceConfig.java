package com.laiwen.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

/**
 * @author laiw
 * @date 2022/11/10 16:50
 */
@Configuration
public class DynamicDataSourceConfig {

    @Bean(name = "primaryDataSource")
    @ConfigurationProperties(prefix = "spring.datasource.primary")
    public DataSource primaryDataSource() {
        return DataSourceBuilder.create().build();
    }

    @Bean(name = "secondaryDataSource")
    @ConfigurationProperties(prefix = "spring.datasource.secondary")
    public DataSource secondaryDataSource() {
        return DataSourceBuilder.create().build();
    }

    @Bean(name="dynamicDataSource")
    @Primary
    public DynamicDataSource dataSource() {
        DataSource defaultTargetDataSource = primaryDataSource();
        Map<Object, Object> targetDataSources = new HashMap<>(2);
        targetDataSources.put(DataSourceTypeEnum.DEFAULT.getName(), defaultTargetDataSource);
        targetDataSources.put(DataSourceTypeEnum.EXTERNAL_USER.getName(), secondaryDataSource());
        return new DynamicDataSource(defaultTargetDataSource, targetDataSources);
    }
}

