package com.liznsalt.javatask.takeout.system.mapper.user;

import com.liznsalt.javatask.takeout.system.model.user.Admin;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class AdminMapper implements RowMapper<Admin> {

    @Override
    public Admin mapRow(ResultSet resultSet, int i) throws SQLException {
        Admin admin = new Admin();
        admin.setId(resultSet.getInt("aid"));
        admin.setUsername(resultSet.getString("aname"));
        admin.setPassword(resultSet.getString("apassword"));
        return admin;
    }
}
