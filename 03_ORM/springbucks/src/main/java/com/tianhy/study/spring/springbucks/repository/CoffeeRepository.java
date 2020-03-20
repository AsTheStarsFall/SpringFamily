package com.tianhy.study.spring.springbucks.repository;

import com.tianhy.study.spring.springbucks.model.Coffee;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * {@link}
 *
 * @Desc:
 * @Author: thy
 * @CreateTime: 2020/3/17 7:04
 **/
public interface CoffeeRepository extends JpaRepository<Coffee,Long> {
}
