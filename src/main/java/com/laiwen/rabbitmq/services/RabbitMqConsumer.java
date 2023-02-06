package com.laiwen.rabbitmq.services;

import net.agilewing.phoenix.common.model.DateUtils;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 如果消息类型为字节数组，则content-type为application/octet-stream；
 * 如果消息类型为字符串，则content-type为text/plain;
 * 如果消息类型为序列化对象，则content-type为application/x-java-serialized-object。
 *
 *
 *
 * @author laiw
 * @date 2022/9/6
 */
@Component
public class RabbitMqConsumer {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    //@RabbitListener(queues = "agilecdn-laiw")
    public void handler(com.rabbitmq.client.Channel channel, org.springframework.amqp.core.Message message) throws Exception {
        String jsonStr = new String(message.getBody(), StandardCharsets.UTF_8);
        System.out.println("receive msg -> time: " + DateUtils.format(new Date(), DateUtils.DATE_PATTERN.YYYY_MM_DD_HH_MM_SS) + ", body:" + jsonStr);
        Thread.sleep(2000);
        channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
//        rabbitTemplate.convertAndSend(message.getMessageProperties().getReceivedRoutingKey(), jsonStr);
//        RabbitMQUtil.sendMQ(rabbitTemplate,
//                message.getBody(),
//                "agilecdn-laiw",
//                "agw-delayed-exchange",
//                60 * 1000L);

        // 重收MQ消息至队列(超过最大次数则进入死信队列)
       // RabbitMQUtil.RetryConsume(channel, message, 5, "agw-delayed-exchange12", 10000L);



    }

    //@RabbitListener(queues = "${agilewing.laiw.rabbitmq.queue}")
    public void handlerBatch(List<org.springframework.amqp.core.Message> messages, com.rabbitmq.client.Channel channel) throws Exception {
        System.out.println("test receive msg -> 消息总数" + messages.size());
        for (org.springframework.amqp.core.Message message : messages) {
            System.out.println("test receive msg -> 当前线程:" + Thread.currentThread().getName() +
                    ",  body:" + new String(message.getBody(), StandardCharsets.UTF_8));
            channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
        }
        Thread.sleep(3000);
        //channel.basicNack(message.getMessageProperties().getDeliveryTag(), false, true);
    }

    //@RabbitListener(queues = "${agilewing.laiw.rabbitmq.queue}")
    public void handler(Map<String, Object> objectMap, com.rabbitmq.client.Channel channel, org.springframework.amqp.core.Message message) throws IOException {
        System.out.println("test receive msg : " + objectMap.toString());
        //channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
        //System.out.println("test receive msg end ");
        //channel.basicNack(message.getMessageProperties().getDeliveryTag(), false, true);
    }

    //@RabbitListener(queues = "agilecdn-test")
    public void handlerTest(String jsonStr, com.rabbitmq.client.Channel channel, org.springframework.amqp.core.Message message) throws IOException {
        System.out.println("test receive msg : " + jsonStr);
        channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
    }

    /**
     * 获取消息被重试的次数
     */
    public long getRetryCount(MessageProperties messageProperties) {
        Long retryCount = 0L;
        if (null != messageProperties) {
            List<Map<String, ?>> deaths = messageProperties.getXDeathHeader();
            if (deaths != null && deaths.size() > 0) {
                Map<String, Object> death = (Map<String, Object>)deaths.get(0);
                retryCount = (Long)death.get("count");
            }
        }
        return retryCount;
    }

}
