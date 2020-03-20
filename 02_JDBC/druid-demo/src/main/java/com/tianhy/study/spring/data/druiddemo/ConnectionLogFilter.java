package com.tianhy.study.spring.data.druiddemo;

import com.alibaba.druid.filter.FilterChain;
import com.alibaba.druid.filter.FilterEventAdapter;
import com.alibaba.druid.proxy.jdbc.ConnectionProxy;
import lombok.extern.slf4j.Slf4j;

import java.util.Properties;

/**
 * {@link}
 *
 * @Desc:
 * @Author: thy
 * @CreateTime: 2020/3/14 12:42
 **/
@Slf4j
public class ConnectionLogFilter extends FilterEventAdapter {


    @Override
    public void connection_connectBefore(FilterChain chain, Properties info) {

        log.info("Before connection");
    }

    @Override
    public void connection_connectAfter(ConnectionProxy connection) {
        log.info("After connection");

    }
}
