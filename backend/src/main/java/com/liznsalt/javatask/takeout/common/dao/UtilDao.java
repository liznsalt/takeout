package com.liznsalt.javatask.takeout.common.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository("utilDao")
public class UtilDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public Integer getLastId() {
        return jdbcTemplate.query("select LAST_INSERT_ID()",
                new Object [] {},
                new int[] {},
                (resultSet, i) -> {
//                    System.out.println(resultSet);
                    return resultSet.getInt("LAST_INSERT_ID()");
                })
                .get(0);
    }

}
