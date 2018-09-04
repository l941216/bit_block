package com.bluebooth.entity;

import com.bluebooth.common.entity.BaseEntity;

public class BtLockBond extends BaseEntity {

    private String bondId;

    private String fkCustomerId;

    private String fkBtId;

    private Long addTime;

    private Long updateTime;

    private Integer useStatus;
    
    private String password;

    private Integer keyStatus;

    private Integer authStatus;

    private String fkAuthCustomer;

    public String getBondId() {
        return bondId;
    }

    public void setBondId(String bondId) {
        this.bondId = bondId == null ? null : bondId.trim();
    }

    public String getFkCustomerId() {
        return fkCustomerId;
    }

    public void setFkCustomerId(String fkCustomerId) {
        this.fkCustomerId = fkCustomerId == null ? null : fkCustomerId.trim();
    }

    public String getFkBtId() {
        return fkBtId;
    }

    public void setFkBtId(String fkBtId) {
        this.fkBtId = fkBtId == null ? null : fkBtId.trim();
    }

    public Long getAddTime() {
        return addTime;
    }

    public void setAddTime(Long addTime) {
        this.addTime = addTime;
    }

    public Long getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Long updateTime) {
        this.updateTime = updateTime;
    }

    public Integer getUseStatus() {
        return useStatus;
    }

    public void setUseStatus(Integer useStatus) {
        this.useStatus = useStatus;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public Integer getKeyStatus() {
        return keyStatus;
    }

    public void setKeyStatus(Integer keyStatus) {
        this.keyStatus = keyStatus;
    }

    public Integer getAuthStatus() {
        return authStatus;
    }

    public void setAuthStatus(Integer authStatus) {
        this.authStatus = authStatus;
    }

    public String getFkAuthCustomer() {
        return fkAuthCustomer;
    }

    public void setFkAuthCustomer(String fkAuthCustomer) {
        this.fkAuthCustomer = fkAuthCustomer == null ? null : fkAuthCustomer.trim();
    }
}