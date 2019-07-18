package com.liznsalt.javatask.takeout.system.dao.goods.impl;

import com.liznsalt.javatask.takeout.system.dao.goods.OrderHistoryDao;
import com.liznsalt.javatask.takeout.system.mapper.goods.OrderHistoryMapper;
import com.liznsalt.javatask.takeout.system.model.goods.OrderHistory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.Types;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;

@Repository("orderHistoryDao")
public class OrderHistoryDaoImpl implements OrderHistoryDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<OrderHistory> queryAllOrderHistoryBySid(Integer sid) {
        String sql = "select * from orderhistory_view where sid = ? order by buyTime desc ";
        Object[] args = { sid };
        int[] argTypes = { Types.INTEGER };
        List<OrderHistory> orderHistoryList=this.jdbcTemplate.query(sql, args, argTypes, new OrderHistoryMapper());
        if (orderHistoryList !=null && orderHistoryList.size()>0) {
            return orderHistoryList;
        }
        else
            return null;
    }

    @Override
    public List<OrderHistory> queryAllOrderHistoryByCid(Integer cid) {
        String sql = "select * from orderhistory_view where cid = ? order by buyTime desc ";
        Object[] args = { cid };
        int[] argTypes = { Types.INTEGER };
        List<OrderHistory> orderHistoryList=this.jdbcTemplate.query(sql, args, argTypes, new OrderHistoryMapper());
        if (orderHistoryList !=null && orderHistoryList.size()>0) {
            return orderHistoryList;
        }
        else
            return null;
    }

    @Override
    public int deleteOrderHistoryByOid(int oid) {
        String sql="delete from orderhistory where oId = ?";
        Object[] args = { oid };
        int[] argTypes = {Types.INTEGER};
        return this.jdbcTemplate.update(sql, args, argTypes);
    }

    @Override
    public int add(OrderHistory orderHistory) {
        String sql = "insert into orderhistory(oState, oTime, oNum, cost, CID, GID) " +
                "values (?,?,?,?,?,?)";
        Object[] args = {
                orderHistory.getState(),
                new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(orderHistory.getTime()),
                orderHistory.getNum(),
                orderHistory.getTotal(),
                orderHistory.getCid(),
                orderHistory.getGid()
        };
        int[] argTypes = {
                Types.VARCHAR,
                Types.VARCHAR,
                Types.INTEGER,
                Types.DECIMAL,
                Types.INTEGER,
                Types.INTEGER
        };
        return this.jdbcTemplate.update(sql, args, argTypes);
    }

    @Override
    public List<Map<String, Object>> queryAllOrderHistory() {
        String sql = "select * from orderhistory_view";
        return this.jdbcTemplate.queryForList(sql);
    }

    @Override
    public OrderHistory queryOrderHistoryByOid(Integer oid) {
        String sql = "select * from orderhistory_view where id = ? order by buyTime desc ";
        Object[] args = {oid};
        int[] argTypes = {Types.INTEGER};
        List<OrderHistory> orderHistoryList = this.jdbcTemplate.query(sql, args, argTypes,
                new OrderHistoryMapper());
        if (orderHistoryList != null && orderHistoryList.size() > 0) {
            return orderHistoryList.get(0);
        }
        return null;
    }

    @Override
    public int update(OrderHistory orderHistory) {
        String sql = "update orderhistory set oState=?,oTime=?,oNum=?,cost=?,CID=?,GID=? " +
                "where oId=?";
        Object[] args = {
                orderHistory.getState(),
                new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(orderHistory.getTime()),
                orderHistory.getNum(),
                orderHistory.getTotal(),
                orderHistory.getCid(),
                orderHistory.getGid(),
                orderHistory.getId()
        };
        int[] argTypes = {
                Types.VARCHAR,
                Types.VARCHAR,
                Types.INTEGER,
                Types.DECIMAL,
                Types.INTEGER,
                Types.INTEGER,
                Types.INTEGER
        };
        return this.jdbcTemplate.update(sql, args, argTypes);
    }
}
