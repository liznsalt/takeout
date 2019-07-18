package com.liznsalt.javatask.takeout.system.dao.goods;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderHistoryDaoTest {

    @Autowired
    private OrderHistoryDao orderHistoryDao;


    @Test
    public void queryAllOrderHistoryBySid() {
        System.out.println(this.orderHistoryDao.queryAllOrderHistoryBySid(001));
    }

    @Test
    public void queryAllOrderHistoryByCid() {
        System.out.println(this.orderHistoryDao.queryAllOrderHistoryByCid(001));

    }

    @Test
    public void deleteOrderHistoryByOid() {

        System.out.println(this.orderHistoryDao.deleteOrderHistoryByOid(1));
    }
}