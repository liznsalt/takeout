package com.liznsalt.javatask.takeout.system.service.user.impl;

import com.liznsalt.javatask.takeout.system.dao.user.ConsumerDao;
import com.liznsalt.javatask.takeout.system.model.user.Consumer;
import com.liznsalt.javatask.takeout.system.service.user.ConsumerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service("consumerService")
public class ConsumerServiceImpl implements ConsumerService {

    @Autowired
    private ConsumerDao consumerDao;

    @Override
    public int add(Consumer consumer) {
        return this.consumerDao.add(consumer);
    }

    @Override
    public int update(Consumer consumer) {
        return this.consumerDao.update(consumer);
    }

    @Override
    public int deleteById(Integer cid) {
        return this.consumerDao.deleteById(cid);
    }

    @Override
    public List<Map<String, Object>> queryAllConsumer() {
        return this.consumerDao.queryAllConsumer();
    }

    @Override
    public Consumer queryConsumerById(Integer cid) {
        return this.consumerDao.queryConsumerById(cid);
    }

    @Override
    public Consumer queryConsumerByName(String cname) {
        if (this.consumerDao.queryConsumerByName(cname) == null) {
            return null;
        }
        return this.consumerDao.queryConsumerByName(cname).get(0);
    }

    @Override
    public Consumer queryConsumerByPhone(String phone) {
        return this.consumerDao.queryConsumerByPhone(phone);
    }
}
