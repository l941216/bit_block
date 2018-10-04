package com.bluebooth.common.JpushTool;

import cn.jpush.api.push.model.audience.Audience;
import com.google.common.collect.Lists;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by IntelliJ IDEA.
 * 通知消息扩展
 *
 * @author zhaoqt
 * @date 2017-10-17 上午11:29
 */
public class NotificationExtras {

    /**
     *  获取推送映射别名
     * @param tag 标签
     * @param source 主键
     *
     *  返回 pair 第一个参数 设备平台 ios or android 第二个参数 设备别名
     *
     * @param tag
     * @param source
     * @return
     */
//    public Pair<String, String> getSourceAlias(String tag, String source) {
//        return jPushService.getSourceAlias(tag, source);
//    }

    private String title;

    private String alert;

    private String http;

    private int badge;

    private int jump_typ;

    private Map<String, String> extras;

    private List<String> aliases;

    private List<String> tags;


    public NotificationExtras() {

    }

    public NotificationExtras(String title, String alert) {
        this(title, alert, "", 1, 1, new HashMap<String, String>());
    }

    public NotificationExtras(String title, String alert, String http, int jump_typ) {
        this(title, alert, http, 1, jump_typ, new HashMap<String, String>());
    }

    public NotificationExtras(String title, String alert, String http, int jump_typ, Map<String, String> extras) {
        this(title, alert, http, 1, jump_typ, extras);
    }
//
//    public NotificationExtras(String title, String alert, String http, int jump_typ,List<String> tag) {
//        this.title = title;
//        this.alert = alert;
//        this.http = http;
//        this.jump_typ = jump_typ;
//        this.tags = tag;
//    }

    public NotificationExtras(String title, String alert, String http, int jump_typ, Map<String, String> extras, List<String> aliase) {
        this.title = title;
        this.alert = alert;
        this.http = http;
        this.jump_typ = jump_typ;
        this.extras = extras;
        this.aliases =aliase;
    }
    public NotificationExtras(String title, String alert, String http, int badge, int jump_typ) {
        this(title, alert, http, badge, jump_typ, new HashMap<String, String>());
    }

    public NotificationExtras(String title, String alert, String http, int badge, int jump_typ, Map<String, String> extras) {
        this.title = title;
        this.alert = alert;
        this.http = http;
        this.badge = badge;
        this.jump_typ = jump_typ;
        this.extras = extras;
    }


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAlert() {
        return alert;
    }

    public void setAlert(String alert) {
        this.alert = alert;
    }

    public String getHttp() {
        return http;
    }

    public void setHttp(String http) {
        this.http = http;
    }

    public int getBadge() {
        return badge;
    }

    public void setBadge(int badge) {
        this.badge = badge;
    }

    public int getJump_typ() {
        return jump_typ;
    }

    public void setJump_typ(int jump_typ) {
        this.jump_typ = jump_typ;
    }

    public Map<String, String> getExtras() {
        extras.put("http", this.http);
        extras.put("badge", ""+this.badge);
        extras.put("jump_typ", ""+this.jump_typ);
        return extras;
    }

    public void setExtras(Map<String, String> extras) {
        this.extras = extras;
    }

    public NotificationExtras to(String tag) {
        if(this.tags == null) {
            this.tags = Lists.newArrayList();
        }
        this.tags.add(tag);
        return this;
    }

    public Audience getAudience() {
        if(this.tags != null && !this.tags.isEmpty()) {
            return Audience.tag(this.tags);
        }
        if(this.aliases != null && !this.aliases.isEmpty()) {
            return Audience.alias(this.aliases);
        }
        return Audience.all();
    }

    public List<String> getAliases() {
        return aliases;
    }
}
