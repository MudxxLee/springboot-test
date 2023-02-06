package com.laiwen.rabbitmq.interceptor;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @author laiw
 * @date 2022/9/8
 */
@Aspect
@Component
public class RabbitInterceptor {
    private final static Logger log = LoggerFactory.getLogger(RabbitInterceptor.class);

    @Value("${spring.rabbitmq.listener.simple.acknowledge-mode:auto}")
    private String acknowledgeMode;

    @Pointcut("@annotation(com.laiwen.rabbitmq.interceptor.MyRabbitMQConsumer)")
    public void consumerPointCut() {
    }

    @Around("consumerPointCut()")
    public Object consumerListenerAround(ProceedingJoinPoint joinPoint) throws Throwable {
        String className = joinPoint.getTarget().getClass().getSimpleName();
        String methodName = joinPoint.getSignature().getName();
        Object[] args = joinPoint.getArgs();
        com.rabbitmq.client.Channel channel = null;
        Message amqpMessage = null;
        String correlationId = "";
        long deliveryTag = -1L;
        for (Object arg : args) {
            if (arg instanceof Message) {
                amqpMessage = (Message) arg;
                deliveryTag = amqpMessage.getMessageProperties().getDeliveryTag();
                correlationId = amqpMessage.getMessageProperties().getCorrelationId();
            } else if (arg instanceof org.springframework.messaging.Message<?>) {
                org.springframework.messaging.Message message = (org.springframework.messaging.Message) arg;
                deliveryTag = (long) message.getHeaders().get("amqp_deliveryTag");
                correlationId = (String) message.getHeaders().get("amqp_correlationId");
            } else if (arg instanceof com.rabbitmq.client.Channel) {
                channel = (com.rabbitmq.client.Channel) arg;
            }
        }
        if (log.isInfoEnabled()) {
            log.info("RabbitMQ Interceptor > {}.{}(), parameters: {}", className, methodName, args);
        }
        long start = System.nanoTime();
        Object obj = null;

        if ("auto".equalsIgnoreCase(acknowledgeMode)) {
            obj = joinPoint.proceed(args);
        } else {
            if (channel == null) {
                throw new RuntimeException("手动确认消息，方法参数需要有Channel");
            }
            try {
                obj = joinPoint.proceed(args);
            } catch (Exception e) {
                // 是否重新投递到队列
                channel.basicNack(deliveryTag, false, false);
                throw e;
            }
        }
        if (log.isInfoEnabled()) {
            log.info("RabbitMQ Interceptor < [" + (System.nanoTime() - start) / 1000000 + "]ms");
        }
        return obj;
    }

}
