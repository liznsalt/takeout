package com.liznsalt.javatask.takeout.system.service.communication.impl;

import com.liznsalt.javatask.takeout.system.dao.communication.ContactDao;
import com.liznsalt.javatask.takeout.system.model.communication.Contact;
import com.liznsalt.javatask.takeout.system.service.communication.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("contactService")
public class ContactServiceImpl implements ContactService {

    @Autowired
    private ContactDao contactDao;

    @Override
    public int add(Contact contact) {
        return this.contactDao.add(contact);
    }

    @Override
    public List<Contact> queryContactBySIDCID(Integer SID, Integer CID) {
        return this.contactDao.queryContactBySIDCID(SID, CID);
    }

    @Override
    public List<Contact> queryContactBySID(Integer SID) {
        return this.contactDao.queryContactBySID(SID);
    }

    @Override
    public List<Contact> queryContactByCID(Integer CID) {
        return this.contactDao.queryContactByCID(CID);
    }
}
