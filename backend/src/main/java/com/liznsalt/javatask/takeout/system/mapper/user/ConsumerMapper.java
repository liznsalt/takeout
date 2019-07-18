package com.liznsalt.javatask.takeout.system.mapper.user;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.liznsalt.javatask.takeout.system.model.user.Consumer;

public class ConsumerMapper implements RowMapper<Consumer> {
    @Override
    public Consumer mapRow(ResultSet rs, int rowNum) throws SQLException {
        Consumer consumer = new Consumer();
        consumer.setId(rs.getInt("cid"));
        consumer.setUsername(rs.getString("cname"));
        consumer.setSex(rs.getString("cSex"));
        consumer.setAge(rs.getInt("cage"));
        consumer.setTelephone(rs.getString("cTel"));
        consumer.setEmail(rs.getString("cEmail"));
        consumer.setAddress(rs.getString("cAddress"));
        consumer.setPassword(rs.getString("cPassword"));
        consumer.setBalance(rs.getDouble("cBalance"));
        return consumer;
    }

}