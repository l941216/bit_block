package com.bluebooth.controller;

import com.bluebooth.common.JSONModel;
import com.bluebooth.common.util.*;
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
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
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

    private BtLockEquipment btLockEquipments;
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
    public Result fillInthe(@RequestBody BtLockEquipment blue) {
        JSONModel jsonModel = JSONModel.me();
        Result result = new Result();
        if (blue==null){
            return result.set(SystemEnum.FAIL).setMsg("Parameter is null");
        }
        if ("".equals(blue.getMac())){
            return result.set(SystemEnum.FAIL).setMsg("Parameter Mac is null");
        }
        if ("".equals(blue.getVerCode())){
            return result.set(SystemEnum.FAIL).setMsg("Parameter VerCode is null");
        }
        BtLockEquipment btLockEquipment = btLockEquipmentService.selectMac(blue.getMac());
        if (btLockEquipment == null){
            int a=0;
            try {
                 a = btLockEquipmentService.insert(blue);
            }catch (Exception e){
                log.info("操作蓝牙设备失败");
                return result.set(SystemEnum.FAIL);
            }
            BtLockEquipment btLockEquipment1 = btLockEquipmentService.selectMac(blue.getMac());
            if (a == 1) {
                jsonModel.setMessage("success");
                HashMap<String,Object> hashMap = new HashMap<>();
                hashMap.put("key",btLockEquipment1);
                jsonModel.setData(hashMap);
                result.set(SystemEnum.SUCCESS).setData(hashMap);
                return result;
            } else {
                log.info("操作蓝牙设备失败");
                return result.set(SystemEnum.FAIL);
            }
        }else {
            return result.set(SystemEnum.FAIL);
        }

    }
    @ApiOperation(value = "绑定蓝牙设备", notes = "绑定蓝牙")
    @ApiImplicitParams({
           @ApiImplicitParam(paramType = "query", dataType = "String", name = "customerId", value = "请求用户id", required = false),
    })
    @ResponseBody
    @RequestMapping(value = "/bondBtblock", method = RequestMethod.GET)
    public Result bondBtblock(@RequestBody BtLockEquipment btLockEquipment,@RequestParam String customerId){
        JSONModel jsonModel =JSONModel.me();
        Result result = new Result();
        /**
         * *查询设备是否有绑定
         */
        btLockBonds.setUseStatus(BtLockConstant.useStatus.USE_ISUSE);//启用
        btLockBonds.setFkBtId(btLockEquipment.getEquipmentId());//蓝牙id
        btLockBonds =btlockBondService.selectMacUseStatus(btLockBonds);
        if (btLockBonds != null) {
            jsonModel.setMessage("The lock has been bound");
            jsonModel.fail();
            result.set(SystemEnum.FAIL).setMsg("The lock has been bound");
        }else {
            //查询是否有设备
            btLockEquipments = btLockEquipmentService.select(btLockEquipment);
            if (btLockEquipments == null){
                jsonModel.setMessage("This lock does not exist or verification code is not correct");
                jsonModel.fail();
            }else {
                btLockBonds.setBondId(String.valueOf(SnowFlakeUtil.nextId()));
                btLockBonds.setAddTime(Long.valueOf(DateUtils.timeStamp()));
                btLockBonds.setFkCustomerId(customerId);
                try {
                    int i = btlockBondService.insert(btLockBonds);
                    if (i ==1){
                        jsonModel.setMessage("Lock binding Success");
                        jsonModel.success();
                        result.set(SystemEnum.SUCCESS).setMsg("Lock binding Success");
                    }else {
                        jsonModel.setMessage("Lock binding Fail");
                        jsonModel.fail();
                        result.set(SystemEnum.FAIL).setMsg("Lock binding Fail");
                    }
                }catch (Exception e){
                    log.error("用户绑定设备异常:"+e.toString());
                    result.set(SystemEnum.FAIL).setMsg(e.toString());
                    return result;
                }
            }
        }
        return result;
    }

    /**
     * 发送请求授权
     *
     * @param btLockBond 可以只传蓝牙的设备Id 扫码用户的id可以不传
     * @return
     */
    @ApiOperation(value = "请求蓝牙授权请求", notes = "请求蓝牙授权")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", dataType = "String", name = "customerId", value = "请求用户id", required = false),
    })
    @ResponseBody
    @RequestMapping(value = "/requestAuth", method = RequestMethod.POST)
    public Result blueAuthorization (BtLockBond btLockBond) {
        JSONModel jsonModel = JSONModel.me();
        Result result = new Result();
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
            btLockRecords.setActionType(BtLockConstant.actionType.ACTION_TYPE_REQUEST_KEY);
            btLockRecords.setFkBondId(btLockBond.getBondId());
            btLockRecords.setFkAuthCustomer(btLockBond.getFkCustomerId());
            btLockRecords.setParId(btLockRecords.getRecordId());
            btLockRecords.setKeyStatus(btLockBond.getKeyStatus());
            try {
                int i = btLockRecordService.insert(btLockRecords);
                if (i ==1){
                    BtLockRecord btLockRecord1 =btLockRecordService.selectByPrimaryKey(btLockRecords.getRecordId());
                    //这里需要申请的用户数据展示给锁的用户customer
                    HashMap<String,Object> map = new HashMap<>();
                    //将申请数据丢入返回给锁用户
                    map.put("data",btLockRecord1);
                    //用户数据丢入map
                    log.info("发送请求授权:"+"The request is Successful");
                    result.set(SystemEnum.SUCCESS).setData(map);
                }
            }catch (Exception e){

                log.info("发送请求授权:"+"The request is Fail");
                result.set(SystemEnum.FAIL).setMsg("The request is Fail");
                return  result;
            }
        }else {
            log.info("发送请求授权,钥匙数目不一致:"+"The request is fail , This Box Abnormal condition");
            result.set(SystemEnum.FAIL).setMsg("The request is fail , This Box Abnormal condition");
        }
        //返回的数据应该包含 ebhUserBluetooth  add_time
        return result;
    }


    /**
     * 用户授权给请求用户
     */
    @ApiOperation(value = "授权给用户", notes = "授权给用户")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", dataType = "String", name = "customerId", value = "请求用户id", required = false),
    })
    @Transactional
    @ResponseBody
    @RequestMapping(value = "/blueAuthCustomer",method = RequestMethod.GET)
    public Result blueAuthCustomer(@RequestBody BtLockRecord record){
        JSONModel jsonModel = JSONModel.me();
        Result result =new Result();
        //判断该蓝牙锁是否有授权存在，存在则可以，否则不允许
        //查询蓝牙绑定数据
        btLockBonds = btlockBondService.selectByPrimaryKey(record.getFkBondId());
        /**
         *绑定蓝牙开启使用，
         */
        if (btLockBonds != null){
            if ( "1".equals(btLockBonds.getUseStatus())){
                //更新授权状态 同时添加授权记录
                try{
                    //更新绑定状态表
                    btLockBonds.setAuthStatus(BtLockConstant.auth_status.AUTH_UNAUTHORIZED);
                    btLockBonds.setFkAuthCustomer(record.getFkAuthCustomer());
                    btlockBondService.updateByPrimaryKeySelective(btLockBonds);
                }catch (Exception e){
                    log.error("绑定过程中操作异常"+e);
                    result.set(SystemEnum.FAIL).setMsg(e.toString());
                    return  result;
                }
                try {
                    //添加授权记录表
                    btLockRecords.setRecordId(String.valueOf(SnowFlakeUtil.nextId()));
                    btLockRecords.setFkBondId(record.getFkBondId());
                    btLockRecords.setFkAuthCustomer(record.getFkAuthCustomer());
                    btLockRecords.setAddTime(Long.valueOf(DateUtils.timeStamp()));
                    btLockRecords.setActionType(BtLockConstant.actionType.ACTION_TYPE_AUTH_KEY);//授权
                    btLockRecords.setKeyStatus(record.getKeyStatus());
                    int i =  btLockRecordService.insert(btLockRecords);
                    if (i ==1 ){
                        log.info("绑定操作记录成功"+btLockRecords);
                        result.set(SystemEnum.SUCCESS);
                    }
                }catch (Exception e){
                    log.error("授权操作记录过程中操作异常"+e);
                   result.set(SystemEnum.FAIL).setMsg(e.toString());
                   return  result;
                }
            }
        }
        return  result;
    }

    /**
     *
     * @param btLockBond 扫描状态下锁的各项数据状态值，尤其是锁的状态数据
     * @param customerId 当前扫描用户的数据 当前用户的Id
     * @return
     */
    @ApiOperation(value = "扫描二维码", notes = "扫描二维码")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", dataType = "String", name = "customerId", value = "请求用户id", required = false),
    })
    @Transactional
    @ResponseBody
    @RequestMapping(value = "/openBtlock",method = RequestMethod.GET)
    public Result openBtlock(@RequestBody BtLockBond btLockBond ,@RequestParam String customerId ){
        Result result = new Result();
        //判断授权是否存在，同时需要比对钥匙数量，用来决定是允许开锁
        //未授权状态返回客户端是否授权
        btLockBond.setFkAuthCustomer(customerId);
        btLockBond.setAuthStatus(BtLockConstant.auth_status.AUTH_UNAUTHORIZED);
        BtLockBond btLockBond1 = btlockBondService.selectMacUseStatus(btLockBond);//查询授权
        if (btLockBond1 == null){
            result.set(SystemEnum.FAIL).setMsg("Request authorization");
        }else {
            //查询出该用户有授权（由于授权只会一次使用有效，这里可以暂时忽略时间轴的判断）
            //比对钥匙数量决定是否可以打开锁
            String desInit = getDesc(btLockBond.getKeyStatus().toString());
            String desCurrent = getDesc(btLockBond1.getKeyStatus().toString());
            if(!desInit.equals(desCurrent)){
                result.set(SystemEnum.FAIL).setMsg("The number of keys does not match");
                //建议请求数据到锁拥有者
            }else {
                result.set(SystemEnum.SUCCESS);
            }
        }
        return result;
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
