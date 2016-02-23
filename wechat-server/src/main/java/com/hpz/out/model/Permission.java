package com.hpz.out.model;

import java.io.Serializable;

/**
 * Created by tommy on 15/9/4.
 * 权限对象
 */
public class Permission implements Serializable {

    private Long id;
    private String name;  //权限值描述类似 PERMISSION_XX_YY XX是模块名 YY是具体的操作     l
    private String key; //权限值 用来对应controller中的权限
    private Long groupId; //组id 用来显示分组的时候
    private Boolean checked;///
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public Long getGroupId() {
        return groupId;
    }

    public void setGroupId(Long groupId) {
        this.groupId = groupId;
    }

    public Boolean getChecked() {
        return checked;
    }

    public void setChecked(Boolean checked) {
        this.checked = checked;
    }
}
