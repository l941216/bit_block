package com.bluebooth.common;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/9/6.
 * <p>
 * app端接收josn格式
 */
public class JSONModel implements Serializable {

    private static final long  serialVersionUID = 7521149292711927901L;

    private String message;

    private int state;

    private Content content;

    public static JSONModel me() {
        return new JSONModel();
    }

    private JSONModel() {
        this.content = new Content();
        this.state = 1;
        this.message = "操作成功";

    }

    private class Content {
        private Map<String, ?> data;
        private List<? extends Object> rows;

        private Content() {
            data = Maps.newHashMap();
            rows = Lists.newArrayList();
        }

        public Map<String, ?> getData() {
            return data;
        }

        public void setData(Map<String, ?> data) {
            this.data = data;
        }

        public List<? extends Object> getRows() {
            return rows;
        }

        public void setRows(List<? extends Object> rows) {
            this.rows = rows;
        }
    }

    public JSONModel setData(Map<String, ?> data) {
        this.content.data = data;
        return this;
    }

    public JSONModel setRows(List<? extends Object> rows) {
        this.content.rows = rows;
        return this;
    }

    public JSONModel success() {
        return this.state(1, "操作成功");
    }

    public JSONModel success(String message) {
        return this.state(1, message);
    }

    public JSONModel fail() {
        return this.fail("操作失败");
    }

    public JSONModel fail(String message) {
        return this.state(-1, message);
    }




    public JSONModel state(int state, String message) {
        this.message = message;
        this.state = state;
        return this;
    }

    public JSONModel fail(Exception e) {
        this.state(500, e.getMessage());
        return this;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public Content getContent() {
        return content;
    }

    public void setContent(Content content) {
        this.content = content;
    }

}
