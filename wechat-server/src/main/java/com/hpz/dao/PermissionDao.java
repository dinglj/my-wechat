package com.hpz.dao;

import com.hpz.out.model.Permission;
import com.hpz.out.model.Role;
import com.hpz.out.model.RolePermission;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by tommy on 15/9/5.
 */
public interface PermissionDao {

    /***
     * 获取一个角色的所有权限
     * @param roleId
     * @return
     */
    List<Permission> getPermissonByRoleId(@Param("role") Long roleId);

    /**
     * 删除某个角色的权限数据
     * @param roleId
     * @return
     */
    int deletePermissionsByRoleId(@Param("role") Long roleId);

    /**
     * 获取所有的角色
     * @return
     */
    public List<Role> getAllRole();

    /**
     * 分组的权限列表
     * @return
     */
    List<Permission> getPermissionsOrderGroup();

    /**
     * 批量增加用户权限
     * @param rolePermissions
     * @return
     */
    int  newpermissions(List<RolePermission> rolePermissions);

    /***
     * 通过id获取角色
     * @param id
     * @return
     */
    Role getRoleById(Long id);
}
