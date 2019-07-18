package com.liznsalt.javatask.takeout.system.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ShopControllerTest {

    @Autowired
    private ShopController shopController;

    @Test
    public void getAllShopInfo() {
    }

    @Test
    public void getAllGoodsBySid() {
    }

    @Test
    public void deleteGoodsByGid() {
    }

    @Test
    public void updateGoods() {
    }

    @Test
    public void addGoods() {
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

    }

    @Test
    public void confirmOrder() throws Exception {
        System.out.println(this.shopController.confirmOrder(21));
    }
}