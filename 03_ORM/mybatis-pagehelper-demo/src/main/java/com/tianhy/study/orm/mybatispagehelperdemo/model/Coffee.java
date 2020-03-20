package com.tianhy.study.orm.mybatispagehelperdemo.model;

import lombok.*;
import org.joda.money.Money;

import java.util.Date;

/**
 * {@link}
 *
 * @Desc:
 * @Author: thy
 * @CreateTime: 2020/3/18 4:14
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Coffee {
    private Long id;
    private String name;
    private Money price;
    private Date createTime;
    private Date updateTime;
}
