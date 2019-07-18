package com.liznsalt.javatask.takeout.system.mapper.communication;

import com.liznsalt.javatask.takeout.common.utils.DateUtil;
import com.liznsalt.javatask.takeout.system.model.communication.Commentary;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CommentaryMapper implements RowMapper<Commentary> {
    @Override
    public Commentary mapRow(ResultSet resultSet, int i) throws SQLException {
        Commentary commentary = new Commentary();
        commentary.setId(resultSet.getInt("id"));
        commentary.setContent(resultSet.getString("content"));
        commentary.setTime(DateUtil.formatTimeStamp(resultSet.getTimestamp("ctime")));
        commentary.setStars(resultSet.getInt("stars"));
        commentary.setCid(resultSet.getInt("cid"));
        commentary.setSid(resultSet.getInt("sid"));
        commentary.setCname(resultSet.getString("cname"));
        commentary.setSname(resultSet.getString("sname"));
        return commentary;
    }
}
