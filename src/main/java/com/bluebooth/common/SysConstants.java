package com.bluebooth.common;


import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * @author zhaoqt
 * @Title: SysConstants.java
 * @ClassName: com.edan.SysConstants
 * @Description: 系统常量池
 * @date Jul 11, 2016 10:02:09 AM
 */
public class SysConstants extends ConstantClassStatus {

    public static final long DEL_AMOUNT = 0L;

    public static final String NULL_PLACEHOLDER = "";

    public static final Double header_ratio = 0.05;
    public static final Double trylive_ratio = 0.0075;

    /**
     * 兴趣圈是否审核
     */
    public static final boolean interestIsCheck = false;
    /**
     * 要求加兴趣圈默认同意
     */
    public static final boolean inviteDefaultAgree = true;





    /**
     * 默认推广渠道名称
     */
    /**
     * 默认推广渠道key
     */
    public static final String DEF_SUPR_CHANNEL_KEY = "sys";


    public static final String SESSION_SYS_CONFIG = "sys_config";

    public static final String SESSION_CURRENT_RESOURCE = "current_esource";

    public static final Double buyHouseProportion = 1D;

    public static class ImagerRule {
        public static final String WX_SHARE = "@!wechat-share-logo";
    }

    /**
     * 资金证明看房比例
     */
    public static final Double FundsProportion = 1D;

    /**
     * 授权状态
     */
    public static class authStatus {
        /*初始化*/
        //public static final Integer init = 0;
        /**
         * 未授权
         */
        public static final Integer unauthorized = 0;
        /**
         * 授权
         */
        public static final Integer authorized = 1;
        /**
         * 授权失效
         */
        public static final Integer AuthorizationFailure = 2;
    }

    /**
     * 社区相关常量
     */
    public static class Community {

        public static final String defaultHeadImg = "";

        /**
         * 申请加入社状态
         */
        public static class ApplyState {
            public static final Integer apply = 1;
            public static final Integer pass = 2;
            public static final Integer reject = 3;
            public static final Integer dismiss = 4;
            public static final Integer invite = 5;
        }

        /**
         * 社区圈中人员的角色
         */
        public static class Role {
            /**
             * 房东
             */
            public static final String lessor = "lessor";
            /**
             * 租户
             */
            public static final String leasee = "leasee";

            public static boolean isExist(String role) {
                if (leasee.equals(role) || lessor.equals(role)) {
                    return true;
                }
                return false;
            }
        }

        public static class RecordType {
            //类型， community:社区，interest:兴趣圈,friend:个人好友
            public static final String community = "community";
            public static final String interest = "interest";
            public static final String friend = "friend";
        }

        public static class RecordOperation {
            //操作，apply:申请，add:加入，out:退出
            public static final String apply = "apply";
            public static final String add = "add";
            public static final String out = "out";
            /**
             * 邀请
             */
            public static final String invite = "invite";
            /**
             * 踢出
             */
            public static final String off = "off";
            /**
             * 转让
             */
            public static final String turn = "turn";
            /**
             * 解散
             */
            public static final String dismiss = "dismiss";
        }

        public static class enterMode {
            /**
             * 免审核，直接通过
             */
            public static final String free = "free";
        }

    }

    /**
     * record常量
     */
    public static class CmRecord {
        //类型， community:社区，interest:兴趣圈,friend:个人好友
        public static final String community = "community";
        public static final String interest = "interest";
        public static final String friend = "friend";
        //操作，apply:申请，add:加入，out:退出
        public static final String apply = "apply";
        public static final String add = "add";
        public static final String out = "out";


    }

    public static class FundStatus {
        public static final Integer pass = 1;
        public static final Integer noCheck = 2;
        public static final Integer noPass = 3;
    }

    public static class PayMode {
        public static final String full = "full";
        public static final String loan = "loan";
        public static final String platform = "platform";
        public static final String lessor = "lessor";
    }

    public static class RegType {
        public static final String phone = "phone";

        public static final String email = "email";
    }

    public static class AccountType {
        /**
         * 手机号登录
         */
        public static final String phone = "phone";
        /**
         * Email登录
         */
        public static final String email = "email";
    }

    /**
     * 对处理后的数据排序 升降序
     *
     * @param data  集合
     * @param chars 指定字段
     */
    public static void sort(List<Map<String, Object>> data, String chars) {
        if (data.size() > 0) {
            Collections.sort(data, new Comparator<Map>() {
                public int compare(Map o1, Map o2) {
                    Long a = (Long) o1.get(chars);
                    Long b = (Long) o2.get(chars);
                    // 升序
                    //return a.compareTo(b);
                    // 降序
                    return b.compareTo(a);
                }
            });
        }
    }

    /**
     * 国际号码正则
     * 1.如果存在'+'号，必须在第一位
     * 2.如*果 存在 '(' ，必须存在 ')'
     * 3.'-'可以存在任意位置，但不能是第一位和最后一位
     * 4.可以存在空格
     * 5.必须有数字
     * 6.长度不限
     * 7.出现其他符号不能通过验证
     *
     * @param mobiles
     * @return
     */



    public static String adminId = "7cbd6f8d277345c396866e126b7449de";

    /**
     * 验证邮箱
     * 1.长度不限，
     * 2.可以使用英文（包括大小写）、数字、点号、下划线、减号，
     * 3.首字母必须是字母或数字
     * 4.不能以字符"-"开头和结尾
     * 5..com和.cn可以互换位置
     *
     * @param Email
     * @return
     */
    public static boolean isEmailNO(String Email) {
        Pattern p = Pattern.compile("^(\\w)+(\\.\\w+)*@(\\w)+((\\.\\w{2,3}){1,3})$");
        Matcher m = p.matcher(Email);
        return m.matches();
    }

    /**
     * 是否是数字
     *
     * @param number 输入数字
     * @return
     */
    public static boolean isNumber(String number) {
        Pattern p = Pattern.compile("^[0-9]*$");
        Matcher m = p.matcher(number);
        return m.matches();
    }

    /**
     * 是否是数字
     * 1-26位
     *
     * @param string 输入字符
     * @return
     */
    public static boolean isString(String string) {
        Pattern p = Pattern.compile("^[a-zA-Z·]{1,26}");
        Matcher m = p.matcher(string);
        return m.matches();
    }

    public static final String message_type_contact = "contact";

    public static class Push {
        // 消息提醒 联系我们 警告
        public static final Integer alert_message = 1;
        // offer列表
        public static final Integer offer_list = 2;
        // 系统广播 -- 没用
        public static final Integer sys_radio = 3;
        // 房屋推送--所有与房屋有关的全用该字段,包括房屋审核,房屋推送
        public static final Integer house_radio = 4;
        // 合同推送 -- 没用
        public static final Integer offer_radio = 5;
        // 资金凭证 -- 不用
        @Deprecated
        public static final Integer fund_radio = 6;
        // 合同 -- 没用
        public static final Integer contract_radio = 7;
        // 好友申请 不保存到ps_radio_ment
        public static final Integer friend_apply = 8;
        //需求模块房源推送
        public static final Integer friend_need_house = 9;
    }

    public static class VerifyCodeType {
        /**
         * 注册
         */
        public static final String reg = "reg";
        /**
         * 重置
         */
        public static final String reset = "reset";
    }

    public static class Origin {
        /**
         * pc/web
         */
        public static final String pc = "pc";
        /**
         * app
         */
        public static final String app = "app";
        /**
         * 平台
         */
        public static final String platform = "platform";

        public static final String admin = "admin";
    }

    public static class CheckStatus {
        /*初始化*/
        //public static final Integer init = 0;
        /**
         * 审核中
         */
        public static final Integer waitCheck = 1;
        /**
         * 通过
         */
        public static final Integer pass = 2;
        /**
         * 驳回
         */
        public static final Integer back = 3;
    }

    public static class IsEnable {
        /*初始化*/
        //public static final Integer init = 0;
        /**
         * 上架审核中
         */
        public static final Integer waitCheck = 1;
        /**
         * 上架
         */
        public static final Integer enable = 2;
        /**
         * 下架
         */
        public static final Integer disabled = 3;
        /**
         * 不可用
         */
        public static final Integer unUse = 4;

    }

    public static class OfferPageType {
        /**
         * 提交offer页面
         */
        public static final String submit = "submit";
        /**
         * Offer列表页面
         */
        public static final String list = "list";
        /**
         * 进入offer详情页
         */
        public static final String enter = "enter";
    }

    public static String rtnDaysChar(Long current_time, Long add_time) {
        long day = ((current_time - add_time) / 24 / 60 / 60) + 1;
        if (day > 1) {
            return day + " days";
        } else {
            return day + " day";
        }
    }

    public static class State {
        /**
         * 成功
         */
        public static final int STATUS_200 = 200;
        /** */
        public static final int STATUS_404 = 404;//不存在
        /**
         * 不能为空
         */
        public static final int STATUS_500 = 500;
        /**
         * 重复验证
         */
        public static final int STATUS_406 = 406;
        /**
         * 验证错误，不匹配
         */
        public static final int STATUS_407 = 407;
        /**
         * 服务端没有保存用户的秘钥 ，无法解密,客户端 调到登录页，需要重新登录
         */
        public static final int STATUS_408 = 408;
        /**
         * 短信发送失败
         */
        public static final int STATUS_409 = 409;
        /**
         * 服务器端的秘钥已经丢失，请重新登录 secret is not exists ,please relogin
         */
        public static final int STATUS_501 = 501;
        /**
         * 不需要验证，请不要请求发送验证码
         */
        public static final int STATUS_502 = 502;
        /**
         * 超过短信每天最多发送 次数
         */
        public static final int STATUS_503 = 503;
        public static final String STATUS_STR = "state";
        public static final String ENTITY = "entity";
        public static final String ROWS = "rows";
        public static final String TOTAL = "total";
        public static final String ERROR = "error";
    }

    public enum record {
        /**
         * 减少
         **/
        sub,
        /**
         * 增加
         **/
        add,
        /**
         * 除
         **/
        div,
        /**
         * 乘
         **/
        mul
    }

    public enum Arith {
        /**
         * 减少
         **/
        sub,
        /**
         * 增加
         **/
        add,
        /**
         * 除
         **/
        div,
        /**
         * 乘
         **/
        mul
    }

    public static class FrendApplyState {
        /**
         * 待处理
         **/
        public static final Integer wait = 1;
        /**
         * 已查看
         */
        public static final Integer look = 2;
        /**
         * 已通过
         **/
        public static final Integer pass = 3;
        /**
         * 已拒绝
         **/
        public static final Integer refuse = 4;
        /**
         * 删除
         **/
        public static final Integer delete = 5;
    }

    /**
     *  * 正则表达式验证密码
     *  * @param input
     *  * @return
     *  
     */
    public static boolean rexCheckPassword(String input) {
        // 6-20 位，字母、数字、字符
        //String reg = "^([A-Z]|[a-z]|[0-9]|[`-=[];,./~!@#$%^*()_+}{:?]){6,20}$";
        String regStr = "^([A-Z]|[a-z]|[0-9]|[`~!@#$%^&*()+=|{}':;',\\\\[\\\\].<>/?~！@#￥%……&*（）——+|{}【】‘；：”“'。，、？]){6,20}$";
        return input.matches(regStr);
    }

    public static final class AppRoleTag {
        public static final String MCHT = "954895";

        public static final String USER = "954895";

        public static final String WORK = "954895";

        public static boolean contains(String key) {
            if (MCHT.equals(key) || USER.equals(key) || WORK.equals(key)) {
                return Boolean.TRUE;
            }
            return Boolean.FALSE;
        }
    }

    public static final class saveType {
        public static final String RENT = "rent";
        public static final String SALE = "sale";

    }

    public static final String THREAD_CURRENT_ISS = "current_iss";

    public static Long currentTimeSecond() {
        return System.currentTimeMillis() / 1000;
    }


}
