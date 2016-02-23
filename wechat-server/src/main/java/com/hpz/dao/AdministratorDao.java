package com.hpz.dao;

import com.hpz.out.model.Administrator;

import java.util.List;

/**
 * Created by mao on 2015/7/24.
 */
public interface AdministratorDao {
    /**
     * 获得所有后台管理员
     * @return
     */
    public List<Administrator> getAllAdministrator();

    public Administrator getById(Long id);

    public Administrator getByCellphone(String cellphone);

    public Administrator getByAliwangwang(String aliwangwang);

    public int addAdmin(Administrator admin);

    /**
     * 修改
     * @param admin
     * @return
     */
    public int updateAdmin(Administrator admin);
}
