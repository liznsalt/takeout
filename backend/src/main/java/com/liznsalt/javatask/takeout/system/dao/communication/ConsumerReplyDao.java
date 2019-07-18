package com.liznsalt.javatask.takeout.system.dao.communication;

import com.liznsalt.javatask.takeout.system.model.communication.ConsumerReply;

import java.util.Date;
import java.util.List;

public interface ConsumerReplyDao {
    int add(ConsumerReply consumerReply); // 添加回复
    int delete(Integer cid, Integer coid, Date date); // 通过消费者ID，评论ID和评论时间删除回复
    // 因为回复没有主码ID，所以需要知道之前的回复
    int update(ConsumerReply oldReply, ConsumerReply newReply);
    List<ConsumerReply> queryConsumerReplyByCID(Integer CID);
    List<ConsumerReply> queryConsumerReplyByCoID(Integer CoID);
}
