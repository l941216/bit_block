package com.bluebooth.mapper;

import com.bluebooth.entity.BtLockEquipment;

public interface BtLockEquipmentMapper {

    int deleteByPrimaryKey(String equipmentId);

    int insert(BtLockEquipment record);

    int insertSelective(BtLockEquipment record);

    BtLockEquipment selectByPrimaryKey(String equipmentId);

    int updateByPrimaryKeySelective(BtLockEquipment record);

    int updateByPrimaryKey(BtLockEquipment record);
}