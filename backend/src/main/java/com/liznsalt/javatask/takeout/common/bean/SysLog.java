package com.liznsalt.javatask.takeout.common.bean;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;

@Data
@ToString
public class SysLog implements Serializable {
    private static final long serialVersionUID = 6719491607778976407L;

    private Integer id;
    private String username;
    private String operation;
    private Integer time;
    private String method;
    private String params;
    private String ip;
    private Date createTime;
}
