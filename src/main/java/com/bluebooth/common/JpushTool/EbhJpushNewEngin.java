package com.bluebooth.common.JpushTool;

import cn.jiguang.common.ClientConfig;
import cn.jiguang.common.ServiceHelper;
import cn.jiguang.common.connection.NettyHttpClient;
import cn.jiguang.common.resp.APIConnectionException;
import cn.jiguang.common.resp.APIRequestException;
import cn.jpush.api.JPushClient;
import cn.jpush.api.push.PushResult;
import cn.jpush.api.push.model.Options;
import cn.jpush.api.push.model.Platform;
import cn.jpush.api.push.model.PushPayload;
import cn.jpush.api.push.model.notification.AndroidNotification;
import cn.jpush.api.push.model.notification.IosNotification;
import cn.jpush.api.push.model.notification.Notification;
import cn.jpush.api.push.model.notification.PlatformNotification;
import com.google.common.collect.Maps;
import io.netty.handler.codec.http.HttpMethod;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Map;

/**
 * Copyright (C), 2006-2010, ChengDu Lovo info. Co., Ltd.
 *
 * @BelongsPackage: com.ebuyhouse.common
 * @author: LX
 * @CreateDate: 2018/09/30/15:25
 * @version: 1.0
 * @Description: 构建PushPayload
 */
@Slf4j
@Component
public class EbhJpushNewEngin {
    public static int JUMP_CONTRACT = 2;
    public static int JUMP_QA = 1;
    public static String AppRoleTag = "954895";
    private static final String MASTER_SECRET = "5d78088893adabed80193560";  //TODO(填写你的MASTER_SECRET)
    private static final String APP_KEY = "1876312cfbcb244a4825ee3b";     //TODO(填写你的APP_KEY)



    /**
     * 消息广播调用接口，消息同步发送
     * @param title    标题
     * @param alert    内容
     * @param http     url模版
     * @param jump_typ 1系统广播 2合同消息 3个人消息
     * @return
     */
    public static PushResult radioBroadcast(String title, String alert, String http, int jump_typ) {
        return sendPush(new NotificationExtras(title, alert, http, jump_typ), false);
    }

//    /**
//     * 消息广播 消息发布给定标签，商户、用户、工作人员
//     * @param tag
//     * @param title
//     * @param alert
//     * @param http
//     * @param jump_typ
//     * back 暂时未用到
//     * @return
//     */
//    public static PushResult radioBroadcast(String tag, String title, String alert, String http, int jump_typ) {
//        return sendPush(new NotificationExtras(title, alert, http, jump_typ).to(tag), false);
//    }

    /**
     * 异步发送 到唯一设备 商户、用户、工作人员 target  消息预到达目的主键
     *
     * @param tag
     * @param target
     * @param title
     * @param alert
     * @param http
     * @param jump_typ
     * @param alise 别名参数
     * @return
     */
    public static PushResult asyncOnlyDevice(String tag, String target, String title, String alert, String http, int jump_typ,List<String> alise) {
        return asyncOnlyDevice(tag, target, title, alert, http, jump_typ, Maps.newHashMap(),alise);
    }

    public static PushResult asyncOnlyDevice(String tag, String target, String title, String alert, String http, int jump_typ, Map<String, String> extras, List<String> alise) {
        NotificationExtras notificationExtras = new NotificationExtras(title, alert, http, jump_typ, extras ,alise);

        // 唯一设备推送 必须制定 设备别名 若无别名则不发送
        if(notificationExtras.getAliases() == null || notificationExtras.getAliases().isEmpty()) {
            return new PushResult();
        }
        return sendPush(notificationExtras, true);
    }

    /**
     * 异步发送 到唯一设备 商户 target  消息预到达目的主键
     *
     * @param target
     * @param title
     * @param alert
     * @param http
     * @param jump_typ 1 留言消息 2 合同消息 3 系统广播 4 有http模版的系统广播
     * @return
     */
    public static PushResult asyncOnlyDeviceMcht(String target, String title, String alert, String http, int jump_typ,List<String> alise) {
        return asyncOnlyDevice(AppRoleTag, target, title, alert, http, jump_typ,alise);
    }

    public static PushResult asyncOnlyDeviceMcht(String target, String title, String alert, String https, int jump_typ, Map<String, String> extras,List<String> alise) {
        return asyncOnlyDevice(AppRoleTag, target, title, alert, https, jump_typ, extras,alise);
    }

    /**
     * 异步发送 到唯一设备 用户 target  消息预到达目的主键
     *
     * @param target
     * @param title
     * @param alert
     * @param http
     * @param jump_typ
     * @return
     */
    public static PushResult asyncOnlyDeviceUser(String target, String title, String alert, String http, int jump_typ,List<String> alise) {
        return asyncOnlyDevice("954895", target, title, alert, http, jump_typ,alise);
    }

    /**
     * 异步发送 到唯一设备 工作人员 target  消息预到达目的主键
     *
     * @param target
     * @param title
     * @param alert
     * @param http
     * @param jump_typ
     * @return
     */
    public static PushResult asyncOnlyDeviceWork(String target, String title, String alert, String http, int jump_typ,List<String> alise) {
        return asyncOnlyDevice(AppRoleTag, target, title, alert, http, jump_typ,alise);
    }


    /**
     * 极光推送 消息发送
     *
     * @param notificationExtras
     * @return
     */
    public static PushResult sendPush(NotificationExtras notificationExtras, boolean asynchronous){
        try {
            PushPayload payload = buildPushObject(notificationExtras);
            if(!asynchronous) {
                JPushClient jPushClient = new JPushClient(MASTER_SECRET, APP_KEY, null, ClientConfig.getInstance());
                return  jPushClient.sendPush(payload);
            } else {
                ClientConfig clientConfig = ClientConfig.getInstance();
                String host = (String) clientConfig.get(ClientConfig.PUSH_HOST_NAME);
                final NettyHttpClient client = new NettyHttpClient(ServiceHelper.getBasicAuthorization(APP_KEY, MASTER_SECRET),
                        null, clientConfig);
                URI uri = new URI(host + clientConfig.get(ClientConfig.PUSH_PATH));
                client.sendRequest(HttpMethod.POST, payload.toString(), uri,
                        responseWrapper -> log.info("Got result: " + responseWrapper.responseContent));
            }
        } catch (APIConnectionException e) {
            // Connection error, should retry later
            log.error("Connection error, should retry later", e);
        } catch (APIRequestException e) {
            log.error("Should review the error, and fix the request", e);
            log.info("HTTP Status: " + e.getStatus());
            log.info("Error Code: " + e.getErrorCode());
            log.info("Error Message: " + e.getErrorMessage());
        } catch (URISyntaxException e) {

        }
        return new PushResult();
    }

    /**
     * 给定别名推送
     *
     * @param notificationExtras
     * @return
     */
    private static PushPayload buildPushObject(NotificationExtras notificationExtras) {
        return PushPayload.newBuilder()
                .setPlatform(Platform.all())
                .setAudience(notificationExtras.getAudience())
                .setNotification(buildPushObject_all_notification(notificationExtras))
                .setOptions(Options.newBuilder()
//                        .setApnsProduction(true)
                        .build())
                .build();
    }

    /**
     * 构建全平台推送消息体 ios android
     * @param notificationExtras
     * @return
     */
    private static Notification buildPushObject_all_notification(NotificationExtras notificationExtras) {
        return Notification.newBuilder()
                .setAlert(notificationExtras.getAlert())
                .addPlatformNotification(buildPushObject_android_notification(notificationExtras))
                .addPlatformNotification(buildPushObject_ios_notification(notificationExtras))
                .build();
    }

    /**
     * 构建 android 主体消息
     * @param notificationExtras
     * @return
     */
    private static PlatformNotification buildPushObject_android_notification(NotificationExtras notificationExtras) {
        return AndroidNotification.newBuilder()
                .setTitle(notificationExtras.getTitle())
                .addExtras(notificationExtras.getExtras())
                .build();
    }

    /**
     * 构建 ios 主体消息
     * @param notificationExtras
     * @return
     */
    private static PlatformNotification buildPushObject_ios_notification(NotificationExtras notificationExtras) {
        return IosNotification.newBuilder()
                .incrBadge(notificationExtras.getBadge())
                .addExtras(notificationExtras.getExtras())
                .build();
    }
}
