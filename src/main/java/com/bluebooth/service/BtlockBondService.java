package com.bluebooth.service;

import com.bluebooth.entity.BtLockBond;

public interface BtlockBondService {
//    int deleteByPrimaryKey(String bondId);
//
//    int insert(BtLockBond record);
//
//    int insertSelective(BtLockBond record);

    BtLockBond selectByPrimaryKey(String bondId);

//    int updateByPrimaryKeySelective(BtLockBond record);
//
//    int updateByPrimaryKey(BtLockBond record);
}
