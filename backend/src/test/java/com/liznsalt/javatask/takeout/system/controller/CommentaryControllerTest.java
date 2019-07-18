package com.liznsalt.javatask.takeout.system.controller;

import com.liznsalt.javatask.takeout.TakeoutApplication;
import com.liznsalt.javatask.takeout.system.controller.communication.CommentaryController;
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
public class CommentaryControllerTest {
    private static final Logger LOGGER = LoggerFactory.getLogger(CommentaryControllerTest.class);

    private MockMvc mockMvc;
    private MockHttpSession session;

    @Autowired
    private WebApplicationContext wac;

    @Autowired
    private CommentaryController commentaryController;

    @Before
    public void setupMockMvc() {
        mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
        session = new MockHttpSession();
    }

    @Test
    public void getCommentaryInfoByCoid(){
        System.out.println(this.commentaryController.getCommentaryInfoByCoid(1));
    }

    @Test
    public void getCommentaryInfoBySid(){
        System.out.println(this.commentaryController.getCommentaryInfoBySid(1));
    }

    @Test
    public void deleteCommentaryByCoid(){
        try{
            System.out.println(this.commentaryController.deleteCommentaryByCoid(1));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void addCommentary(){
        try{
            System.out.println(this.commentaryController.addCommentary(
                    2,2,new Date(),"dada",5));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void updateCommentary(){
        try{
            System.out.println(this.commentaryController.updateCommentary(
               2,new Date(),"Just so so",3
            ));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
