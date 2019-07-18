package com.liznsalt.javatask.takeout.system.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ShopInfoControllerTest {

    @Autowired
    private ShopInfoController shopInfoController;

    @Test
    public void getShopInfoBySid() {
        System.out.println(
                this.shopInfoController.getShopInfoBySid(1)
        );
    }

    @Test
    public void getOrderHistoryBySid() {
        System.out.println(this.shopInfoController.getOrderHistoryBySid(1));
    }

    @Test
    public void shopUpdatePassword() {
        try {
            System.out.println(this.shopInfoController.shopUpdatePassword(1, "4452133", "123"));
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Test
    public void shopEditInfo() {
        try {
            System.out.println(
                    this.shopInfoController.shopEditInfo(
                            1,
                            "mai",
                            "12345678909",
                            "haiyun",
                            "mai",
                            "mai@email"
                    )
            );
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}