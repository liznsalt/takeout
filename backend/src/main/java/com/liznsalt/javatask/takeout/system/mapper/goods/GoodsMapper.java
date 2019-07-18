package com.liznsalt.javatask.takeout.system.mapper.goods;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.liznsalt.javatask.takeout.system.model.goods.Goods;

public class GoodsMapper implements RowMapper<Goods>{

    @Override
    public Goods mapRow(ResultSet rs, int i) throws SQLException {
        Goods goods = new Goods();
        goods.setId(rs.getInt("gId"));
        goods.setName(rs.getString("gName"));
        goods.setPrice(rs.getDouble("gPrice"));
        goods.setStock(rs.getInt("stock"));
        goods.setType(rs.getString("type"));
        goods.setBuyCount(rs.getInt("buyCount"));
        goods.setPictureUrl(rs.getString("gPictureUrl"));
        goods.setPraise(rs.getDouble("gPraise"));
        goods.setSid(rs.getInt("sId"));
        goods.setDesc(rs.getString("desc"));
        return goods;
    }
}
