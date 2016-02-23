package com.hpz.out.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by tommy on 15/9/5.
 */
public class PermissionGroup implements Serializable {

    Long id;
    String name;
    List<Permission>  permissions;


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

    public List<Permission> getPermissions() {
        return permissions;
    }

    public void setPermissions(List<Permission> permissions) {
        this.permissions = permissions;
    }

    public PermissionGroup(Long id,String name){
         this.id= id;
         this.name=name;
    }
    /***
     * 获取默认所有的写死的权限分组 因为权限分组是虚拟的和业务没有直接关系 所以这里写死
     * @return
     */
    public static List<PermissionGroup> getPermissionGroups(){
        List<PermissionGroup> permissionGroups = new ArrayList<>();
        PermissionGroup p1 = new PermissionGroup(1l,"广告管理");
        PermissionGroup p2 = new PermissionGroup(2l,"专场管理");
        PermissionGroup p3 = new PermissionGroup(3l,"品牌库");
        PermissionGroup p4 = new PermissionGroup(4l,"认证审核");
        PermissionGroup p5 = new PermissionGroup(5l,"财务相关");
        PermissionGroup p6 = new PermissionGroup(6l,"帮助中心");
        PermissionGroup p7 = new PermissionGroup(7l,"特卖");
        PermissionGroup p8 = new PermissionGroup(8l,"权限管理");
        permissionGroups.add(p1);
        permissionGroups.add(p2);
        permissionGroups.add(p3);
        permissionGroups.add(p4);
        permissionGroups.add(p5);
        permissionGroups.add(p6);
        permissionGroups.add(p7);
        permissionGroups.add(p8);
        return permissionGroups;
    }
}
