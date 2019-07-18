package com.liznsalt.javatask.takeout.system.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AdminControllerTest {

    @Autowired
    private AdminController adminController;

    @Test
    public void queryAllConsumers() {
        System.out.println(this.adminController.queryAllConsumers());
    }

    @Test
    public void queryAllShops() {
        System.out.println(this.adminController.queryAllShops());
    }

    @Test
    public void updateConsumerByID() {
        try {
            System.out.println(this.adminController.updateConsumerByID(10, "age", 13));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void updateShopById() {
        try {
            System.out.println(this.adminController.updateShopById(6, "name", "阿伟"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void deleteConsumerByID() {
        try {
            System.out.println(this.adminController.deleteConsumerByID(9));
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Test
    public void deleteShopByID() {
        try {
            System.out.println((this.adminController.deleteShopByID(5)));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}