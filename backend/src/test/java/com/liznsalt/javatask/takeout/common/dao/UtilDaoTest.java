package com.liznsalt.javatask.takeout.common.dao;

import com.liznsalt.javatask.takeout.system.controller.ShopController;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UtilDaoTest {

    @Autowired
    private UtilDao util;

    @Autowired
    private ShopController shopController;

    @Test
    public void getLastId() {
        try {
            System.out.println(this.shopController.addGoods(
                    1,
                    "加滑稽",
                    12,
                    13,
                    "小吃",
                    "好吃的",
                    null
            ));
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(util.getLastId());
    }
}