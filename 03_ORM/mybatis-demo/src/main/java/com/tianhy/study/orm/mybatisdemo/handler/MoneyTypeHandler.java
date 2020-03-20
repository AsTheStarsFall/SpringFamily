package com.tianhy.study.orm.mybatisdemo.handler;

import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.joda.money.CurrencyUnit;
import org.joda.money.Money;

import java.sql.*;

/**
 * {@link}
 *
 * @Desc: Money 与 Long 之前的转换，处理CNY人民币
 * @Author: thy
 * @CreateTime: 2020/3/17 8:24
 **/
public class MoneyTypeHandler extends BaseTypeHandler<Money> {
    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, Money money, JdbcType jdbcType) throws SQLException {
        ps.setLong(i, money.getAmountMajorLong());
    }

    @Override
    public Money getNullableResult(ResultSet resultSet, String columnName) throws SQLException {
        return paseMoney(resultSet.getLong(columnName));
    }

    @Override
    public Money getNullableResult(ResultSet resultSet, int columnIndex) throws SQLException {
        return paseMoney(resultSet.getLong(columnIndex));
    }

    @Override
    public Money getNullableResult(CallableStatement callableStatement, int columnIndex) throws SQLException {
        return paseMoney(callableStatement.getLong(columnIndex));
    }

    private Money paseMoney(Long value) {
        return Money.ofMajor(CurrencyUnit.of("CNY"), value);
    }
}
