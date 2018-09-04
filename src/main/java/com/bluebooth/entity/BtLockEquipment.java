package com.bluebooth.entity;

import com.bluebooth.common.entity.BaseEntity;

public class BtLockEquipment extends BaseEntity {

    private String equipmentId;

    private String mac;

    private Long addTime;

    private String verCode;

    public String getEquipmentId() {
        return equipmentId;
    }

    public void setEquipmentId(String equipmentId) {
        this.equipmentId = equipmentId == null ? null : equipmentId.trim();
    }

    public String getMac() {
        return mac;
    }

    public void setMac(String mac) {
        this.mac = mac == null ? null : mac.trim();
    }

    public Long getAddTime() {
        return addTime;
    }

    public void setAddTime(Long addTime) {
        this.addTime = addTime;
    }

    public String getVerCode() {
        return verCode;
    }

    public void setVerCode(String verCode) {
        this.verCode = verCode == null ? null : verCode.trim();
    }
}