package com.yolo.workdemo.controller.rocketmq;

import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.common.message.MessageExt;

import java.util.List;

/**
 * 延迟队列消费者定时扫描
 */
public class DelayConsumer {
    public static void main(String[] args) throws MQClientException {
        //订单服务的消费者
        DefaultMQPushConsumer consumer = new DefaultMQPushConsumer("OrderScanServiceConsumer");
        //订阅订单创建通知topic
        consumer.subscribe("CreateOrderInfomTopic", "*");
        //注册消息监听者
        consumer.registerMessageListener(new MessageListenerConcurrently() {
            @Override
            public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> msgs, ConsumeConcurrentlyContext context) {
                for (MessageExt message : msgs) {
                    //打印一下消息的存储时间到消费时间的差值
                    //大概就是我们设置的延迟级别的时间
                    System.out.println("receive message[msgId= " + message.getMsgId() + "]"
                            + (System.currentTimeMillis() - message.getStoreTimestamp()) + "ms later");
                }
                return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
            }
        });
        //启动消费者
        consumer.start();
    }
}
