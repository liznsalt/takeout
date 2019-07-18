package com.liznsalt.javatask.takeout.system.service.user;

import com.liznsalt.javatask.takeout.system.model.user.Admin;

import java.util.List;

public interface AdminService {
    Admin queryAdminByName(String username);

    Admin queryAdminById(Integer id);
    int update(Admin admin);
}
