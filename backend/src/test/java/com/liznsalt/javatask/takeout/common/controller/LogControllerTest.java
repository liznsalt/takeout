package com.liznsalt.javatask.takeout.common.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class LogControllerTest {

    @Autowired
    private LogController logController;

    @Test
    public void getAllLogs() {
        System.out.println(logController.getAllLogs());
    }
}