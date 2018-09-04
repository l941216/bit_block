package com.bluebooth.serviceImple;

import com.bluebooth.entity.BtLockBond;
import com.bluebooth.mapper.BtLockBondMapper;
import com.bluebooth.service.BtlockBondService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Copyright (C), 2006-2010, ChengDu Lovo info. Co., Ltd.
 *
 * @BelongsPackage: com.bluebooth.serviceImple
 * @author: LX
 * @CreateDate: 2018/09/04/13:58
 * @version: 1.0
 * @Description: 蓝牙绑定实现接口
 */
@SuppressWarnings("All")
@Service
public class BtlockBondServiceImple implements BtlockBondService {
    @Autowired
    BtLockBondMapper btLockBondMapper;
    @Override
    public BtLockBond selectByPrimaryKey(String bondId) {
        return btLockBondMapper.selectByPrimaryKey(bondId);
    }
}
