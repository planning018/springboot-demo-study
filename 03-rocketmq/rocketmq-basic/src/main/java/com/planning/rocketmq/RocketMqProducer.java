package com.planning.rocketmq;

import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.common.RemotingHelper;

/**
 * @author planning
 * @since 2020-04-06 11:24
 **/
public class RocketMqProducer {

    public static void main(String[] args) throws MQClientException, InterruptedException {

        /*
         * instantiate with a producer group name
         */
        DefaultMQProducer producer = new DefaultMQProducer("hello-rocketMQ");

        producer.start();

        for (int i = 0; i < 1000; i++) {
            try {
                Message msg = new Message("hello-topic","TagA",
                        (("Hello RocketMQ" + i)).getBytes(RemotingHelper.DEFAULT_CHARSET));

                /*
                 * Call send message to deliver message to one of brokers.
                 */
                SendResult sendResult = producer.send(msg);

                System.out.printf("%s%n", sendResult);
            } catch (Exception e) {
                e.printStackTrace();
                Thread.sleep(1000);
            }
        }

        /*
         * shut down once the producer instance is not longer in use.
         */
        producer.shutdown();
    }

}