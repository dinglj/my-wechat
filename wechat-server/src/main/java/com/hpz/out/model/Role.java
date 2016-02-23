package com.hpz.out.model;

import java.io.Serializable;
import java.util.List;

/**
 * Created by tommy on 15/9/4.
 * 角色
 */
public class Role implements Serializable {

    private Long id;
    private String name;
    private List<Permission> permissionList;

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

    public List<Permission> getPermissionList() {
        return permissionList;
    }

    public void setPermissionList(List<Permission> permissionList) {
        this.permissionList = permissionList;
    }
}
