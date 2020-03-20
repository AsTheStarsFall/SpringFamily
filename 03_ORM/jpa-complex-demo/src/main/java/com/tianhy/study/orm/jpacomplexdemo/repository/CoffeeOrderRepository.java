package com.tianhy.study.orm.jpacomplexdemo.repository;

import com.tianhy.study.orm.jpacomplexdemo.model.CoffeeOrder;

import java.util.List;

/**
 * {@link}
 *
 * @Desc:
 * @Author: thy
 * @CreateTime: 2020/3/17 7:04
 **/
public interface CoffeeOrderRepository extends BaseRepository<CoffeeOrder,Long> {

    //根据客户订单ID查询订单信息
    List<CoffeeOrder> findByCustomerOrderById(String customer);
    List<CoffeeOrder> findByItems_Name(String name);
}
