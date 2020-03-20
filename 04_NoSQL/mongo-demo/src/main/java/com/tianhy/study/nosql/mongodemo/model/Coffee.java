package com.tianhy.study.nosql.mongodemo.model;

import lombok.*;
import org.joda.money.Money;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

/**
 * {@link}
 *
 * @Desc: Coffee实体类
 * @Author: thy
 * @CreateTime: 2020/3/18 10:23
 **/
@Document
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Coffee {
    @Id
    private String id;
    private String name;
    private Money price;
    private Date createTime;
    private Date updateTime;
}
