package com.bluebooth.constant;

import com.bluebooth.common.util.EnumUtil;

import java.util.Map;

public class BtLockConstant {
    /**
     * @BelongsPackage: com.bluebooth.common.constant
     * @author: Lee
     * @CreateDate: 2018/09/03/20:38
     * @version: 1.0
     * @Description: 蓝牙锁相关常量
     */

    /**
     *启用状态
     */
    public static class useStatus {
        /**
         * 启用
         */
        public static final Integer USE = 0;
        /**
         * 未启用
         */
        public static final Integer UNUSED = 1;
    }

    public static class auth_status {
        /**
         * 启用
         */
        public static final Integer USE = 0;
        /**
         * 未启用
         */
        public static final Integer UNUSED = 1;
    }

    /**
     * 钥匙状态
     * 格式：
     * KEY_當前存在的钥匙孔标号_状态缩写 = 状态值
     */
    public  static class  keyStatus{
        public static final Integer KEY_3_FIRST = 1;
        public static final Integer KEY_2_SECOND = 2;
        public static final Integer KEY_23_THIRD = 3;
        public static final Integer KEY_1_FOURTH = 4;
        public static final Integer KEY_13_FIFTH = 5;
        public static final Integer KEY_12_SIXTH = 6;
        public static final Integer KEY_123_SEVEN = 7;

    }

    public  static  class  actionType{
        /**
         * 请求
         */
        public static final  Integer REQUEST_KEY =1;
        /**
         * 授权
         */
        public static final  Integer AUTH_KEY =2;
        /**
         * 开锁
         */
        public static final  Integer OPEN_LOCKBOX =3;
        /**
         * 取钥匙
         */
        public static final  Integer TAKE_KEY =4;
        /**
         * 归还钥匙
         */
        public static final  Integer RETURN_KEY =5;
        /**
         * 关锁
         */
        public static final  Integer SHUT_LOCKBOX =6;
    }


}