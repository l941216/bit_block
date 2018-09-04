package com.bluebooth.mapper;

import com.bluebooth.entity.BtLockBond;

public interface BtLockBondMapper {
    int deleteByPrimaryKey(String bondId);

    int insert(BtLockBond record);

    int insertSelective(BtLockBond record);

    BtLockBond selectByPrimaryKey(String bondId);

    int updateByPrimaryKeySelective(BtLockBond record);

    int updateByPrimaryKey(BtLockBond record);
}