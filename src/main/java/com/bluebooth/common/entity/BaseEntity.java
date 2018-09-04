package com.bluebooth.common.entity;

import java.io.Serializable;
import java.util.Date;


public class BaseEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 当前页数
     */
    private int pageNum = 1;

    public int getPageNum() {
        return pageNum;
    }

    public void setPageNum(int pageNum) {
        this.pageNum = pageNum;
    }
    /**
     * 每页显示数
     */
    private int pageSize = 10;

    /**
     * 版本号
     */
    private Date version;


    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public Date getVersion() {
        return version;
    }

    public void setVersion(Date version) {
        this.version = version;
    }
}
