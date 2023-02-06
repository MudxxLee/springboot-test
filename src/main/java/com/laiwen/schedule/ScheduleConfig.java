package com.laiwen.schedule;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author laiw
 * @date 2022/9/30 15:01
 */
@Data
@Component
@ConfigurationProperties(prefix = "schedule")
public class ScheduleConfig {

    private String demoCron;

    private String demoDesc;


}
