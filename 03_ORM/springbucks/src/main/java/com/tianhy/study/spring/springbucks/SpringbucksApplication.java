package com.tianhy.study.spring.springbucks;

import com.tianhy.study.spring.springbucks.model.*;
import com.tianhy.study.spring.springbucks.repository.CoffeeOrderRepository;
import com.tianhy.study.spring.springbucks.repository.CoffeeRepository;
import com.tianhy.study.spring.springbucks.service.CoffeeOrderService;
import com.tianhy.study.spring.springbucks.service.CoffeeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.*;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.util.Optional;

@Slf4j
@SpringBootApplication
@EnableTransactionManagement
@EnableJpaRepositories
public class SpringbucksApplication implements ApplicationRunner {
    @Autowired
    private CoffeeRepository coffeeRepository;
    @Autowired
    private CoffeeOrderRepository orderRepository;
    @Autowired
    private CoffeeService coffeeService;
    @Autowired
    private CoffeeOrderService orderService;

    public static void main(String[] args) {
        SpringApplication.run(SpringbucksApplication.class, args);
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        log.info("Find all coffee:{}", coffeeRepository.findAll());
        log.info("Find all coffee order:{}", orderRepository.findAll());
        Optional<Coffee> latte = coffeeService.findOneCoffee("Latte");
        if (latte.isPresent()) {
            //创建订单
            CoffeeOrder order = orderService.createOrder("wenhua", latte.get());
            log.info("Update INIT to PAID: {}", orderService.updateState(order, OrderState.PAID));
            log.info("Update PAID to INIT: {}", orderService.updateState(order, OrderState.INIT));
        }

    }
}
