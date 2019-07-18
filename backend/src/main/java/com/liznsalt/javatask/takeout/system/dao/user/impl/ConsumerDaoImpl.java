package com.liznsalt.javatask.takeout.system.dao.user.impl;

import com.liznsalt.javatask.takeout.system.dao.user.ConsumerDao;
import com.liznsalt.javatask.takeout.system.mapper.user.ConsumerMapper;
import com.liznsalt.javatask.takeout.system.model.user.Consumer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.Types;
import java.util.List;
import java.util.Map;

@Repository("consumerDao")
public class ConsumerDaoImpl implements ConsumerDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public int add(Consumer consumer) {
        String sql = "insert into consumer(cName,cSex,cAge,cTel,cEmail," +
                "cAddress,cPassword,cBalance) values (?,?,?,?,?,?,?,?)";
        Object[] args = {
                consumer.getUsername(),
                consumer.getSex(),
                consumer.getAge(),
                consumer.getTelephone(),
                consumer.getEmail(),
                consumer.getAddress(),
                consumer.getPassword(),
                consumer.getBalance()
        };
        int[] argTypes = {
                Types.VARCHAR,
                Types.CHAR,
                Types.SMALLINT,
                Types.CHAR,
                Types.VARCHAR,
                Types.VARCHAR,
                Types.VARCHAR,
                Types.DECIMAL
        };
        return this.jdbcTemplate.update(sql,args,argTypes);
    }

    @Override
    public int update(Consumer consumer) {
        String sql = "update consumer set cName=?, cSex=?, cAge=?, cTel=?, cEmail=?," +
                "cAddress=?, cPassword=?, cBalance=? where cId=?";
        Object[] args = {
                consumer.getUsername(),
                consumer.getSex(),
                consumer.getAge(),
                consumer.getTelephone(),
                consumer.getEmail(),
                consumer.getAddress(),
                consumer.getPassword(),
                consumer.getBalance(),
                consumer.getId()
        };
        int[] argTypes = {
                Types.VARCHAR,
                Types.CHAR,
                Types.SMALLINT,
                Types.CHAR,
                Types.VARCHAR,
                Types.VARCHAR,
                Types.VARCHAR,
                Types.DECIMAL,
                Types.INTEGER
        };
        return this.jdbcTemplate.update(sql, args, argTypes);
    }

    @Override
    public int deleteById(Integer cid) {
        String sql = "delete from consumer where cid = ?";
        Object[] args = { cid };
        int[] argTypes = {Types.INTEGER};
        return this.jdbcTemplate.update(sql, args, argTypes);
    }

    @Override
    public List<Map<String, Object>> queryAllConsumer() {
        String sql = "select * from consumer";
        return this.jdbcTemplate.queryForList(sql);
    }

    @Override
    public Consumer queryConsumerById(Integer cid) {
        String sql = "select * from consumer where cid = ?";
        Object[] args = { cid };
        int[] argTypes = { Types.INTEGER };
        List<Consumer> consumerList = this.jdbcTemplate.query(sql, args, argTypes, new ConsumerMapper());
        if (consumerList != null && consumerList.size() > 0) {
            return consumerList.get(0);
        } else {
            return null;
        }
    }

    @Override
    public List<Consumer> queryConsumerByName(String cname) {
        String sql = "select * from consumer where cname = ?";
        Object [] args = { cname };
        int[] argTypes = {Types.VARCHAR};
        List<Consumer> consumerList = this.jdbcTemplate.query(sql, args, argTypes, new ConsumerMapper());
        if(consumerList != null && consumerList.size() > 0) {
            return consumerList;
        }else {
            return null;
        }
    }

    @Override
    public Consumer queryConsumerByPhone(String phone) {
        String sql = "select * from consumer where cTel = ?";
        Object [] args = { phone };
        int[] argTypes = {Types.CHAR};
        List<Consumer> consumerList = this.jdbcTemplate.query(sql, args, argTypes, new ConsumerMapper());
        if(consumerList != null && consumerList.size() > 0) {
            return consumerList.get(0);
        }else {
            return null;
        }
    }
}
