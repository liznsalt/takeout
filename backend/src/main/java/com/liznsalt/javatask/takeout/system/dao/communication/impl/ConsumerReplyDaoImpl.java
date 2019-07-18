package com.liznsalt.javatask.takeout.system.dao.communication.impl;

import com.liznsalt.javatask.takeout.system.dao.communication.ConsumerReplyDao;
import com.liznsalt.javatask.takeout.system.mapper.communication.ConsumerReplyMapper;
import com.liznsalt.javatask.takeout.system.model.communication.ConsumerReply;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.Types;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Repository("consumerReplyDao")
public class ConsumerReplyDaoImpl implements ConsumerReplyDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public int add(ConsumerReply consumerReply)  {
        String sql = "insert into consumerreply(cid, coid, cotime, content) " +
                "values (?, ?, ?, ?)";

//        System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(consumerReply.getTime()));

        Object[] args = {
                consumerReply.getCid(),
                consumerReply.getCoid(),
                new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(consumerReply.getTime()),
                consumerReply.getContent()
        };
        int[] argTypes = {
                Types.INTEGER,
                Types.INTEGER,
                Types.VARCHAR,
                Types.CHAR
        };
        return this.jdbcTemplate.update(sql, args, argTypes);
    }

    @Override
    public int delete(Integer cid, Integer coid, Date date) {
        String sql = "delete from consumerreply where CID = ? AND Coid = ? " +
                "AND CoTime = ?";
        Object[] args = {
                cid,
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
    public int update(ConsumerReply oldReply, ConsumerReply newReply) {
        String sql = "update consumerreply set CID = ?, CoID = ?, CoTime = ?, " +
                "content = ? where CID = ? and CoID = ? and CoTime = ?";
        Object[] args = {
                newReply.getCid(),
                newReply.getCoid(),
                new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(newReply.getTime()),
                newReply.getContent(),
                oldReply.getCid(),
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
    public List<ConsumerReply> queryConsumerReplyByCID(Integer CID) {
        String sql = "select * from consumerreply_view where cid = ? order by cotime desc ";
        Object[] args = {CID};
        int[] argTypes = {Types.INTEGER};
        List<ConsumerReply> consumerReplyList = this.jdbcTemplate.query(sql, args, argTypes,
                new ConsumerReplyMapper());
        if(consumerReplyList != null && consumerReplyList.size() > 0) {
            return consumerReplyList;
        }
        else {
            return null;
        }
    }

    @Override
    public List<ConsumerReply> queryConsumerReplyByCoID(Integer CoID) {
        String sql = "select * from consumerreply_view where coid = ? order by cotime desc ";
        Object[] args = {CoID};
        int[] argTypes = {Types.INTEGER};
        List<ConsumerReply> consumerReplyList = this.jdbcTemplate.query(sql, args, argTypes,
                new ConsumerReplyMapper());
        if(consumerReplyList != null && consumerReplyList.size() > 0) {
            return consumerReplyList;
        }
        else {
            return null;
        }
    }
}
