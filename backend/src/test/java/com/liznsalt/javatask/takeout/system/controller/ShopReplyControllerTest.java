package com.liznsalt.javatask.takeout.system.controller;

import com.liznsalt.javatask.takeout.TakeoutApplication;
import com.liznsalt.javatask.takeout.system.controller.communication.ShopReplyController;
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
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.text.SimpleDateFormat;
import java.util.Date;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {TakeoutApplication.class, MockServletContext.class})
public class ShopReplyControllerTest {
    private static final Logger LOGGER = LoggerFactory.getLogger(ShopReplyControllerTest.class);

    private MockMvc mockMvc;
    private MockHttpSession session;

    @Autowired
    private WebApplicationContext wac;

    @Autowired
    private ShopReplyController shopReplyController;

    @Before
    public void setupMockMvc() {
        mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
        session = new MockHttpSession();
    }

    @Test
    public void addShopReply(){
        try{
            System.out.println(this.shopReplyController.addShopReply(
                    1,3,new Date(),"Thank you"
            ));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void deleteShopReply(){
        SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try{
            System.out.println(this.shopReplyController.deleteShopReply(
                    1,2, fmt.parse("2019-05-12 19:53:17")
            ));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void getShopReplyInfoByCoid(){
        try{
            System.out.println(this.shopReplyController.getShopReplyInfoByCoid(3));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void getShopReplyInfoBySid(){
        try{
            System.out.println(this.shopReplyController.getShopReplyInfoBySid(1));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
