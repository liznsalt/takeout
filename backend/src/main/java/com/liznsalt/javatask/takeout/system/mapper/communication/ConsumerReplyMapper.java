package com.liznsalt.javatask.takeout.system.mapper.communication;

import com.liznsalt.javatask.takeout.common.utils.DateUtil;
import com.liznsalt.javatask.takeout.system.model.communication.ConsumerReply;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ConsumerReplyMapper implements RowMapper<ConsumerReply> {
    @Override
    public ConsumerReply mapRow(ResultSet resultSet, int i) throws SQLException {
        ConsumerReply consumerReply = new ConsumerReply();
        consumerReply.setCid(resultSet.getInt("cid"));
        consumerReply.setCoid(resultSet.getInt("coid"));
        consumerReply.setTime(DateUtil.formatTimeStamp(resultSet.getTimestamp("cotime")));
        consumerReply.setContent(resultSet.getString("content"));
        consumerReply.setCname(resultSet.getString("cname"));
        return consumerReply;
    }
}
