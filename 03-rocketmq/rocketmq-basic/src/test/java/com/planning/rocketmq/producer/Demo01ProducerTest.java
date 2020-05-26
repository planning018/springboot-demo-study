package com.planning.rocketmq.producer;

import com.planning.rocketmq.RocketMqBasicApplication;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.producer.SendResult;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.concurrent.CountDownLatch;

/**
 * @author planning
 * @date 2020/5/26 8:13
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = RocketMqBasicApplication.class)
@Slf4j
public class Demo01ProducerTest {

    @Autowired
    private Demo01Producer producer;

    @Test
    public void testSyncSend() throws InterruptedException {
        int id = (int)(System.currentTimeMillis() / 1000);
        SendResult sendResult = producer.syncSend(id);
        log.info("[Demo01ProducerTest testSyncSend][发送编号：[{}] 发送结果：[{}]]", id, sendResult);

        //阻塞等待，保证消费
        new CountDownLatch(10).await();
    }
}
