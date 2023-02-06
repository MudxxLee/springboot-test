package com.laiwen.rabbitmq.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.listener.ListenerContainerConsumerFailedEvent;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import java.util.Arrays;

/**
 * MQ消费者失败事件监听器
 *  (RabbitMQ删除队列不重启消费者，动态重启)
 * @author laiwen
 */
//@Component
public class ListenerContainerConsumerFailedEventListener implements ApplicationListener<ListenerContainerConsumerFailedEvent> {
    private final static Logger log = LoggerFactory.getLogger(ListenerContainerConsumerFailedEventListener.class);


    @Override
    public void onApplicationEvent(ListenerContainerConsumerFailedEvent event) {
        // event BlockingQueueConsumer
        if (event.isFatal()) {
            log.error(String.format("Stopping container from aborted consumer. Reason::%s.", event.getReason()), event.getThrowable());
            SimpleMessageListenerContainer container = (SimpleMessageListenerContainer) event.getSource();
            String queueNames = Arrays.toString(container.getQueueNames());
            try {
                // 重启
                restart(container);
                log.info("Restart Queue [{}] Listening success！", queueNames);
            } catch (Exception e) {
                log.error(String.format("Restart Queue [%s] Listening failed！", queueNames), e);
            }
        }
    }

    /**
     * 重启监听
     */
    private void restart(SimpleMessageListenerContainer container) {
        Assert.state( ! container.isRunning(), String.format("Listening container [%s] running！", container));
        container.start();
    }

}
