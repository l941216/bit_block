package com.bluebooth.constant;
/**
 * Copyright (C), 2006-2010, ChengDu Lovo info. Co., Ltd.
 *
 * @BelongsPackage: com.bluebooth.constant
 * @author: LX
 * @CreateDate: 2018/09/04/14:33
 * @version: 1.0
 * @Description: 枚举常量池
 */
public enum  BtlockEnum  {
    /**
     * btkey 蓝牙锁 1 代表状态值 1 代表钥匙数目
     */
    btKey0_0("0", "0"),
    btKey1_1("1", "1"),
    btKey1_2("2", "1"),
    btKey1_4("4", "1"),
    btKey2_3("3", "2"),
    btKey2_5("5", "2"),
    btKey2_6("6", "2"),
    btKey3_7("7", "3");
    private String value;
    private String description;
    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    private BtlockEnum(String value, String description) {
        this.value = value;
        this.description = description;
    }
}
