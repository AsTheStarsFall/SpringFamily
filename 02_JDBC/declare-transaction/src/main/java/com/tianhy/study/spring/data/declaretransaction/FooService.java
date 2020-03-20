package com.tianhy.study.spring.data.declaretransaction;

/**
 * {@link}
 *
 * @Desc:
 * @Author: thy
 * @CreateTime: 2020/3/14 23:22
 **/
public interface FooService {
    void insertData();

    void insertThenRollback() throws RollbackException;

    void invokeInsertThenRollback() throws RollbackException;
}
