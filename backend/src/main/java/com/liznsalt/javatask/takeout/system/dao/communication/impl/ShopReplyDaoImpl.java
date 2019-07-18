package com.liznsalt.javatask.takeout.system.dao.communication.impl;

import com.liznsalt.javatask.takeout.system.dao.communication.ShopReplyDao;
import com.liznsalt.javatask.takeout.system.mapper.communication.ShopReplyMapper;
import com.liznsalt.javatask.takeout.system.model.communication.ShopReply;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.Types;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Repository("shopReplyDao")
public class ShopReplyDaoImpl implements ShopReplyDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public int add(ShopReply shopReply) {
        String sql = "insert into shopreply(sid, coid, cotime, content) " +
                "values (?, ?, ?, ?)";
        Object[] args = {
                shopReply.getSid(),
                shopReply.getCoid(),
                new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(shopReply.getTime()),
                shopReply.getContent()
        };
        int[] argTypes = {
                Types.INTEGER,
                Types.INTEGER,
                Types.VARCHAR,
                Types.VARCHAR
        };
        return this.jdbcTemplate.update(sql, args, argTypes);
    }

    @Override
    public int delete(Integer sid, Integer coid, Date date) {
        String sql = "delete from shopreply where SID = ? and  CoID = ? " +
                "and CoTime = ?";
        Object[] args = {
                sid,
                coid,
                new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date)
        };
        int[] argTypes = {
                Types.INTEGER,
                Types.INTEGER,
                Types.VARCHAR
        };
        return this.jdbcTemplate.update(sql, args, argTypes);
    }

    @Override
    public int update(ShopReply oldReply, ShopReply newReply) {
        String sql = "update shopreply set SID = ?, CoID = ?, CoTime = ?, " +
                "content = ? where SID = ? and CoID = ? and CoTime = ?";
        Object[] args = {
                newReply.getSid(),
                newReply.getCoid(),
                new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(newReply.getTime()),
                newReply.getContent(),
                oldReply.getSid(),
                oldReply.getCoid(),
                new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(oldReply.getTime())
        };
        int[] argTypes = {
                Types.INTEGER,
                Types.INTEGER,
                Types.VARCHAR,
                Types.VARCHAR,
                Types.INTEGER,
                Types.INTEGER,
                Types.VARCHAR
        };
        return this.jdbcTemplate.update(sql, args, argTypes);
    }

    @Override
    public List<ShopReply> queryShopReplyByCoID(Integer CoID) {
        String sql = "select * from shopreply_view where coid = ? order by cotime desc ";
        Object[] args = {CoID};
        int[] argTypes = {Types.INTEGER};
        List<ShopReply> shopReplyList = this.jdbcTemplate.query(sql, args, argTypes,
                new ShopReplyMapper());
        if(shopReplyList != null && shopReplyList.size() > 0){
            return  shopReplyList;
        }
        else {
            return null;
        }
    }

    @Override
    public List<ShopReply> queryShopReplyBySID(Integer SID) {
        String sql = "select * from shopreply_view where sid = ? order by cotime desc ";
        Object[] args = {SID};
        int[] argTypes = {Types.INTEGER};
        List<ShopReply> shopReplyList = this.jdbcTemplate.query(sql, args, argTypes,
                new ShopReplyMapper());
        if(shopReplyList != null && shopReplyList.size() > 0){
            return  shopReplyList;
        }
        else {
            return null;
        }
    }
}
