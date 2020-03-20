package com.tianhy.study.spring.orm.jpademo.repository;

import com.tianhy.study.spring.orm.jpademo.model.CoffeeOrder;
import org.springframework.data.repository.CrudRepository;

/**
 * {@link}
 *
 * @Desc:
 * @Author: thy
 * @CreateTime: 2020/3/17 6:32
 **/
public interface CoffeeOrderRepository extends CrudRepository<CoffeeOrder, Long> {
}
