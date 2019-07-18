package com.liznsalt.javatask.takeout.system.service.user.impl;

import com.liznsalt.javatask.takeout.system.dao.user.AdminDao;
import com.liznsalt.javatask.takeout.system.model.user.Admin;
import com.liznsalt.javatask.takeout.system.service.user.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("adminService")
public class AdminServiceImpl implements AdminService {

    @Autowired
    private AdminDao adminDao;

    @Override
    public Admin queryAdminByName(String username) {
        if (this.adminDao.queryAdminByName(username) == null) {
            return null;
        }
        return this.adminDao.queryAdminByName(username).get(0);
    }

    @Override
    public Admin queryAdminById(Integer id) {
        if (this.adminDao.queryAdminById(id) == null) {
            return null;
        }
        return this.adminDao.queryAdminById(id).get(0);
    }

    @Override
    public int update(Admin admin) {
        return this.adminDao.update(admin);
    }
}
