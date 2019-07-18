package com.liznsalt.javatask.takeout.common.dao;

import com.liznsalt.javatask.takeout.common.bean.SysLog;

import java.util.List;
import java.util.Map;

public interface SysLogDao {
    void saveSysLog(SysLog sysLog);
    List<Map<String, Object>> queryAllLogs();
}
