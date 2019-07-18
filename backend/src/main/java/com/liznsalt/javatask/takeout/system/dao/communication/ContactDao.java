package com.liznsalt.javatask.takeout.system.dao.communication;

import com.liznsalt.javatask.takeout.system.model.communication.Contact;

import java.util.List;

public interface ContactDao { // 本系统不允许撤回消息
    int add(Contact contact); // 添加一条对话
    List<Contact> queryContactBySIDCID(Integer SID, Integer CID);

    List<Contact> queryContactBySID(Integer SID);
    List<Contact> queryContactByCID(Integer CID);
}
