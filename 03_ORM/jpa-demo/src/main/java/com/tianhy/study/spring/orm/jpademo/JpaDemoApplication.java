package com.tianhy.study.spring.orm.jpademo;

import com.tianhy.study.spring.orm.jpademo.model.Coffee;
import com.tianhy.study.spring.orm.jpademo.model.CoffeeOrder;
import com.tianhy.study.spring.orm.jpademo.repository.CoffeeOrderRepository;
import com.tianhy.study.spring.orm.jpademo.repository.CoffeeRepository;
import lombok.extern.slf4j.Slf4j;
import org.joda.money.CurrencyUnit;
import org.joda.money.Money;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.*;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.util.Arrays;
import java.util.Collections;

@SpringBootApplication
@Slf4j
@EnableJpaRepositories
public class JpaDemoApplication implements ApplicationRunner {


    @Autowired
    private CoffeeRepository coffeeRepository;
    @Autowired
    private CoffeeOrderRepository orderRepository;

    public static void main(String[] args) {
        SpringApplication.run(JpaDemoApplication.class, args);
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        initOrders();
    }

    //初始化订单
    private void initOrders() {
        Coffee latte = Coffee.builder()
                .name("latte")
                .price(Money.of(CurrencyUnit.of("CNY"), 20.0))
                .build();
        coffeeRepository.save(latte);
        log.info("Coffee :{}", latte);

        Coffee mocha = Coffee.builder()
                .name("latte")
                .price(Money.of(CurrencyUnit.of("CNY"), 30.0))
                .build();
        coffeeRepository.save(mocha);
        log.info("Coffee :{}", mocha);


        //订单
        CoffeeOrder order = CoffeeOrder.builder()
                .customer("wenhua")
                .items(Collections.singletonList(latte))
                .state(0)
                .build();
        orderRepository.save(order);
        log.info("Order：{}", order);

        order = CoffeeOrder.builder()
                .customer("wenhua")
                .items(Arrays.asList(latte, mocha))
                .state(0)
                .build();
        orderRepository.save(order);
        log.info("Order：{}", order);


    }


}
