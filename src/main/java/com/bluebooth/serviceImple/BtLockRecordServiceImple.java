package com.bluebooth.serviceImple;

import com.bluebooth.common.util.DateUtils;
import com.bluebooth.common.util.SnowFlakeUtil;
import com.bluebooth.entity.BtLockRecord;
import com.bluebooth.mapper.BtLockRecordMapper;
import com.bluebooth.service.BtLockRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Copyright (C), 2006-2010, ChengDu Lovo info. Co., Ltd.
 *
 * @BelongsPackage: com.bluebooth.serviceImple
 * @author: LX
 * @CreateDate: 2018/09/04/16:33
 * @version: 1.0
 * @Description: 蓝牙操作记录接口实现
 */
@SuppressWarnings("All")
@Service
public class BtLockRecordServiceImple  implements BtLockRecordService {
    @Autowired
    BtLockRecordMapper btLockRecordMapper;
    @Override
    public int insert(BtLockRecord record) {

        return btLockRecordMapper.insert(record);
    }
}
