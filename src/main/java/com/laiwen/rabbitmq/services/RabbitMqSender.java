package com.laiwen.rabbitmq.services;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import net.agilewing.phoenix.common.model.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.MessageDeliveryMode;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;


/**
 * @author laiwen
 */

@Configuration
@EnableScheduling
public class RabbitMqSender {
	private final static Logger logger = LoggerFactory.getLogger(RabbitMqSender.class);

	private final static String RABBITMQ_QUEUE = "agilecdn-laiw";

	@Autowired
	private RabbitTemplate rabbitTemplate;


	//@PostConstruct
	//@Scheduled(cron = "0/10 * * * * ?")
	public void send() {
		System.out.println("test send msg");
		try {
			for (int i = 0; i < 1; i++) {
				Map<String, Object> map = new HashMap<>();
				map.put("time", DateUtils.format(new Date(), DateUtils.DATE_PATTERN.YYYY_MM_DD_HH_MM_SS));
				map.put("desc", "实时测试");
				rabbitTemplate.convertAndSend(RABBITMQ_QUEUE, JSON.toJSONString(map, SerializerFeature.WriteMapNullValue));
				//rabbitTemplate.convertAndSend(rabbitmqQueue, map);

				//send1();

				//send2();
				
			}

			System.out.println("test send msg end");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void send1() {
		Map<String, Object> map = new HashMap<>();
		map.put("time", DateUtils.format(new Date(), DateUtils.DATE_PATTERN.YYYY_MM_DD_HH_MM_SS));
		map.put("desc", "延迟120秒测试");
		rabbitTemplate.convertAndSend(RABBITMQ_QUEUE,  JSON.toJSONString(map, SerializerFeature.WriteMapNullValue), messageObject ->{
			messageObject.getMessageProperties().setDeliveryMode(MessageDeliveryMode.PERSISTENT);  //消息持久化
			messageObject.getMessageProperties().setDelay(120 * 1000);   // 单位为毫秒
			return messageObject;
		});
	}

	public void send2() {
		Map<String, Object> map = new HashMap<>();
		map.put("time", DateUtils.format(new Date(), DateUtils.DATE_PATTERN.YYYY_MM_DD_HH_MM_SS));
		map.put("desc", "延迟60秒测试");
		rabbitTemplate.convertAndSend(RABBITMQ_QUEUE,  JSON.toJSONString(map, SerializerFeature.WriteMapNullValue), messageObject ->{
			messageObject.getMessageProperties().setDeliveryMode(MessageDeliveryMode.PERSISTENT);  //消息持久化
			messageObject.getMessageProperties().setDelay(60 * 1000);   // 单位为毫秒
			return messageObject;
		});
	}

	public void send3() {
		Map<String, Object> map = new HashMap<>();
		map.put("time", DateUtils.format(new Date(), DateUtils.DATE_PATTERN.YYYY_MM_DD_HH_MM_SS));
		map.put("desc", "延迟60秒测试");
		/*RabbitMQUtil.sendMQ(rabbitTemplate,
				JSON.toJSONString(map, SerializerFeature.WriteMapNullValue),
				"agilecdn-laiw",
				"agw-delayed-exchange",
				60 * 1000L);*/
	}

}
