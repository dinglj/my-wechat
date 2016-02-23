package com.hpz.out.model;

import java.io.Serializable;

/**
 * Created by tommy on 15/9/6.
 */
public class RolePermission implements Serializable {
    Long id;
    Long roleId;
    Long permissionId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    public Long getPermissionId() {
        return permissionId;
    }

    public void setPermissionId(Long permissionId) {
        this.permissionId = permissionId;
    }
}
