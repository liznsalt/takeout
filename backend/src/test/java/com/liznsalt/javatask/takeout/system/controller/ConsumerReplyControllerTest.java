package com.liznsalt.javatask.takeout.system.controller;

import com.liznsalt.javatask.takeout.TakeoutApplication;
import com.liznsalt.javatask.takeout.system.controller.communication.ConsumerReplyController;
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

import java.util.Date;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {TakeoutApplication.class, MockServletContext.class})
public class ConsumerReplyControllerTest {
    private static final Logger LOGGER = LoggerFactory.getLogger(ConsumerReplyControllerTest.class);

    private MockMvc mockMvc;
    private MockHttpSession session;

    @Autowired
    private WebApplicationContext wac;

    @Autowired
    private ConsumerReplyController consumerReplyController;

    @Before
    public void setupMockMvc() {
        mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
        session = new MockHttpSession();
    }

    @Test
    public void addConsumerReply(){
        try{
            System.out.println(this.consumerReplyController.addConsumerReply(
                    1,2, new Date(),"Good"
            ));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void deleteConsumerReply(){
        try{
            System.out.println(this.consumerReplyController.deleteConsumerReply(
                    1,2,new Date()
            ));
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void getConsumerReplyInfoByCoid(){
        try{
            System.out.println(this.consumerReplyController.getConsumerReplyInfoByCoid(2));
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void getConsumerReplyInfoByCid(){
        try{
            System.out.println(this.consumerReplyController.getConsumerReplyInfoByCid(2));
        }catch (Exception e) {
            e.printStackTrace();
        }
    }
}
