package com.liznsalt.javatask.takeout.system.controller;

import com.liznsalt.javatask.takeout.TakeoutApplication;
import com.liznsalt.javatask.takeout.common.bean.SysLog;
import com.liznsalt.javatask.takeout.system.controller.communication.ContactController;
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
public class ContactControllerTest {
    private static final Logger LOGGER = LoggerFactory.getLogger(ContactControllerTest.class);

    private MockMvc mockMvc;
    private MockHttpSession session;

    @Autowired
    private WebApplicationContext wac;

    @Autowired
    private ContactController contactController;

    @Before
    public void setupMockMvc() {
        mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
        session = new MockHttpSession();
    }

    @Test
    public void getContactInfoBySidCid(){
        System.out.println(this.contactController.getContactInfoBySidCid(1,1));
    }

    @Test
    public void addContact(){
        try{
            System.out.println(this.contactController.addContact(1,1,
                    new Date(),"1","Hello world"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void getContactInfoBySid() throws Exception {
        System.out.println(this.contactController.getContactInfoBySid(1));
    }

    @Test
    public void getContactInfoByCid() throws Exception {
        System.out.println(this.contactController.getContactInfoByCid(2));
    }
}
