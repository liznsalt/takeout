package com.liznsalt.javatask.takeout;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class MyDisposableBean implements DisposableBean {

    @Override
    public void destroy() throws Exception {
        System.out.println("end");
    }
}
