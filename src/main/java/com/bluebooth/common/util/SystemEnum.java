package com.bluebooth.common.util;

public enum SystemEnum {
    SUCCESS("成功", "000000"),
    FAIL("失败", "000001"),
    EXISTS("已存在", "000002"),
    SYSTEM_ERROR("系统繁忙，请稍后重试", "000002"),
    REQUEST_ERROR("请求错误", "000003"),
    ACCOUNT_TOKEN("新token标识", "000004"),
    FAILURE_DATA("数据失效，请重新申请", "000005"),
    QUERY_ERROR("查询错误", "000006"),
    INSERT_ERROR("新增错误", "000007"),
    YI_CAN("出现异常","000008"),
    TASKINSERT_ERROR("任务新增错误", "000009"),
    NO_DATA("无数据", "0000011"),
    CHONG_FU("数据重复", "0000012");

    // 成员变量
    private String descr;
    private String index;

    // 构造方法
    private SystemEnum(String descr, String index) {
        this.descr = descr;
        this.index = index;
    }

    // 通过索引获取描述
    public static String getDescr(String index) {
        for (SystemEnum c : SystemEnum.values()) {
            if (c.getIndex().equals(index)) {
                return c.descr;
            }
        }
        return FAIL.descr;
    }

    // 通过描述获取索引
    public static String getIndex(String descr) {
        if (descr == null)
            descr = "";
        for (SystemEnum c : SystemEnum.values()) {
            if (descr.equals(c.descr)) {
                return c.index;
            }
        }
        return FAIL.index;
    }

    // get set 方法
    public String getDescr() {
        return descr;
    }

    public void setDescr(String descr) {
        this.descr = descr;
    }

    public String getIndex() {
        return index;
    }

    public void setIndex(String index) {
        this.index = index;
    }

}
