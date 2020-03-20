package com.tianhy.study.nosql.mongodemo;

import com.mongodb.client.result.UpdateResult;
import com.tianhy.study.nosql.mongodemo.converter.MoneyReadConverter;
import com.tianhy.study.nosql.mongodemo.model.Coffee;
import lombok.extern.slf4j.Slf4j;
import org.joda.money.CurrencyUnit;
import org.joda.money.Money;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.*;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.convert.MongoCustomConversions;
import org.springframework.data.mongodb.core.query.*;

import java.util.*;

@Slf4j
@SpringBootApplication
public class MongoDemoApplication implements ApplicationRunner {

    @Autowired
    private MongoTemplate mongoTemplate;

    public static void main(String[] args) {
        SpringApplication.run(MongoDemoApplication.class, args);
    }

    @Bean
    public MongoCustomConversions mongoCustomConversions() {
        return new MongoCustomConversions(Arrays.asList(new MoneyReadConverter()));
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        Coffee latte = Coffee.builder()
                .name("latte")
                .price(Money.of(CurrencyUnit.of("CNY"), 30.0))
                .createTime(new Date())
                .updateTime(new Date())
                .build();
        Coffee save = mongoTemplate.save(latte);
        log.info("Save:{}", save);

        List<Coffee> list = mongoTemplate.find(
                Query.query(Criteria.where("name").is("latte")), Coffee.class
        );
        log.info("Find latte size:{}", list.size());
        list.forEach(e -> log.info("Coffee:{}", e));

        UpdateResult updateResult = mongoTemplate.updateFirst(Query.query(Criteria.where("name").is("latte")),
                new Update()
                        .set("price", Money.ofMinor(CurrencyUnit.of("CNY"), 35))
                        .currentDate("updateTime"), Coffee.class);
        log.info("Update modify count:{}", updateResult.getModifiedCount());

        Coffee coffee = mongoTemplate.findById(save.getId(), Coffee.class);

        log.info("Update result:{}", coffee);

        mongoTemplate.remove(coffee);
    }
}
