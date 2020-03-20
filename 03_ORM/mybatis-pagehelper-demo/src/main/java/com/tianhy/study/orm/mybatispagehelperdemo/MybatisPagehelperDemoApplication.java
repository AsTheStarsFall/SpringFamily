package com.tianhy.study.orm.mybatispagehelperdemo;

import com.github.pagehelper.PageInfo;
import com.tianhy.study.orm.mybatispagehelperdemo.mapper.CoffeeMapper;
import com.tianhy.study.orm.mybatispagehelperdemo.model.Coffee;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.session.RowBounds;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.*;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;

@SpringBootApplication
@Slf4j
@MapperScan("com.tianhy.study.orm.mybatispagehelperdemo.mapper")

public class MybatisPagehelperDemoApplication implements ApplicationRunner {

    @Autowired
    private CoffeeMapper coffeeMapper;

    public static void main(String[] args) {
        SpringApplication.run(MybatisPagehelperDemoApplication.class, args);
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {

        coffeeMapper.findAllWithRowBounds(new RowBounds(1, 3))
                .forEach(c -> log.info("page(1) : Coffee :{}", c));
        coffeeMapper.findAllWithRowBounds(new RowBounds(2, 3))
                .forEach(c -> log.info("page(2) : Coffee :{}", c));

        log.info("==================================================");
        coffeeMapper.findAllWithRowBounds(new RowBounds(1, 0))
                .forEach(c -> log.info("page(1) : Coffee :{}", c));
        log.info("==================================================");

        coffeeMapper.findAllWithParams(1, 3)
                .forEach(c -> log.info("page(1) : Coffee :{}", c));
        List<Coffee> list = coffeeMapper.findAllWithParams(1, 3);
        PageInfo pageInfo = new PageInfo(list);
        log.info("PageInfo :{}", pageInfo);

    }
}
