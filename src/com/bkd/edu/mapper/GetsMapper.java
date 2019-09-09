package com.bkd.edu.mapper;

import com.bkd.edu.model.GetsKey;

public interface GetsMapper {
    int deleteByPrimaryKey(GetsKey key);

    int insert(GetsKey record);

    int insertSelective(GetsKey record);
}