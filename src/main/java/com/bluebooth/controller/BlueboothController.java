package com.bluebooth.controller;

import com.bluebooth.common.JSONModel;
import com.bluebooth.common.util.DateUtils;
import com.bluebooth.common.util.EnumUtil;
import com.bluebooth.common.util.SnowFlakeUtil;
import com.bluebooth.constant.BtLockConstant;
import com.bluebooth.constant.BtlockEnum;
import com.bluebooth.entity.BtLockBond;
import com.bluebooth.entity.BtLockEquipment;
import com.bluebooth.entity.BtLockRecord;
import com.bluebooth.service.BtLockEquipmentService;
import com.bluebooth.service.BtLockRecordService;
import com.bluebooth.service.BtlockBondService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * Copyright (C), 2006-2010, ChengDu Lovo info. Co., Ltd.
 *
 * @BelongsPackage: com.bluebooth.controller
 * @author: LX
 * @CreateDate: 2018/09/03/17:51
 * @version: 1.0
 * @Description: 蓝牙锁操作
 */

@Api(value = BlueboothController.modelPath, tags = "蓝牙模块",
        description = "BluetoothController")
@RestController
@Slf4j
@RequestMapping(BlueboothController.modelPath)
public class BlueboothController {
    public static final String modelPath = "/app/bluetooth";
    @Autowired
    private BtLockEquipmentService btLockEquipmentService;
    @Autowired
    private BtlockBondService btlockBondService;
    @Autowired
    private BtLockRecordService btLockRecordService;
    private BtLockBond btLockBonds;
    private BtLockRecord btLockRecords;
    /**
     * 录入蓝牙设备信息
     * //     * @param blue
     * lx swagger
     * @return
     */
    @ApiOperation(value = "新增蓝牙设备", notes = "新增蓝牙")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", dataType = "String", name = "id", value = "蓝牙锁id", required = false),
            @ApiImplicitParam(paramType = "query", dataType = "String", name = "bluetoothmac", value = "蓝牙锁mac地址", required = false),
            @ApiImplicitParam(paramType = "query", dataType = "String", name = "code", value = "蓝牙锁认证code", required = false),
    })
    @ResponseBody
    @RequestMapping(value = "/fillInthe", method = RequestMethod.POST)
    public JSONModel bindBluetooth(@RequestBody BtLockEquipment blue) {
        JSONModel jsonModel = JSONModel.me();
        if (blue==null){
            return jsonModel.fail("Parameter is null");
        }
        if ("".equals(blue.getMac())){
            return jsonModel.fail("Parameter Mac is null");
        }
        if ("".equals(blue.getVerCode())){
            return jsonModel.fail("Parameter VerCode is null");
        }
        int a = btLockEquipmentService.insert(blue);
        if (a == 1) {
            jsonModel.setMessage("success");
            return jsonModel.success();
        } else {
            return jsonModel.fail();
        }
    }


    /**
     * 发送请求授权
     *
     * @param btLockBond 可以只传蓝牙的设备Id 扫码用户的id可以不传
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/requestAuth", method = RequestMethod.POST)
    public JSONModel blueAuthorization (BtLockBond btLockBond) {
        JSONModel jsonModel = JSONModel.me();
        //查询绑定状态的钥匙的各项数据
        btLockBonds = btlockBondService.selectByPrimaryKey(btLockBond.getBondId());
        //使用初始状态 查看钥匙数目是否正确
        String desInit = getDesc(btLockBonds.getKeyStatus().toString());
        String desCurrent = getDesc(btLockBond.getKeyStatus().toString());
        /**
         * 如果扫描请求授权时的钥匙数目与初始化的钥匙数目一致，那么则可以授权
         */
        if (desInit.equals(desCurrent)){
            btLockRecords.setAddTime(Long.valueOf(DateUtils.timeStamp()));
            btLockRecords.setRecordId(String.valueOf(SnowFlakeUtil.nextId()));
            btLockRecords.setActionType(BtLockConstant.actionType.REQUEST_KEY);
            btLockRecords.setFkBondId(btLockBond.getBondId());
            btLockRecords.setFkAuthCustomer(btLockBond.getFkCustomerId());
            btLockRecords.setParId(btLockRecords.getRecordId());
            btLockRecords.setKeyStatus(btLockBond.getKeyStatus());
            try {
                int i = btLockRecordService.insert(btLockRecords);
                if (i ==1){
                    jsonModel.setMessage("The request is successful");
                    jsonModel.success();
                }
            }catch (Exception e){
                jsonModel.setMessage("The request is successful");
                jsonModel.fail();
                return  jsonModel;
            }
        }else {
            jsonModel.setMessage("The request is fail , This Box Abnormal condition");
            jsonModel.fail();
        }
        //返回的数据应该包含 ebhUserBluetooth  add_time
        return jsonModel;
    }


    /**
     * 根据状态值获取描述数据
     * @param value
     * @return
     */
    public String getDesc(String value){
        Class<BtlockEnum> cb =  BtlockEnum.class;
        Map<Object, String> map=EnumUtil.EnumToMap(cb);
        String des=(String) EnumUtil.getEnumDescriotionByValue(value, cb);
        return des;
    }
}
