package com.thinkgem.jeesite.modules.drh;

import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by Administrator on 2016/9/6.
 */
public class ResultModel implements Serializable{

    private static final long serialVersionUID = 1L;

    private int status;

    private String msg;

    private Map data;


    public ResultModel(int status, String msg, Map data) {
        this.status = status;
        this.msg = msg;
        this.data = data;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }


    public Map getData() {
        return data;
    }

    public void setData(Map data) {
        this.data = data;
    }

    public ResultModel put(String key , Object value){
        if (data==null){
            data = new LinkedHashMap();
        }
        data.put(key,value);
        return this;
    }
}
