package com.planning.rocketmq.consumer;

import com.planning.rocketmq.message.Demo01Message;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.common.message.MessageExt;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.stereotype.Component;

/**
 * @author planning
 * @date 2020/5/26 8:06
 */
@Component
@RocketMQMessageListener(
        topic = Demo01Message.TOPIC,
        consumerGroup = "demo01-consumer-group-" + Demo01Message.TOPIC
)
@Slf4j
public class Demo01Consumer implements RocketMQListener<MessageExt> {

    @Override
    public void onMessage(MessageExt message) {
        log.info("[Demo01Consumer onMessage] [线程编号：{} 消息内容: {}]", Thread.currentThread().getId(), message);
    }
}
