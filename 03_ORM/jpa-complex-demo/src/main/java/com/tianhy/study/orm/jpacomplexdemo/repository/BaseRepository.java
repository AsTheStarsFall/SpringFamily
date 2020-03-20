package com.tianhy.study.orm.jpacomplexdemo.repository;

import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

/**
 * {@link}
 *
 * @Desc:
 * @Author: thy
 * @CreateTime: 2020/3/17 7:00
 **/
@NoRepositoryBean
public interface BaseRepository<T, Long> extends PagingAndSortingRepository<T, Long> {
    //时间降序，ID升序
    List<T> findTop3ByOrderByUpdateTimeDescIdAsc();
}
