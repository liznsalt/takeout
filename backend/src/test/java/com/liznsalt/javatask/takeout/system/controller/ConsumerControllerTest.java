package com.liznsalt.javatask.takeout.system.controller;

import com.liznsalt.javatask.takeout.TakeoutApplication;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.mock.web.MockServletContext;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.Date;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ConsumerControllerTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(ConsumerControllerTest.class);

    private MockMvc mockMvc;
    private MockHttpSession session;

    @Autowired
    private WebApplicationContext wac;

    @Autowired
    private ConsumerController consumerController;

    @Before
    public void setupMockMvc() {
        mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
        session = new MockHttpSession();
//        User user =new User();
//        user.setUsername("Dopa");
//        user.setPasswd("ac3af72d9f95161a502fd326865c2f15");
//        session.setAttribute("user",user);
    }

    @Test
    public void getConsumerInfoByCID() {
        System.out.println(this.consumerController
                .getConsumerInfoByCID(1));
    }

    @Test
    public void getConsumerInfoByName() {
        System.out.println(this.consumerController
                .getConsumerInfoByName("张三"));
    }

    @Test
    public void getOrderHistoryByCID() {
        System.out.println(this.consumerController
                .getOrderHistoryByCID(1));
    }

    @Test
    public void updatePassword() {
        try {
            System.out.println(this.consumerController
                    .updatePassword(1,"12854334","123456"));
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Test
    public void getShoppingCartByCID() {
        System.out.println(this.consumerController
                .getShoppingCartByCID(1));
    }

    @Test
    public void editConsumerInfo() {
        try {
            System.out.println(this.consumerController
                    .editConsumerInfo(1, "mai", "男", 20, "12345678909", "mai@email", "hainan"));

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Test
    public void changePasswordByPhoneCode() {
    }

    @Test
    public void clearShoppingCart() {
    }

    @Test
    public void getShoppingCartByCIDSID() {
    }

    @Test
    public void commentTo() {
        System.out.println(this.consumerController.commentTo(1, 1, "测试测试", 5, new Date()));
    }

    @Test
    public void cancelOrder() {
        System.out.println(this.consumerController.cancelOrder(22));
    }
}