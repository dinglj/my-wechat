package com.hpz.manager.impl;

import com.hpz.common.ServiceException;
import com.hpz.dao.AdministratorDao;
import com.hpz.manager.AdministratorManager;
import com.hpz.out.model.Administrator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by mao on 2015/7/28.
 */
@Service
public class AdministratorManagerImpl implements AdministratorManager {
    @Autowired
    private AdministratorDao administratorDao;

    /**
     * 获得所有的洽谈人员
     * @return
     */
    @Override
    public List<Administrator> getAllAdministrator() {
        return  administratorDao.getAllAdministrator();
    }

    /**
     * 增加一个后台管理员
     *
     * @param admin
     * @return
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED,isolation = Isolation.READ_COMMITTED)
    public Administrator addAdmin(Administrator admin) {
        Administrator ad = administratorDao.getByCellphone(admin.getCellphone());
        if(ad != null && ad.getId() >0){
            throw new ServiceException("已存在手机号为["+admin.getCellphone()+"]的后台管理员");
        }

        int i = administratorDao.addAdmin(admin);
        if(i <= 0){
            throw new ServiceException("新增用户失败[Name:"+admin.getName()+";cellPhone:"+admin.getCellphone()+"]");
        }

        if(admin.getId() <= 0){
            throw new ServiceException("新增用户失败[Name:"+admin.getName()+";cellPhone:"+admin.getCellphone()+"]");
        }
        return admin;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED,isolation = Isolation.READ_COMMITTED)
    public Administrator updateAdmin(Administrator admin) {
        if(admin==null)throw new ServiceException("Administrator对象为空!");

        //01 根据id 判断当前要修改的手机号和数据库的手机号是否一样
        Administrator dbAdmin=this.administratorDao.getById(admin.getId());

        //02 一样继续更新操作
        if(admin.getCellphone()!=null&&!"".equals(admin.getCellphone())&&dbAdmin.getCellphone()!=null&&!"".equals(dbAdmin.getCellphone())){
            if(admin.getCellphone().equals(dbAdmin.getCellphone())){
                if(administratorDao.updateAdmin(admin)<1) {
                    throw new ServiceException("更新administrator表失败");
                }
                return admin;
            }
        }
        //03 不一样 根据手机号去查找是否有已注册的手机号
        Administrator ad = administratorDao.getByCellphone(admin.getCellphone());
        if(ad != null && ad.getId() >0){
            throw new ServiceException("已存在手机号为["+admin.getCellphone()+"]的后台管理员");
        }
        int i = administratorDao.updateAdmin(admin);
        if(i <= 0){
            throw new ServiceException("修改用户失败");
        }
        return admin;
    }

    @Override
    public Administrator getAdministratorById(Long id) {
        if(id==null||id==0)throw new ServiceException("用户id为空");

        return this.administratorDao.getById(id);
    }
}
