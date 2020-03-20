package com.tianhy.study.orm.mybatisdemo;

import com.tianhy.study.orm.mybatisdemo.mapper.CoffeeMapper;
import com.tianhy.study.orm.mybatisdemo.model.Coffee;
import lombok.extern.slf4j.Slf4j;
import org.joda.money.CurrencyUnit;
import org.joda.money.Money;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.*;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@Slf4j
@MapperScan("com.tianhy.study.orm.mybatisdemo.mapper")
public class MybatisDemoApplication implements ApplicationRunner {

    @Autowired
    private CoffeeMapper coffeeMapper;

    public static void main(String[] args) {
        SpringApplication.run(MybatisDemoApplication.class, args);
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        Coffee latte = Coffee.builder()
                .name("latte")
                .price(Money.of(CurrencyUnit.of("CNY"), 20.0))
                .build();
        int count = coffeeMapper.save(latte);
        log.info("Save {} Coffee:{}", count, latte);

        latte = Coffee.builder()
                .name("latte")
                .price(Money.of(CurrencyUnit.of("CNY"), 30.0))
                .build();
        count = coffeeMapper.save(latte);
        log.info("Save {} Coffee:{}", count, latte);

        latte = coffeeMapper.findById(latte.getId());
        log.info("Find Coffee:{}", latte);
    }
}
