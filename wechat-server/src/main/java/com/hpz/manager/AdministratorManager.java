package com.hpz.manager;

import com.hpz.out.model.Administrator;

import java.util.List;

/**
 * Created by mao on 2015/7/28.
 */
public interface AdministratorManager {

    /**
     * 获得所有的洽谈人员
     * @return
     */
    public List<Administrator> getAllAdministrator();

    /***
     * 增加一个后台管理员
     * @param admin
     * @return
     */
    public Administrator addAdmin(Administrator admin);

    /**
     * 修改
     * @param admin
     * @return
     */
    public Administrator updateAdmin(Administrator admin);

    public Administrator getAdministratorById(Long id);
}
