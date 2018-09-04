package com.bluebooth.mapper;

import com.bluebooth.entity.BtLockRecord;

public interface BtLockRecordMapper {

    int deleteByPrimaryKey(String recordId);

    int insert(BtLockRecord record);

    int insertSelective(BtLockRecord record);

    BtLockRecord selectByPrimaryKey(String recordId);

    int updateByPrimaryKeySelective(BtLockRecord record);

    int updateByPrimaryKey(BtLockRecord record);
}