package com.tianhy.study.spring.springbucks.service;

import com.tianhy.study.spring.springbucks.model.Coffee;
import com.tianhy.study.spring.springbucks.repository.CoffeeRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static org.springframework.data.domain.ExampleMatcher.GenericPropertyMatchers.exact;

/**
 * {@link}
 *
 * @Desc:
 * @Author: thy
 * @CreateTime: 2020/3/18 4:58
 **/
@Slf4j
@Service
public class CoffeeService {

    @Autowired
    private CoffeeRepository coffeeRepository;

    public Optional<Coffee> findOneCoffee(String name) {
        //构建匹配规则
        ExampleMatcher matcher =
                ExampleMatcher.matching().withMatcher("name", exact().ignoreCase());
        Optional<Coffee> one =
                coffeeRepository.findOne(Example.of(Coffee.builder().name(name).build(), matcher));
        log.info("FindOneCoffee :{}", one);
        return one;
    }
}
