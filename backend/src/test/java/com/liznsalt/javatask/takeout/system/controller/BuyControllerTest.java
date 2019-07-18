package com.liznsalt.javatask.takeout.system.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BuyControllerTest {

    @Autowired
    private BuyController buyController;

    @Test
    public void addShoppingCart() {
        try {
            System.out.println(buyController.addShoppingCart(1, 5));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void numAdd() {
        try {
           System.out.println(buyController.numAdd(1, 5));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void numSub() {
        try {
            System.out.println(buyController.numSub(1, 3));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void deleteItem() {
        try {
           System.out.println(buyController.deleteItem(1, 3));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void deleteAllItem() {
        try {
            System.out.println(buyController.deleteAllItem(1));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void confirmItem() {
        try {
            System.out.println(buyController.confirmItem(1, 5));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void confirmItemWithDate() throws Exception {
        System.out.println(buyController.confirmItemWithDate(1, 3, new Date()));
    }
}