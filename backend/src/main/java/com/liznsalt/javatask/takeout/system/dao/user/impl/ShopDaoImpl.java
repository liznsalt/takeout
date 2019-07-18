package com.liznsalt.javatask.takeout.system.dao.user.impl;

import com.liznsalt.javatask.takeout.system.dao.user.ShopDao;
import com.liznsalt.javatask.takeout.system.mapper.user.ShopMapper;
import com.liznsalt.javatask.takeout.system.model.user.Shop;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.Types;
import java.util.List;
import java.util.Map;

@Repository("shopDao")
public class ShopDaoImpl implements ShopDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public int add(Shop shop) {
        String sql = "insert into shop(sname, sPassword, sTel, sAddress," +
                "managerName, managerEmail, sPictureUrl, score) values (?,?,?,?,?,?,?,?)";
        Object[] args = {
                shop.getUsername(),
                shop.getPassword(),
                shop.getTelephone(),
                shop.getAddress(),
                shop.getManagerName(),
                shop.getManagerEmail(),
                shop.getPictureUrl(),
                shop.getScore()
        };
        int[] argTypes = {
                Types.VARCHAR,
                Types.VARCHAR,
                Types.CHAR,
                Types.VARCHAR,
                Types.VARCHAR,
                Types.VARCHAR,
                Types.VARCHAR,
                Types.DECIMAL
        };
        return this.jdbcTemplate.update(sql,args,argTypes);
    }

    @Override
    public int update(Shop shop) {
        String sql = "update shop set sName=?, sPassword=?, sTel=?, " +
                "sAddress=?, managerName=?, managerEmail=?, sPictureUrl=?, score=? where sId=?";
        Object[] args = {
                shop.getUsername(),
                shop.getPassword(),
                shop.getTelephone(),
                shop.getAddress(),
                shop.getManagerName(),
                shop.getManagerEmail(),
                shop.getPictureUrl(),
                shop.getScore(),
                shop.getId()
        };
        int[] argTypes = {
                Types.VARCHAR,
                Types.VARCHAR,
                Types.CHAR,
                Types.VARCHAR,
                Types.VARCHAR,
                Types.VARCHAR,
                Types.VARCHAR,
                Types.DECIMAL,
                Types.INTEGER
        };
        return this.jdbcTemplate.update(sql, args, argTypes);
    }

    @Override
    public int deleteById(Integer sid) {
        String sql = "delete from shop where sid = ?";
        Object[] args = { sid };
        int[] argTypes = {Types.INTEGER};
        return this.jdbcTemplate.update(sql, args, argTypes);
    }

    @Override
    public List<Map<String, Object>> queryAllShops() {
        String sql = "select * from shop";
        return this.jdbcTemplate.queryForList(sql);
    }

    @Override
    public Shop queryShopById(Integer sid) {
        String sql = "select * from shop where sid = ?";
        Object[] args = { sid };
        int[] argTypes = { Types.INTEGER };
        List<Shop> shopList = this.jdbcTemplate.query(sql, args, argTypes, new ShopMapper());
        if (shopList != null && shopList.size() > 0) {
            return shopList.get(0);
        } else {
            return null;
        }
    }

    @Override
    public List<Shop> queryShopByName(String sname) {
        String sql = "select * from shop where sname = ?";
        Object [] args = { sname };
        int[] argTypes = {Types.VARCHAR};
        List<Shop> shopList = this.jdbcTemplate.query(sql, args, argTypes, new ShopMapper());
        if(shopList != null && shopList.size() > 0) {
            return shopList;
        }else {
            return null;
        }
    }

    @Override
    public Shop queryShopByPhone(String phone) {
        String sql = "select * from shop where sTel = ?";
        Object [] args = { phone };
        int[] argTypes = {Types.CHAR};
        List<Shop> shopList = this.jdbcTemplate.query(sql, args, argTypes, new ShopMapper());
        if(shopList != null && shopList.size() > 0) {
            return shopList.get(0);
        }else {
            return null;
        }
    }
}
