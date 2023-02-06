package com.laiwen.config;

/**
 * 数据源枚举类
 *
 * @author laiwen
 */
public enum DataSourceTypeEnum {

    /**
     * internal_system
     */
    DEFAULT("internal_system"),
    /**
     * external_user
     */
    EXTERNAL_USER("external_user");

    private final String name;

    DataSourceTypeEnum(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

}
