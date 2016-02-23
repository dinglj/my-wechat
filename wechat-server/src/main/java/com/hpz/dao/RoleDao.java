package com.hpz.dao;

import com.hpz.out.model.Role;

/**
 * Created by tommy on 15/9/6.
 */
public interface RoleDao {

    /***
     * 增加一个角色
     * @param role
     * @return
     */
    public int addRole(Role role);
}
