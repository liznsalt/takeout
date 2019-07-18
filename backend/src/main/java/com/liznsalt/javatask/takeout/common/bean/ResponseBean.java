package com.liznsalt.javatask.takeout.common.bean;

import java.io.Serializable;
import java.util.HashMap;

public class ResponseBean extends HashMap<String, Object> {

    private static final long serialVersionUID = 5240616507873561747L;

    public ResponseBean message(String msg) {
        this.put("msg", msg);
        return this;
    }

    public ResponseBean data(Object d) {
        this.put("data", d);
        return this;
    }

    public ResponseBean code(int c) {
        this.put("code", c);
        return this;
    }

    @Override
    public ResponseBean put(String key, Object value) {
        super.put(key, value);
        return this;
    }

//    // http 状态码
//    private int code;
//
//    // 返回信息
//    private String msg;
//
//    // 返回的数据
//    private Object data;
//
//    public ResponseBean(int code, String msg, Object data) {
//        this.code = code;
//        this.msg = msg;
//        this.data = data;
//    }
//
//    public int getCode() {
//        return code;
//    }
//
//    public void setCode(int code) {
//        this.code = code;
//    }
//
//    public String getMsg() {
//        return msg;
//    }
//
//    public void setMsg(String msg) {
//        this.msg = msg;
//    }
//
//    public Object getData() {
//        return data;
//    }
//
//    public void setData(Object data) {
//        this.data = data;
//    }
}
