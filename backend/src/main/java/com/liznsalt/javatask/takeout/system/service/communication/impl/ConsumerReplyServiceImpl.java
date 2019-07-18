package com.liznsalt.javatask.takeout.system.service.communication.impl;

import com.liznsalt.javatask.takeout.system.dao.communication.ConsumerReplyDao;
import com.liznsalt.javatask.takeout.system.model.communication.ConsumerReply;
import com.liznsalt.javatask.takeout.system.service.communication.ConsumerReplyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service("consumerReplyService")
public class ConsumerReplyServiceImpl implements ConsumerReplyService {

    @Autowired
    private ConsumerReplyDao consumerReplyDao;

    @Override
    public int add(ConsumerReply consumerReply) {
        return this.consumerReplyDao.add(consumerReply);
    }

    @Override
    public int delete(Integer cid, Integer coid, Date date) {
        return this.consumerReplyDao.delete(cid, coid, date);
    }

    @Override
    public int update(ConsumerReply oldReply, ConsumerReply newReply) {
        return this.consumerReplyDao.update(oldReply, newReply);
    }

    @Override
    public List<ConsumerReply> queryConsumerReplyByCID(Integer CID) {
        return this.consumerReplyDao.queryConsumerReplyByCID(CID);
    }

    @Override
    public List<ConsumerReply> queryConsumerReplyByCoID(Integer CoID) {
        return this.consumerReplyDao.queryConsumerReplyByCoID(CoID);
    }
}
