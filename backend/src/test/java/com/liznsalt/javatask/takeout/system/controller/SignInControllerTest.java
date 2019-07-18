package com.liznsalt.javatask.takeout.system.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SignInControllerTest {

    @Autowired
    private SignInController signInController;

    @Test
    public void addAConsumer() {
        System.out.println(
                this.signInController.addAConsumer("lizn", "haode", "12345678908")
        );
    }

    @Test
    public void addAShop() {
        System.out.println(
                this.signInController.addAShop("kaige", "kaige", "17865453789", "beijings", "18976")
        );
    }
}