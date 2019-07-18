package com.liznsalt.javatask.takeout.common.controller;

import com.liznsalt.javatask.takeout.common.dao.SysLogDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
public class LogController {

    @Autowired
    private SysLogDao sysLogDao;

    @RequestMapping("/log/all")
    @ResponseBody
    public List<Map<String, Object>> getAllLogs() {
        return this.sysLogDao.queryAllLogs();
    }
}
