package com.hpz.remote.service.impl;

import com.hpz.common.CommonUtil;
import com.hpz.common.DateUtil;
import com.hpz.common.ServiceException;
import com.hpz.manager.AdminSessionManager;
import com.hpz.out.model.AdminSession;
import com.hpz.out.model.PermissionGroup;
import com.hpz.out.model.Result;
import com.hpz.out.model.Role;
import com.hpz.remote.service.AdminSessionService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;
import java.util.HashSet;
import java.util.List;

/**
 * Created by mao on 2015/8/4.
 */
public class AdminSessionServiceImpl implements AdminSessionService {
    @Autowired
    private AdminSessionManager adminSessionManager;

    @Override
    public Result<AdminSession> login(String ipAddress,String cellphone, String password) {
        Date startTime = DateUtil.now();
        Result<AdminSession> result = new Result<AdminSession>();
        try {
            result.setData(adminSessionManager.login( ipAddress,cellphone,password));
        }catch (ServiceException e){
            CommonUtil.error(e.getMessage());
            result.getMsg().add(e.getMessage());
            result.setSuccess(false);
        }catch (Exception e) {
            CommonUtil.error(e.toString());
            result.getMsg().add("系统异常");
            result.setSuccess(false);
        }finally {
            Date endTime = DateUtil.now();
            result.setTimeCount(endTime.getTime() - startTime.getTime());
            return result;
        }
    }

    /**
     * 通过管理用户的id获取到所有的权限
     *
     * @param uid
     * @return
     */
    @Override
    public Result<HashSet<String>> getPermissionSetByUid(Long uid) {
        Date startTime = DateUtil.now();
        Result<HashSet<String>> result = new Result<HashSet<String>>();
        try {
            result.setData(adminSessionManager.getPermissionsKeyByUid(uid));
        }catch (ServiceException e){
            CommonUtil.error(e.getMessage());
            result.getMsg().add(e.getMessage());
            result.setSuccess(false);
        }catch (Exception e) {
            CommonUtil.error(e.toString());
            result.getMsg().add("系统异常");
            result.setSuccess(false);
        }finally {
            Date endTime = DateUtil.now();
            result.setTimeCount(endTime.getTime() - startTime.getTime());
            return result;
        }
    }

    /**
     * 新建角色
     *
     * @param roleName      角色名字
     * @param permissionIds 角色对应的权限值
     * @return
     */
    @Override
    public Result<Role> newRole(String roleName, List<String> permissionIds) {
        Date startTime = DateUtil.now();
        Result<Role> result = new Result<>();
        try {
            result.setData(adminSessionManager.newRole(roleName, permissionIds));
        }catch (ServiceException e){
            CommonUtil.error(e.getMessage());
            result.getMsg().add(e.getMessage());
            result.setSuccess(false);
        }catch (Exception e) {
            CommonUtil.error(e.toString());
            result.getMsg().add("系统异常");
            result.setSuccess(false);
        }finally {
            Date endTime = DateUtil.now();
            result.setTimeCount(endTime.getTime() - startTime.getTime());
            return result;
        }

    }

    /**
     * 获取分组的权限系统
     *
     * @return
     */
    @Override
    public Result<List<PermissionGroup>> getGroupedPermission() {
        Date startTime = DateUtil.now();
        Result<List<PermissionGroup>> result = new Result<>();
        try {
            result.setData(adminSessionManager.getGroupedPermission());
        }catch (ServiceException e){
            CommonUtil.error(e.getMessage());
            result.getMsg().add(e.getMessage());
            result.setSuccess(false);
        }catch (Exception e) {
            CommonUtil.error(e.toString());
            result.getMsg().add("系统异常");
            result.setSuccess(false);
        }finally {
            Date endTime = DateUtil.now();
            result.setTimeCount(endTime.getTime() - startTime.getTime());
            return result;
        }
    }

    /**
     * 修改角色的权限值
     *
     * @param roleId
     * @param permissionIds
     * @return
     */
    @Override
    public Result<String> updataRolePermissions(Long roleId, List<String> permissionIds) {
        Date startTime = DateUtil.now();
        Result<String> result = new Result<>();
        try {
            Role role = new Role();
            role.setId(roleId);
            adminSessionManager.updateRolePermissions(role, permissionIds);
            result.setSuccess(true);
            result.setData("添加成功");
        }catch (ServiceException e){
            CommonUtil.error(e.getMessage());
            result.getMsg().add(e.getMessage());
            result.setSuccess(false);
        }catch (Exception e) {
            CommonUtil.error(e.toString());
            result.getMsg().add("系统异常");
            result.setSuccess(false);
        }finally {
            Date endTime = DateUtil.now();
            result.setTimeCount(endTime.getTime() - startTime.getTime());
            return result;
        }
    }

    /**
     * 给用户赋予一个角色
     *
     * @param role
     * @param uid
     * @return
     */
    @Override
    public Result<String> assignRoleToUid(Long role, Long uid) {
        return null;
    }

    /**
     * 获取所有的角色
     *
     * @param needPermission 是否返回角色下的权限值列表
     * @return
     */
    @Override
    public Result<List<Role>> getAllRole(Boolean needPermission) {

        Date startTime = DateUtil.now();
        Result<List<Role>> result = new Result<List<Role>>();
        try {
            result.setData(adminSessionManager.getAllRole(needPermission));
        }catch (ServiceException e){
            CommonUtil.error(e.getMessage());
            result.getMsg().add(e.getMessage());
            result.setSuccess(false);
        }catch (Exception e) {
            CommonUtil.error(e.toString());
            result.getMsg().add("系统异常");
            result.setSuccess(false);
        }finally {
            Date endTime = DateUtil.now();
            result.setTimeCount(endTime.getTime() - startTime.getTime());
            return result;
        }
    }


    @Override
    public Result<Role> getRoleByRoleId(Long roleId) {

        Date startTime = DateUtil.now();
        Result<Role> result = new Result<Role>();
        try {
            result.setData(adminSessionManager.getRoleById(roleId));
        }catch (ServiceException e){
            CommonUtil.error(e.getMessage());
            result.getMsg().add(e.getMessage());
            result.setSuccess(false);
        }catch (Exception e) {
            CommonUtil.error(e.toString());
            result.getMsg().add("系统异常");
            result.setSuccess(false);
        }finally {
            Date endTime = DateUtil.now();
            result.setTimeCount(endTime.getTime() - startTime.getTime());
            return result;
        }
    }
}
