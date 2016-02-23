package com.hpz.out.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by mao on 2015/6/23.
 */
public class Result<T> implements Serializable{
    private Boolean success = true;
    private List<String> msg = new ArrayList<String>();
    private T data ;
    private Long timeCount;
    //用户提示信息
    private String userMsg;

    public String getUserMsg() {
        return userMsg;
    }

    public void setUserMsg(String userMsg) {
        this.userMsg = userMsg;
    }

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public List<String> getMsg() {
        return msg;
    }

    public void setMsg(List<String> msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public Long getTimeCount() {
        return timeCount;
    }

    public void setTimeCount(Long timeCount) {
        this.timeCount = timeCount;
    }
}



