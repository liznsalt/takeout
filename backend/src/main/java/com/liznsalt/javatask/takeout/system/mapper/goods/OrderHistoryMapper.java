package com.liznsalt.javatask.takeout.system.mapper.goods;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.liznsalt.javatask.takeout.common.utils.DateUtil;
import com.liznsalt.javatask.takeout.system.model.goods.Goods;
import jdk.nashorn.internal.objects.NativeString;
import org.springframework.jdbc.core.RowMapper;

import com.liznsalt.javatask.takeout.system.model.goods.OrderHistory;

public class OrderHistoryMapper implements RowMapper<OrderHistory> {

    @Override
    public OrderHistory mapRow(ResultSet resultSet, int i) throws SQLException {
        OrderHistory orderHistory = new OrderHistory();
        orderHistory.setSid(resultSet.getInt("sid"));
        orderHistory.setId(resultSet.getInt("id"));
        orderHistory.setCid(resultSet.getInt("cid"));
        orderHistory.setGid(resultSet.getInt("gid"));
        orderHistory.setShopName(resultSet.getString("shopName"));
        orderHistory.setGoodsName(resultSet.getString("goodsName"));
        orderHistory.setTime(DateUtil.formatTimeStamp(resultSet.getTimestamp("buyTime")));
        orderHistory.setPicture(resultSet.getString("picture"));
        orderHistory.setNum(resultSet.getInt("num"));
        orderHistory.setPrice(resultSet.getDouble("price"));
        orderHistory.setTotal(resultSet.getDouble("total"));
        orderHistory.setState(resultSet.getString("state"));
        orderHistory.setConsumerName(resultSet.getString("consumerName"));
        return orderHistory;
    }
}

