package com.tianhy.study.spring.data.programmatictransaction;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;
import org.springframework.transaction.support.TransactionTemplate;


/**
 * @Description: 编程式事务
 * @Author: thy
 * @Date: 2020/3/14
 */
@SpringBootApplication
@Slf4j
public class ProgrammaticTransactionApplication implements CommandLineRunner {
    @Autowired
    private TransactionTemplate transactionTemplate;
    @Autowired
    private JdbcTemplate jdbcTemplate;


    public static void main(String[] args) {
        SpringApplication.run(ProgrammaticTransactionApplication.class, args);
    }

    @Override
    public void run(String... args) {
        //插入数据前查询
        log.info("Count before transaction :{}", getCount());
        //无返回结果
        transactionTemplate.execute(new TransactionCallbackWithoutResult() {
            @Override
            protected void doInTransactionWithoutResult(TransactionStatus status) {
                jdbcTemplate.execute("INSERT INTO foo (id,bar) VALUES (1,'aaa')");
                //回滚前查询
                log.info("Count in transaction :{}", getCount());
                //设置回滚
                status.setRollbackOnly();
            }
        });
        log.info("Count after transaction :{}", getCount());

    }

    private long getCount() {
        Object cnt = jdbcTemplate.queryForList("SELECT COUNT(*) AS CNT FROM foo").get(0).get("CNT");
        return (long) cnt;
    }
}
