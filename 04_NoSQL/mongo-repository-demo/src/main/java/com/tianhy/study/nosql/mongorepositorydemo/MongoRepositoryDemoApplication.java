package com.tianhy.study.nosql.mongorepositorydemo;

import com.tianhy.study.nosql.mongorepositorydemo.converter.MoneyReadConverter;
import com.tianhy.study.nosql.mongorepositorydemo.model.Coffee;
import com.tianhy.study.nosql.mongorepositorydemo.repository.CoffeeRepository;
import lombok.extern.slf4j.Slf4j;
import org.joda.money.CurrencyUnit;
import org.joda.money.Money;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.*;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.convert.MongoCustomConversions;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import java.util.Arrays;
import java.util.Date;

@SpringBootApplication
@Slf4j
@EnableMongoRepositories
public class MongoRepositoryDemoApplication implements ApplicationRunner {

    @Autowired
    private CoffeeRepository coffeeRepository;

    public static void main(String[] args) {
        SpringApplication.run(MongoRepositoryDemoApplication.class, args);
    }

    @Bean
    public MongoCustomConversions mongoCustomConversions() {
        return new MongoCustomConversions(Arrays.asList(new MoneyReadConverter()));
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        Coffee mocha = Coffee.builder()
                .name("mocha")
                .price(Money.of(CurrencyUnit.of("CNY"), 30.5))
                .createTime(new Date())
                .updateTime(new Date())
                .build();
        Coffee latte = Coffee.builder()
                .name("latte")
                .price(Money.of(CurrencyUnit.of("CNY"), 30.5))
                .createTime(new Date())
                .updateTime(new Date())
                .build();
        coffeeRepository.insert(Arrays.asList(mocha, latte));
        coffeeRepository.findAll(Sort.by("name"))
                .forEach(e -> log.info("Saved coffee:{}", e));
        Thread.sleep(1000);

        latte.setPrice(Money.of(CurrencyUnit.of("CNY"), 35.5));
        latte.setUpdateTime(new Date());

        coffeeRepository.insert(latte);
        coffeeRepository.findByName("latte")
                .forEach(c -> log.info("Update coffee:{}", c));

        coffeeRepository.deleteAll();


    }
}
