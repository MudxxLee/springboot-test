package com.laiwen.schedule;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.Trigger;
import org.springframework.scheduling.TriggerContext;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;
import org.springframework.scheduling.support.CronTrigger;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.Date;

/**
 * @author laiw
 * @date 2022/9/30 14:51
 */
@Slf4j
@Component
public class ScheduleTask implements SchedulingConfigurer {

    @Autowired
    private ScheduleConfig scheduleConfig;

    @Override
    public void configureTasks(ScheduledTaskRegistrar taskRegistrar) {

        taskRegistrar.addTriggerTask(new Runnable() {
            @Override
            public void run() {
                //log.info("schedule cron: {}, desc: {}, time: {}", scheduleConfig.getDemoCron(), scheduleConfig.getDemoDesc(), LocalDateTime.now());
                //log.info("do something ...");
            }
        }, new Trigger() {
            @Override
            public Date nextExecutionTime(TriggerContext triggerContext) {
                // 使用CronTrigger触发器,可动态修改cron表达式来操作循环规则
                CronTrigger trigger = new CronTrigger(scheduleConfig.getDemoCron());
                return trigger.nextExecutionTime(triggerContext);
            }
        });

    }
}
