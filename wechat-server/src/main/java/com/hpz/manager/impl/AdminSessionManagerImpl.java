package com.hpz.manager.impl;

import com.hpz.common.ServiceException;
import com.hpz.dao.AdminSessionDao;
import com.hpz.dao.AdministratorDao;
import com.hpz.dao.PermissionDao;
import com.hpz.dao.RoleDao;
import com.hpz.manager.AdminSessionManager;
import com.hpz.out.model.*;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * Created by mao on 2015/8/4.
 */
@Transactional
@Service
public class AdminSessionManagerImpl implements AdminSessionManager {

    @Autowired
    private AdminSessionDao adminSessionDao;
    @Autowired
    private AdministratorDao administratorDao;
    @Autowired
    private PermissionDao permissionDao;
    @Autowired
    private RoleDao roleDao;

    @Override
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED)
    public AdminSession add(AdminSession adminSession) {
//        adminSession.setAccessTime(sessionDao.getSystemTime());
        if (adminSessionDao.add(adminSession) < 1) {
            throw new ServiceException("添加管理员登录记录失败");
        }
        return adminSession;
    }

    @Override
    public AdminSession login(String ipAddress, String cellphone, String password) {
        if (cellphone == null || password == null) {
            throw new ServiceException("手机号码或者密码为空");
        }
        Administrator administrator = administratorDao.getByCellphone(cellphone);
        if (administrator == null) {
            throw new ServiceException("不存在该用户");
        }
        if (password == null || !password.equals(administrator.getPassword())) {
            throw new ServiceException("用户密码错误");
        }
        AdminSession adminSession = new AdminSession();
        adminSession.setAccessIp(ipAddress);
        adminSession.setAdministrator(administrator);
        return add(adminSession);
    }

    /**
     * 通过管理员的id获取到当前管理员的角色对应的所有权限
     *
     * @param id
     * @return
     */
    @Override
    public HashSet<String> getPermissionsKeyByUid(Long id) {
        Administrator admin = administratorDao.getById(id);
        Role role = admin.getRole();
        HashSet<String> hashSetKeys = new HashSet<>();
        if (role == null || role.getId() == null || role.getId() == 0) {
            return null;
        }
        List<Permission> permissions = permissionDao.getPermissonByRoleId(role.getId());
        if (permissions != null && permissions.size() > 0) {
            for (Permission p : permissions) {
                String key = p.getKey();
                if (StringUtils.isNotBlank(key)) {
                    hashSetKeys.add(key);
                }
            }
            return hashSetKeys;
        } else {
            return null;
        }
    }

    /**
     * 获取所有的角色
     *
     * @param needPermissions 是否返回权限列表
     * @return
     */
    @Override
    public List<Role> getAllRole(boolean needPermissions) {
        List<Role> listRoles = permissionDao.getAllRole();
        if (listRoles == null || listRoles.size() == 0) {
            throw new ServiceException("没有角色数据");
        }
        if (needPermissions) {
            for (Role r : listRoles) {
                Long id = r.getId();
                List<Permission> listPermissions = permissionDao.getPermissonByRoleId(id);
                r.setPermissionList(listPermissions);
            }
        }
        return listRoles;
    }


    /**
     * 获取角色
     *
     * @param
     * @return
     */
    @Override
    public Role getRoleById(Long  roleId) {
        Role role = permissionDao.getRoleById(roleId);
        if (role == null || role.getId() == 0) {
            throw new ServiceException("没有角色");
        }
                Long id = role.getId();
                List<Permission> listPermissions = permissionDao.getPermissonByRoleId(id);
        role.setPermissionList(listPermissions);
        return role;
    }

    /**
     * 获取分组的权限值
     *
     * @return
     */
    @Override
    public List<PermissionGroup> getGroupedPermission() {
        List<PermissionGroup> group = PermissionGroup.getPermissionGroups();

        List<Permission> allPermissions = permissionDao.getPermissionsOrderGroup();

        Map<Long, List<Permission>> permissionmap = new HashMap<>();
        Long currentGroupId = 1l;

        int order = 0;
        List<Permission> permissions = new ArrayList<>();
        for (Permission p : allPermissions) {
            if (p.getGroupId() == currentGroupId) {
                permissions.add(p);
            } else {

                permissionmap.put(currentGroupId, permissions);
                currentGroupId = p.getGroupId();
                permissions = new ArrayList<>();
                permissions.add(p);
            }
            if (order == allPermissions.size() - 1) {
                permissionmap.put(currentGroupId, permissions);
            }
            order++;
        }

        List<Integer> toDeleteGroupId = new ArrayList<>();

        int s = 0;
        for (PermissionGroup pg : group) {
            if (permissionmap.containsKey(pg.getId())) {
                pg.setPermissions(permissionmap.get(pg.getId()));
            } else {
                toDeleteGroupId.add(s);
            }
            s++;
        }

        if (toDeleteGroupId.size() > 0) {
            for (Integer i : toDeleteGroupId) {
                group.remove((int) i);
            }

        }


        return group;
    }

    /**
     * 新增用户角色一个
     *
     * @param roleName
     * @param permissionIds
     * @return
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED)
    public Role newRole(String roleName, List<String> permissionIds) {
        if (StringUtils.isBlank(roleName)) {
            throw new ServiceException("角色名错误");
        }

        if (permissionIds == null || permissionIds.size() == 0) {
            throw new ServiceException("权限值不能为空");
        }
        Role role = new Role();
        role.setName(roleName);
        int addRoleCount = roleDao.addRole(role);
        if (addRoleCount <= 0 || role.getId() == null || role.getId() <= 0) {
            throw new ServiceException("新增角色失败");
        }

        updateRolePermissions(role, permissionIds);

        return role;
    }

    /**
     * 更新角色对应的权限值
     *
     * @param role
     * @param permission
     */
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED)
    public void updateRolePermissions(Role role, List<String> permission) {
        List<Permission> exsitsPermission = permissionDao.getPermissonByRoleId(role.getId());
        if (exsitsPermission != null && exsitsPermission.size() > 0) {
            int deleteExist = permissionDao.deletePermissionsByRoleId(role.getId());
        }

        List<RolePermission> roles = new ArrayList<>();
        for(String pid:permission){
            RolePermission rp = new RolePermission();
            rp.setRoleId(role.getId());
            rp.setPermissionId(Long.valueOf(pid));
            roles.add(rp);
        }

        int insertCount  = permissionDao.newpermissions(roles);
        if(insertCount <= 0){
            throw new ServiceException("匹配角色和权限失败");
        }

    }


}
