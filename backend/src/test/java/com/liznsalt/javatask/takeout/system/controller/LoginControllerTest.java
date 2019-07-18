package com.liznsalt.javatask.takeout.system.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class LoginControllerTest {

    @Autowired
    private LoginController loginController;

    @Test
    public void consumerLogin() {
       try {
           System.out.println(
                   this.loginController.consumerLogin("Zhang San", "161432")
           );
       } catch (Exception e) {
           e.printStackTrace();
       }
    }

    @Test
    public void shopLogin() {
        try {
            System.out.println(
                    this.loginController.shopLogin("chaoyin", "xiadaxue")
            );
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void adminLogin() {
        try {
            System.out.println(
                    this.loginController.adminLogin("mai", "123")
            );
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}