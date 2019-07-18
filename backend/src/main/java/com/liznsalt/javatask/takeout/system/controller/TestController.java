package com.liznsalt.javatask.takeout.system.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
public class TestController {

    @Autowired
    private JdbcTemplate jdbcTemplate;

//    @RequestMapping("/update")
    @ResponseBody
    public int update(String sql) {
        return this.jdbcTemplate.update(sql);
    }

//    @RequestMapping("/query")
    @ResponseBody
    public List<Map<String, Object>> query(String sql) {
        return this.jdbcTemplate.queryForList(sql);
    }

}
