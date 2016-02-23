package com.hpz.manager;

import com.hpz.out.model.AdminSession;
import com.hpz.out.model.PermissionGroup;
import com.hpz.out.model.Role;

import java.util.HashSet;
import java.util.List;

/**
 * Created by mao on 2015/8/4.
 */
public interface AdminSessionManager {
    public AdminSession add(AdminSession adminSession);

    public AdminSession login(String ipAddress, String cellphone, String password);

    /***
     * 获取某个用户的所有权限
     * @param id
     * @return
     */
    public HashSet<String> getPermissionsKeyByUid(Long id);


    /***
     * 获取所有的角色
     * @param needPermissions 是否返回权限列表
     * @return
     */
    List<Role> getAllRole(boolean needPermissions);

    /**
     * 获取分组的权限值
     * @return
     */
    public List<PermissionGroup> getGroupedPermission();

    /***
     * 新增用户角色一个
     * @param
     * @return
     */
    public Role newRole(String roleName, List<String> permissionIds);

    /***
     * 更新某角色的权限
     * @param role
     * @param permission
     */
    public void updateRolePermissions(Role role, List<String> permission);

    /***
     * 通过角色id获取一个角色的详细信息
     * @param roleId
     * @return
     */
    public Role getRoleById(Long roleId);
}
