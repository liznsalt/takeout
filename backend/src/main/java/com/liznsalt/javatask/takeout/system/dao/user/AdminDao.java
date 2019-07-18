package com.liznsalt.javatask.takeout.system.dao.user;

import com.liznsalt.javatask.takeout.system.model.user.Admin;

import java.util.List;

public interface AdminDao {
    List<Admin> queryAdminByName(String username);
    List<Admin> queryAdminById(Integer id);
    int update(Admin admin);
}
