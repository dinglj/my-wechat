package com.hpz.remote.service;

import com.hpz.out.model.Administrator;
import com.hpz.out.model.Result;

import java.util.List;

/**
 * Created by mao on 2015/7/28.
 */
public interface AdministratorService {

    /**
     * 获得所有的洽谈人员
     * @return
     */
    public Result<List<Administrator>> getAllAdministrator();


    /***
     * 增加一个后台管理员
     * @param administrator
     * @return
     */
    public Result<Administrator> addAdministrator(Administrator administrator);

    /**
     * 用户信息修改
     * @param administrator
     * @return
     */
    public Result updateAdministrator(Administrator administrator);

    public Result getAdministratorById(Long id);
}
