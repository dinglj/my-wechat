package com.hpz.remote.service.impl;

import com.hpz.common.CommonUtil;
import com.hpz.common.DateUtil;
import com.hpz.common.ServiceException;
import com.hpz.manager.AdministratorManager;
import com.hpz.out.model.Administrator;
import com.hpz.out.model.Result;
import com.hpz.remote.service.AdministratorService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;
import java.util.List;

/**
 * Created by mao on 2015/7/28.
 */
public class AdministratorServiceImpl implements AdministratorService {
    @Autowired
    private AdministratorManager administratorManager;




    /**
     * 获得所有的洽谈人员
     * @return
     */
    @Override
    public Result<List<Administrator>> getAllAdministrator() {
        Date startTime = DateUtil.now();
        Result<List<Administrator>> listResult = new Result<List<Administrator>>();
        try {
            listResult.setData(administratorManager.getAllAdministrator());
        }catch (ServiceException e) {
            CommonUtil.error(e.getMessage());
            listResult.getMsg().add(e.getMessage());
            listResult.setSuccess(false);
            e.printStackTrace();
        }catch(Exception e) {
            CommonUtil.error(e.toString());
            listResult.getMsg().add("系统异常");
            listResult.setSuccess(false);
            e.printStackTrace();
        }finally {
            Date endTime= DateUtil.now();
            listResult.setTimeCount(endTime.getTime()-startTime.getTime());
            return listResult;
        }
    }

    /**
     * 增加一个后台管理员
     *
     * @param administrator
     * @return
     */
    @Override
    public Result<Administrator> addAdministrator(Administrator administrator) {
        Date startTime = DateUtil.now();
        Result<Administrator> listResult = new Result<Administrator>();
        try {
            listResult.setData(administratorManager.addAdmin(administrator));
        }catch (ServiceException e) {
            CommonUtil.error(e.getMessage());
            listResult.getMsg().add(e.getMessage());
            listResult.setSuccess(false);
            e.printStackTrace();
        }catch(Exception e) {
            CommonUtil.error(e.toString());
            listResult.getMsg().add("系统异常");
            listResult.setSuccess(false);
            e.printStackTrace();
        }finally {
            Date endTime= DateUtil.now();
            listResult.setTimeCount(endTime.getTime()-startTime.getTime());
            return listResult;
        }
    }

    @Override
    public Result updateAdministrator(Administrator administrator) {
        Date startTime = DateUtil.now();
        Result<Administrator> listResult = new Result<Administrator>();
        try {
            listResult.setData(administratorManager.updateAdmin(administrator));
        }catch (ServiceException e) {
            CommonUtil.error(e.getMessage());
            listResult.getMsg().add(e.getMessage());
            listResult.setSuccess(false);
            e.printStackTrace();
        }catch(Exception e) {
            CommonUtil.error(e.toString());
            listResult.getMsg().add("系统异常");
            listResult.setSuccess(false);
            e.printStackTrace();
        }finally {
            Date endTime= DateUtil.now();
            listResult.setTimeCount(endTime.getTime()-startTime.getTime());
            return listResult;
        }
    }

    @Override
    public Result getAdministratorById(Long id) {
        Date startTime = DateUtil.now();
        Result<Administrator> listResult = new Result<Administrator>();
        try {
            listResult.setData(administratorManager.getAdministratorById(id));
        }catch (ServiceException e) {
            CommonUtil.error(e.getMessage());
            listResult.getMsg().add(e.getMessage());
            listResult.setSuccess(false);
            e.printStackTrace();
        }catch(Exception e) {
            CommonUtil.error(e.toString());
            listResult.getMsg().add("系统异常");
            listResult.setSuccess(false);
            e.printStackTrace();
        }finally {
            Date endTime= DateUtil.now();
            listResult.setTimeCount(endTime.getTime()-startTime.getTime());
            return listResult;
        }
    }
}
