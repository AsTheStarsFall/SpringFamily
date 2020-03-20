package com.tianhy.study.orm.mybatisdemo.mapper;

import com.tianhy.study.orm.mybatisdemo.model.Coffee;
import org.apache.ibatis.annotations.*;

/**
 * {@link}
 *
 * @Desc:
 * @Author: thy
 * @CreateTime: 2020/3/17 8:12
 **/
@Mapper
public interface CoffeeMapper {

    @Insert("insert into t_coffee (name,price,create_time,update_time) values (#{name},#{price},now(),now())")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    int save(Coffee coffee);


    @Select("select * from t_coffee where id = #{id}")
    @Results({
            @Result(id = true, column = "id", property = "id"),
            @Result(column = "create_time", property = "createTime")
    })
    Coffee findById(@Param("id") Long id);

}
