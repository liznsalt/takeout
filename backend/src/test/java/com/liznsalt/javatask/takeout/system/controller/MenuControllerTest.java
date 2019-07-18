package com.liznsalt.javatask.takeout.system.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MenuControllerTest {

    @Autowired
    private ShopController menuController;

    @Test
    public void getAllShopInfo() {
        System.out.println(menuController.getAllShopInfo());
    }

    @Test
    public void getAllGoodsBySid() {
        try {
            System.out.println(menuController.getAllGoodsBySid(1));
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Test
    public void deleteGoodsByGid() {
        try {
            System.out.println(menuController.deleteGoodsByGid(1));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void updateGoods() {
        try {
            System.out.println(
                menuController.updateGoods(3, "炒面", 200, 1, "主食", "好康的，也是好吃的", null)
            );
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Test
    public void addGoods() {
        try {
            System.out.println(
                    menuController.addGoods(1, "千叶豆腐", 22, 20, "主食", "HYT经常吃", null)
            );
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}