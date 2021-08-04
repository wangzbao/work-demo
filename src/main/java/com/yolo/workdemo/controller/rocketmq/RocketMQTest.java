package com.yolo.workdemo.controller.rocketmq;

import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.TransactionListener;
import org.apache.rocketmq.client.producer.TransactionMQProducer;
import org.apache.rocketmq.client.producer.TransactionSendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.common.RemotingHelper;

import java.io.UnsupportedEncodingException;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class RocketMQTest {
    public static void main(String[] args) throws MQClientException, UnsupportedEncodingException {
        //用来接受rocketmq回调的一个监听器端口
        //这里会实现执行订单本地事务，commit，rollback，回调查询等逻辑
        TransactionListener transactionListener = new TransactionListenerImpl();
        //创建一个支持事务消息的producer
        //对这个producer还得指定一个生产者分组，随便指定名字
        TransactionMQProducer producer = new TransactionMQProducer("TestProducerGroup");
        //指定一个线程池，里面包含一些线程
        //里面的线程就是用来处理mq回调你的请求的
        ThreadPoolExecutor executorService = new ThreadPoolExecutor(
                2,
                5,
                100,
                TimeUnit.SECONDS,
                new ArrayBlockingQueue<>(2000),
                new ThreadFactory() {
                    @Override
                    public Thread newThread(Runnable r) {
                        Thread thread = new Thread(r);
                        thread.setName("TestThread");
                        return thread;
                    }
                }
        );

        //给事务生产者设置对应的线程池，负责执行rocketmq回调请求
        producer.setExecutorService(executorService);
        //给事务生产者设置对应的回调函数
        producer.setTransactionListener(transactionListener);
        //启动这个事务消息生产者
        producer.start();

        //构造一条订单支付成功的消息，指定topic
        Message msg = new Message("PayOrderSuccessTopic", "TestTag", "TestKey", ("订单支付消息").getBytes(RemotingHelper.DEFAULT_CHARSET));
        //讲消息作为half消息的模式发送出去
        try {
            TransactionSendResult sendResult = producer.sendMessageInTransaction(msg, null);
        } catch (MQClientException e) {
            e.printStackTrace();
            //half消息发送失败
            //订单系统执行回滚逻辑，比如触发支付退款，更新订单状态为“已关闭”
        }

    }
}
