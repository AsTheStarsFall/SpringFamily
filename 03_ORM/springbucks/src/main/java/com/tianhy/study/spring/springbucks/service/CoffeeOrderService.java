package com.tianhy.study.spring.springbucks.service;

import com.tianhy.study.spring.springbucks.model.*;
import com.tianhy.study.spring.springbucks.repository.CoffeeOrderRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.*;

/**
 * {@link}
 *
 * @Desc:
 * @Author: thy
 * @CreateTime: 2020/3/18 5:03
 **/
@Slf4j
@Service
@Transactional
public class CoffeeOrderService {

    @Autowired
    private CoffeeOrderRepository orderRepository;

    //创建订单
    public CoffeeOrder createOrder(String customer, Coffee... coffee) {
        CoffeeOrder order = CoffeeOrder.builder()
                .customer(customer)
                .items(new ArrayList<>(Arrays.asList(coffee)))
                .state(OrderState.INIT)
                .build();
        CoffeeOrder save = orderRepository.save(order);
        log.info("New order:{}", save);
        return order;
    }

    //更新订单状态
    public boolean updateState(CoffeeOrder order, OrderState state) {
        if (state.compareTo(order.getState()) <= 0) {
            log.warn("Wrong state order {}", state, order);
        }
        order.setState(state);
        orderRepository.save(order);
        log.info("Update order:{}", order);
        return true;
    }
}
