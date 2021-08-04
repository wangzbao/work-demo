package com.yolo.workdemo.controller.rocketmq;

import org.apache.rocketmq.client.producer.LocalTransactionState;
import org.apache.rocketmq.client.producer.TransactionListener;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.common.message.MessageExt;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

public class TransactionListenerImpl implements TransactionListener {
    private AtomicInteger transactionIndex = new AtomicInteger(0);
    private ConcurrentHashMap<String, Integer> localTrans = new ConcurrentHashMap<>();

    //如果half消息发送成功
    //就会在这里回调你的这个函数，你就可以执行本地事务
    @Override
    public LocalTransactionState executeLocalTransaction(Message msg, Object o) {
        int value = transactionIndex.getAndIncrement();
        int status = value % 3;
        localTrans.put(msg.getTransactionId(), status);

        //执行本地事务
        //接着根据本地遗传事务执行结果，就可以执行本地事务了
        try {
            //如果本地事务都执行成功了，返回commit
            return LocalTransactionState.COMMIT_MESSAGE;
        } catch (Exception e) {
            //本地事务执行失败，回滚所有一切执行过的操作
            //如果本地事务执行失败了，返回rollback，标记half消息无效
            return LocalTransactionState.ROLLBACK_MESSAGE;
        }
    }

    //如果因为各种原因，没有返回commit或者rollback
    @Override
    public LocalTransactionState checkLocalTransaction(MessageExt msg) {
        //查询本地事务，是否执行成功
        Integer status = localTrans.get(msg.getTransactionId());
        if (null != status) {
            switch (status) {
                case 0:
                    return LocalTransactionState.UNKNOW;
                case 1:
                    return LocalTransactionState.COMMIT_MESSAGE;
                case 2:
                    return LocalTransactionState.ROLLBACK_MESSAGE;
            }
        }
        return LocalTransactionState.COMMIT_MESSAGE;
    }
}
