package com.liznsalt.javatask.takeout.system.dao.communication.impl;

import com.liznsalt.javatask.takeout.system.dao.communication.CommentaryDao;
import com.liznsalt.javatask.takeout.system.mapper.communication.CommentaryMapper;
import com.liznsalt.javatask.takeout.system.model.communication.Commentary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.Types;
import java.text.SimpleDateFormat;
import java.util.List;

@Repository("commentaryDao")
public class CommentaryDaoImpl implements CommentaryDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public int add(Commentary commentary) {
        String sql = "insert into commentary(cotime, content, stars, cid, sid)" +
                " VALUES (?,?,?,?,?)";
        Object[] args = {
                new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(commentary.getTime()),
                commentary.getContent(),
                commentary.getStars(),
                commentary.getCid(),
                commentary.getSid()
        };
        int[] argTypes = {
                Types.VARCHAR,
                Types.VARCHAR,
                Types.INTEGER,
                Types.CHAR,
                Types.CHAR
        };
        return this.jdbcTemplate.update(sql, args, argTypes);
    }

    @Override
    public int deleteCommentaryById(Integer coid) {
        String sql = "delete from commentary where CoID = ?";
        Object[] args = {
                coid
        };
        int[] argTypes = {
                Types.INTEGER
        };
        return this.jdbcTemplate.update(sql, args, argTypes);
    }

    @Override
    public int update(Commentary commentary) {
        String sql = "update commentary set CoTime = ?, content = ?, stars = ?," +
                " Cid = ?, SID = ? where CoID = ?";
        Object[] args = {
                new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(commentary.getTime()),
                commentary.getContent(),
                commentary.getStars(),
                commentary.getCid(),
                commentary.getSid(),
                commentary.getId()
        };
        int[] argTypes = {
                Types.VARCHAR,
                Types.VARCHAR,
                Types.INTEGER,
                Types.INTEGER,
                Types.INTEGER,
                Types.INTEGER
        };
        return this.jdbcTemplate.update(sql, args, argTypes);
    }

    @Override
    public List<Commentary> queryCommentaryBySID(Integer SID) {
        String sql = "select * from commentary_view where sid = ? order by ctime desc ";
        Object[] args = {SID};
        int[] argTypes = {Types.INTEGER};
        List<Commentary> commentaryList = this.jdbcTemplate.query(sql, args, argTypes,
                new CommentaryMapper());
        if(commentaryList != null && commentaryList.size() >0) {
            return commentaryList;
        }
        else {
            return null;
        }
    }

    @Override
    public Commentary queryCommentaryByCoID(Integer CoID) {
        String sql = "select * from commentary_view where id = ? order by ctime desc ";
        Object[] args = {CoID};
        int[] argTypes = {Types.INTEGER};
        List<Commentary> commentaryList = this.jdbcTemplate.query(sql, args, argTypes,
                new CommentaryMapper());
        if(commentaryList != null && commentaryList.size() > 0){
            return commentaryList.get(0);
        }
        else {
            return null;
        }
    }
}
