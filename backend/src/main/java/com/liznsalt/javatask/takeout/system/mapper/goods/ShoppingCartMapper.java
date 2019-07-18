package com.liznsalt.javatask.takeout.system.mapper.goods;

import com.liznsalt.javatask.takeout.system.model.goods.ShoppingCart;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ShoppingCartMapper implements RowMapper<ShoppingCart> {

    @Override
    public ShoppingCart mapRow(ResultSet resultSet, int i) throws SQLException {
        ShoppingCart shoppingCart = new ShoppingCart();
        shoppingCart.setCid(resultSet.getInt("cid"));
        shoppingCart.setSid(resultSet.getInt("sid"));
        shoppingCart.setGid(resultSet.getInt("gid"));
        shoppingCart.setShopName(resultSet.getString("shopName"));
        shoppingCart.setGoodsName(resultSet.getString("goodsName"));
        shoppingCart.setPicture(resultSet.getString("picture"));
        shoppingCart.setNum(resultSet.getInt("num"));
        shoppingCart.setPrice(resultSet.getDouble("price"));
        shoppingCart.setTotal(resultSet.getDouble("total"));
        return shoppingCart;
    }
}
