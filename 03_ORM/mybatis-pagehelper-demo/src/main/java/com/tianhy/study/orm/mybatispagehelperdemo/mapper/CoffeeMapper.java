package com.tianhy.study.orm.mybatispagehelperdemo.mapper;

import com.tianhy.study.orm.mybatispagehelperdemo.model.Coffee;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.session.RowBounds;

import java.util.List;

/**
 * {@link}
 *
 * @Desc:
 * @Author: thy
 * @CreateTime: 2020/3/18 4:14
 **/
@Mapper
public interface CoffeeMapper {

    @Select("select * from t_coffee order by id ")
    List<Coffee> findAllWithRowBounds(RowBounds rowBounds);

    @Select("select * from t_coffee order by id ")
    List<Coffee> findAllWithParams(@Param("pageNum") int pageNum, @Param("pageSize") int pageSize);

}
