package com.tianhy.study.spring.orm.jpademo.repository;

import com.tianhy.study.spring.orm.jpademo.model.Coffee;
import org.springframework.data.repository.CrudRepository;

/**
 * {@link}
 *
 * @Desc:
 * @Author: thy
 * @CreateTime: 2020/3/17 6:31
 **/
public interface CoffeeRepository extends CrudRepository<Coffee,Long> {

}
