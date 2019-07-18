package com.liznsalt.javatask.takeout.system.dao.user;

import com.liznsalt.javatask.takeout.system.model.user.Consumer;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Map;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ConsumerDaoTest {

    @Autowired
    private ConsumerDao consumerDao;

    @Test
    public void add() {
        Consumer consumer = new Consumer();
//        consumer.setId(123);
        consumer.setUsername("ajfakjf");
        consumer.setPassword("123456");
        System.out.println(this.consumerDao.add(consumer));
    }

    @Test
    public void update() {
        Consumer consumer = new Consumer();
        consumer.setId(1);
        consumer.setUsername("Zhang San");
        consumer.setSex("男");
        consumer.setAge(19);
        consumer.setTelephone("16845454545");
        consumer.setAddress("haiyun");
        consumer.setPassword("161432");
        consumer.setBalance(2.34);
        System.out.println(this.consumerDao.update(consumer));
    }

    @Test
    public void queryAllConsumer() {
        this.consumerDao.queryAllConsumer()
                .forEach((x)-> {
                    x.entrySet().forEach((y)->{
                        System.out.println(y.getKey()+":"+y.getValue());
                    });
                });
    }

    @Test
    public void queryConsumerById() {
        Integer cid = 1;
        System.out.println(this.consumerDao.queryConsumerById(cid));
    }

    @Test
    public void queryConsumerByName() {
        String cname = "Zhang San";
        System.out.println(this.consumerDao.queryConsumerByName(cname));
    }

    @Test
    public void deleteById() {
        Integer cid=7;
        System.out.println(this.consumerDao.deleteById(cid));
    }
}