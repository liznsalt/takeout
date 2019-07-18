package com.liznsalt.javatask.takeout.cache;

import com.liznsalt.javatask.takeout.TakeoutApplication;
import com.liznsalt.javatask.takeout.system.model.user.Consumer;
import com.liznsalt.javatask.takeout.system.service.user.ConsumerService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = TakeoutApplication.class)
public class CacheTest {

    @Autowired
    private ConsumerService consumerService;

    @Test
    public void test() throws Exception {
        Consumer consumer1 = this.consumerService.queryConsumerById(1);
        System.out.println(consumer1);

        Consumer consumer2 = this.consumerService.queryConsumerById(1);
        System.out.println(consumer2);
    }
}
