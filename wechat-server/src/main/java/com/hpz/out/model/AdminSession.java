package com.hpz.out.model;

import java.io.Serializable;
import java.util.Date;

/**
 * 管理员登录记录
 * Created by mao on 2015/8/4.
 */
public class AdminSession implements Serializable{
    private Long id;
    private Administrator administrator;
    private String accessIp;
    private Date accessTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Administrator getAdministrator() {
        return administrator;
    }

    public void setAdministrator(Administrator administrator) {
        this.administrator = administrator;
    }

    public String getAccessIp() {
        return accessIp;
    }

    public void setAccessIp(String accessIp) {
        this.accessIp = accessIp;
    }

    public Date getAccessTime() {
        return accessTime;
    }

    public void setAccessTime(Date accessTime) {
        this.accessTime = accessTime;
    }
}
