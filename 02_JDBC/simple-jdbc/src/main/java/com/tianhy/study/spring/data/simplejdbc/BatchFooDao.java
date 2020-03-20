package com.tianhy.study.spring.data.simplejdbc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.*;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSourceUtils;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * {@link}
 *
 * @Desc: 批量处理
 * @Author: thy
 * @CreateTime: 2020/3/14 20:35
 **/
@Repository
public class BatchFooDao {
    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public void batchInsterData() {
        // jdbcTemplate
        jdbcTemplate.batchUpdate("INSERT INTO foo (bar) VALUES (?)",
                new BatchPreparedStatementSetter() {
                    @Override
                    public void setValues(PreparedStatement ps, int i) throws SQLException {
                        ps.setString(1, "batch-" + i);
                    }
                    @Override
                    public int getBatchSize() {
                        return 2;
                    }
                });

        //namedParameterJdbcTemplate
        List<Foo> list = new ArrayList<>(5);
        for (int i = 0; i < 5; i++) {
            list.add(Foo.builder().id(100 + i).bar("b-10" + i).build());
        }
        namedParameterJdbcTemplate.batchUpdate("INSERT INTO foo (id,bar) VALUES (:id, :bar)",
                SqlParameterSourceUtils.createBatch(list));
    }
}
