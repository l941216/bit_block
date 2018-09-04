package com.bluebooth.entity;

import com.bluebooth.common.entity.BaseEntity;

public class BtLockRecord extends BaseEntity {

    private String recordId;

    private String fkBondId;

    private String fkAuthCustomer;

    private Long addTime;

    private Integer actionType;

    private Integer keyStatus;

    private Integer readStatus;

    private String parId;

    private Integer isSend;

    public String getRecordId() {
        return recordId;
    }

    public void setRecordId(String recordId) {
        this.recordId = recordId == null ? null : recordId.trim();
    }

    public String getFkBondId() {
        return fkBondId;
    }

    public void setFkBondId(String fkBondId) {
        this.fkBondId = fkBondId == null ? null : fkBondId.trim();
    }

    public String getFkAuthCustomer() {
        return fkAuthCustomer;
    }

    public void setFkAuthCustomer(String fkAuthCustomer) {
        this.fkAuthCustomer = fkAuthCustomer == null ? null : fkAuthCustomer.trim();
    }

    public Long getAddTime() {
        return addTime;
    }

    public void setAddTime(Long addTime) {
        this.addTime = addTime;
    }

    public Integer getActionType() {
        return actionType;
    }

    public void setActionType(Integer actionType) {
        this.actionType = actionType;
    }

    public Integer getKeyStatus() {
        return keyStatus;
    }

    public void setKeyStatus(Integer keyStatus) {
        this.keyStatus = keyStatus;
    }

    public Integer getReadStatus() {
        return readStatus;
    }

    public void setReadStatus(Integer readStatus) {
        this.readStatus = readStatus;
    }

    public String getParId() {
        return parId;
    }

    public void setParId(String parId) {
        this.parId = parId;
    }

    public Integer getIsSend() {
        return isSend;
    }

    public void setIsSend(Integer isSend) {
        this.isSend = isSend;
    }
}