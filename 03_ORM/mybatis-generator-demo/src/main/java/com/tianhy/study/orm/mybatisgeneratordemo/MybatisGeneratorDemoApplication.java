package com.tianhy.study.orm.mybatisgeneratordemo;

import com.tianhy.study.orm.mybatisgeneratordemo.mapper.CoffeeMapper;
import com.tianhy.study.orm.mybatisgeneratordemo.model.Coffee;
import com.tianhy.study.orm.mybatisgeneratordemo.model.CoffeeExample;
import lombok.extern.slf4j.Slf4j;
import org.joda.money.CurrencyUnit;
import org.joda.money.Money;
import org.mybatis.generator.api.MyBatisGenerator;
import org.mybatis.generator.config.Configuration;
import org.mybatis.generator.config.xml.ConfigurationParser;
import org.mybatis.generator.internal.DefaultShellCallback;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.*;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.*;

@SpringBootApplication
@MapperScan("com.tianhy.study.orm.mybatisgeneratordemo.mapper")
@Slf4j
public class MybatisGeneratorDemoApplication implements ApplicationRunner {

    @Autowired
    private CoffeeMapper coffeeMapper;

    public static void main(String[] args) {
        SpringApplication.run(MybatisGeneratorDemoApplication.class, args);
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        generateArtifacts();
        runWithArtifacts();
    }

    private void generateArtifacts() throws Exception {
        List<String> warnings = new ArrayList<>();
        ConfigurationParser cp = new ConfigurationParser(warnings);
        Configuration config = cp.parseConfiguration(
                this.getClass().getResourceAsStream("/generatorConfig.xml"));
        DefaultShellCallback callback = new DefaultShellCallback(true);
        MyBatisGenerator myBatisGenerator = new MyBatisGenerator(config, callback, warnings);
        myBatisGenerator.generate(null);
    }

    private void runWithArtifacts() {
        Coffee latte = new Coffee()
                .withName("latte")
                .withPrice(Money.of(CurrencyUnit.of("CNY"), 20.0))
                .withCreateTime(new Date())
                .withUpdateTime(new Date());
        coffeeMapper.insert(latte);

        Coffee mocha = new Coffee()
                .withName("mocha")
                .withPrice(Money.of(CurrencyUnit.of("CNY"), 30.0))
                .withCreateTime(new Date())
                .withUpdateTime(new Date());
        coffeeMapper.insert(mocha);

        //简单的查询
        Coffee byPrimaryKey = coffeeMapper.selectByPrimaryKey(1L);
        log.info("byPrimaryKey :{}", byPrimaryKey);

        //复杂的查询
        CoffeeExample example = new CoffeeExample();
        //创建查询条件
        example.createCriteria().andNameEqualTo("latte");
        List<Coffee> selectByExample = coffeeMapper.selectByExample(example);
        selectByExample.forEach(e -> log.info("selectByExample :{}", e));


    }
}
