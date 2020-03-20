package com.tianhy.study.spring.data.simplejdbc;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

/**
 * {@link}
 *
 * @Desc:
 * @Author: thy
 * @CreateTime: 2020/3/14 14:38
 **/
@Slf4j
@Repository
public class FooDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private SimpleJdbcInsert simpleJdbcInsert;

    public void insertData() {
        Arrays.asList("b", "c").forEach(bar -> {
            jdbcTemplate.update("INSERT INTO foo (bar) VALUES (?)", bar);
        });

        //插入后返回ID
        HashMap<String, String> row = new HashMap<>();
        row.put("BAR", "d");
        Number returnKey = simpleJdbcInsert.executeAndReturnKey(row);
        log.info("Insert d, return id :{}", returnKey.longValue());
    }


    public void listData() {
        // foo counts
        log.info("Foo count :{} ",
                jdbcTemplate.queryForObject("SELECT COUNT(*) FROM foo", Long.class));

        List<String> list = jdbcTemplate.queryForList("SELECT bar FROM foo", String.class);
        list.forEach(s -> log.info("Bar:{}", s));

        List<Foo> fooList = jdbcTemplate.query("SELECT * FROM foo", new RowMapper<Foo>() {
            @Override
            public Foo mapRow(ResultSet resultSet, int i) throws SQLException {
                return Foo.builder()
                        .id(resultSet.getLong(1))
                        .bar(resultSet.getString(2))
                        .build();
            }
        });

        fooList.forEach(s -> log.info("Foo :{}", s));

    }
}
