package com.tianhy.study.spring.data.simplejdbc;

import lombok.Builder;
import lombok.Data;

/**
 * {@link}
 *
 * @Desc:
 * @Author: thy
 * @CreateTime: 2020/3/14 14:37
 **/
@Data
@Builder
public class Foo {

    private long id;
    private String bar;
}
