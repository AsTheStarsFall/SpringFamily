package com.tianhy.study.spring.data.declaretransaction;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.AdviceMode;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.annotation.EnableTransactionManagement;


/**
 * @Description: 声明式事务
 * @Author: thy
 * @Date: 2020/3/14
 */
@SpringBootApplication
@Slf4j
@EnableTransactionManagement(mode = AdviceMode.PROXY)
public class DeclareTransactionApplication implements CommandLineRunner {
    @Autowired
    private FooService fooService;
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public static void main(String[] args) {
        SpringApplication.run(DeclareTransactionApplication.class, args);
    }


    @Override
    public void run(String... args) throws Exception {
        fooService.insertData();
        log.info("AAA :{}",
                jdbcTemplate.
                        queryForObject("SELECT COUNT(*) FROM FOO WHERE BAR = 'AAA'", Long.class));

        try {
            fooService.insertThenRollback();
        } catch (RollbackException e) {
            log.info("BBB:{}",
                    jdbcTemplate.
                            queryForObject("SELECT COUNT(*) FROM FOO WHERE BAR = 'BBB'", Long.class));
        }

        try {
            fooService.invokeInsertThenRollback();
        } catch (RollbackException e) {
            log.info("BBB:{}",
                    jdbcTemplate
                            .queryForObject("SELECT COUNT(*) FROM FOO WHERE BAR = 'BBB'", Long.class));
        }


    }
}
