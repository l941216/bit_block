package com.bluebooth.serviceImple;

import com.bluebooth.common.util.DateUtils;
import com.bluebooth.common.util.SnowFlakeUtil;
import com.bluebooth.entity.BtLockEquipment;
import com.bluebooth.mapper.BtLockEquipmentMapper;
import com.bluebooth.service.BtLockEquipmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Copyright (C), 2006-2010, ChengDu Lovo info. Co., Ltd.
 *
 * @BelongsPackage: com.bluebooth.serviceImple
 * @author: LX
 * @CreateDate: 2018/09/03/18:30
 * @version: 1.0
 * @Description: 蓝牙锁实现类
 */
@SuppressWarnings("ALL")
@Service
public class BtLockEquipmentServiceImple implements BtLockEquipmentService {
    @Autowired
     BtLockEquipmentMapper btLockEquipmentMapper;

    /**
     * 录入蓝牙设备
     * @param record
     * @return
     */
    @Override
    public int insert(BtLockEquipment record) {
        record.setAddTime(Long.valueOf(DateUtils.timeStamp()));
        record.setEquipmentId(String.valueOf(SnowFlakeUtil.nextId()));
        return btLockEquipmentMapper.insert(record);
    }
}
