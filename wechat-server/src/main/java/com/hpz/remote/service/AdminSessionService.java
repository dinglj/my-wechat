package com.hpz.remote.service;

import com.hpz.out.model.AdminSession;
import com.hpz.out.model.PermissionGroup;
import com.hpz.out.model.Result;
import com.hpz.out.model.Role;

import java.util.HashSet;
import java.util.List;

/**
 * Created by mao on 2015/8/4.
 */
public interface AdminSessionService {
    /**
     * 管理员登录
     * @param ipAddress ip地址 可为null
     * @param cellphone
     * @param password
     * @return
     */
    public Result<AdminSession> login(String ipAddress, String cellphone, String password);

    /**
     * 通过管理用户的id获取到所有的权限
     * @param uid
     * @return
     */
    public Result<HashSet<String>> getPermissionSetByUid(Long uid);

    /***
     * 新建角色
     * @param roleName  角色名字
     * @param permissionIds   角色对应的权限值
     * @return
     */
    public Result<Role> newRole(String roleName, List<String> permissionIds);


    /***
     * 获取分组的权限系统
     * @return
     */
    public Result<List<PermissionGroup>> getGroupedPermission();
    /***
     * 修改角色的权限值
     * @param roleId
     * @param permissionIds
     * @return
     */
    public Result<String> updataRolePermissions(Long roleId, List<String> permissionIds);


    /***
     * 给用户赋予一个角色
     * @param role
     * @param uid
     * @return
     */
    public Result<String> assignRoleToUid(Long role, Long uid);

    /***
     * 获取所有的角色
     * @param needPermission 是否返回角色下的权限值列表
     * @return
     */
    public Result<List<Role>> getAllRole(Boolean needPermission);

    /***
     * 获取所有的角色
     * @param   id    用户的登录id
     * @return
     */
    public Result<Role> getRoleByRoleId(Long id);





}
