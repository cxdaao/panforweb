package com.bkd.edu.mapper;

import com.bkd.edu.model.FixKey;

public interface FixMapper {
    int deleteByPrimaryKey(FixKey key);

    int insert(FixKey record);

    int insertSelective(FixKey record);
}