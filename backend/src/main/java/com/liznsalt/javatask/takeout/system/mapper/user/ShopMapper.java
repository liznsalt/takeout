package com.liznsalt.javatask.takeout.system.mapper.user;

import com.liznsalt.javatask.takeout.system.model.user.Shop;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ShopMapper implements RowMapper<Shop> {

    @Override
    public Shop mapRow(ResultSet resultSet, int i) throws SQLException {
        Shop shop = new Shop();
        shop.setId(resultSet.getInt("SID"));
        shop.setAddress(resultSet.getString("sAddress"));
        shop.setManagerName(resultSet.getString("managerName"));
        shop.setManagerEmail(resultSet.getString("managerEmail"));
        shop.setPassword(resultSet.getString("sPassword"));
        shop.setTelephone(resultSet.getString("sTel"));
        shop.setUsername(resultSet.getString("sName"));
        shop.setPictureUrl(resultSet.getString("sPictureUrl"));
        shop.setScore(resultSet.getDouble("score"));
        return shop;
    }
}