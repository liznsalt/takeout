package com.liznsalt.javatask.takeout.system.dao.goods.impl;

import com.liznsalt.javatask.takeout.system.dao.goods.GoodsDao;
import com.liznsalt.javatask.takeout.system.mapper.goods.GoodsMapper;
import com.liznsalt.javatask.takeout.system.model.goods.Goods;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.Types;
import java.util.List;
import java.util.Map;

@Repository("goodsDao")
public class GoodsDaoImpl implements GoodsDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public int add(Goods goods) {
        String sql = "insert into goods(gName, gPrice, stock, type, " +
                "buyCount, gPictureUrl, gPraise, `desc`, sid) values(?,?,?,?,?,?,?,?,?)";
        Object[] args = {
                goods.getName(),
                goods.getPrice(),
                goods.getStock(),
                goods.getType(),
                goods.getBuyCount(),
                goods.getPictureUrl(),
                goods.getPraise(),
                goods.getDesc(),
                goods.getSid()
        };
        int[] argTypes = {
                Types.VARCHAR,
                Types.DECIMAL,
                Types.INTEGER,
                Types.VARCHAR,
                Types.INTEGER,
                Types.VARCHAR,
                Types.DECIMAL,
                Types.VARCHAR,
                Types.INTEGER
        };
        return this.jdbcTemplate.update(sql,args,argTypes);
    }

    @Override
    public int update(Goods goods) {
        String sql="update goods set gName=?, gPrice=?, stock=?, type=?, " +
                "buyCount=?, gPictureUrl=?, gPraise=?, `desc`=?, sid=? where gid=?";
        Object[] args = {
                goods.getName(),
                goods.getPrice(),
                goods.getStock(),
                goods.getType(),
                goods.getBuyCount(),
                goods.getPictureUrl(),
                goods.getPraise(),
                goods.getDesc(),
                goods.getSid(),
                goods.getId()
        };
        int[] argTypes = {
                Types.VARCHAR,
                Types.DECIMAL,
                Types.INTEGER,
                Types.VARCHAR,
                Types.INTEGER,
                Types.VARCHAR,
                Types.DECIMAL,
                Types.VARCHAR,
                Types.INTEGER,
                Types.INTEGER
        };
        return this.jdbcTemplate.update(sql,args,argTypes);
    }

    @Override
    public int deleteById(Integer gid) {
        String sql="delete from goods where gid = ?";
        Object[] args = { gid };
        int[] argTypes = {Types.INTEGER};
        return this.jdbcTemplate.update(sql, args, argTypes);
    }

    @Override
    public List<Goods> queryAllGoodsByShopId(Integer sid) {
        String sql = "select * from goods where sid = ?";
        Object [] args = { sid };
        int[] argTypes = {Types.INTEGER};
        List<Goods> goodsList = this.jdbcTemplate.query(sql, args, argTypes, new GoodsMapper());
        if(goodsList != null && goodsList.size() > 0) {
            return goodsList;
        }else {
            return null;
        }
    }

    @Override
    public List<Map<String, Object>> queryAllGoods() {
        String sql = "select * from goods";
//        Object [] args = { };
//        int[] argTypes = {Types.CHAR};
        return this.jdbcTemplate.queryForList(sql);
    }

    @Override
    public Goods queryGoodsById(Integer gid) {
        String sql = "select * from goods where gid = ?";
        Object [] args = { gid };
        int[] argTypes = {Types.INTEGER};
        List<Goods> goodsList = this.jdbcTemplate.query(sql, args, argTypes, new GoodsMapper());
        if (goodsList != null && goodsList.size() > 0) {
            return goodsList.get(0);
        }
        else
            return null;
    }

    @Override
    public List<Map<String, Object>> queryGoodsByName(String gname) {
        String sql = "select * from goods where gname = ?";
        Object [] args = { gname };
        int[] argTypes = {Types.CHAR};
        List<Map<String, Object>> goodsList = this.jdbcTemplate.queryForList(sql, args, argTypes);
        if(goodsList != null && goodsList.size()>0) {
            return goodsList;
        }
        else
            return null;

    }
}
