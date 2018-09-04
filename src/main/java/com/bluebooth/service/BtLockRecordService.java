package com.bluebooth.service;

import com.bluebooth.entity.BtLockRecord;

/**
 * Copyright (C), 2006-2010, ChengDu Lovo info. Co., Ltd.
 *
 * @BelongsPackage: com.bluebooth.service
 * @author: LX
 * @CreateDate: 2018/09/04/16:32
 * @version: 1.0
 * @Description: 蓝牙操作记录接口
 */
public interface BtLockRecordService {

//    int deleteByPrimaryKey(String recordId);

    int insert(BtLockRecord record);

//    int insertSelective(BtLockRecord record);
//
//    BtLockRecord selectByPrimaryKey(String recordId);
//
//    int updateByPrimaryKeySelective(BtLockRecord record);
//
//    int updateByPrimaryKey(BtLockRecord record);
}
