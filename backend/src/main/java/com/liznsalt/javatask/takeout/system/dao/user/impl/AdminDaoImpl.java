package com.liznsalt.javatask.takeout.system.dao.user.impl;

import com.liznsalt.javatask.takeout.system.dao.user.AdminDao;
import com.liznsalt.javatask.takeout.system.mapper.user.AdminMapper;
import com.liznsalt.javatask.takeout.system.model.user.Admin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.Types;
import java.util.List;

@Repository("adminDao")
public class AdminDaoImpl implements AdminDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<Admin> queryAdminByName(String username) {
        String sql = "select * from admin where aname = ?";
        Object [] args = { username };
        int[] argTypes = {Types.VARCHAR};
        List<Admin> adminList = jdbcTemplate.query(sql, args, argTypes, new AdminMapper());
        if(adminList != null && adminList.size() > 0) {
            return adminList;
        }else {
            return null;
        }
    }

    @Override
    public List<Admin> queryAdminById(Integer id) {
        String sql = "select * from admin where aId = ?";
        Object [] args = { id };
        int[] argTypes = {Types.INTEGER};
        List<Admin> adminList = jdbcTemplate.query(sql, args, argTypes, new AdminMapper());
        if(adminList != null && adminList.size() > 0) {
            return adminList;
        }else {
            return null;
        }
    }

    @Override
    public int update(Admin admin) {
        String sql = "update admin set aName=?, aPassword=? where aId = ?";
        Object[] args = {admin.getUsername(), admin.getPassword(), admin.getId()};
        int[] argTypes= {Types.VARCHAR, Types.VARCHAR, Types.INTEGER};
        return this.jdbcTemplate.update(sql, args, argTypes);
    }
}
