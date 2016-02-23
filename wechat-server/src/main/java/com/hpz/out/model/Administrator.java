package com.hpz.out.model;

import com.hpz.out.model.enumClass.SexType;

import java.io.Serializable;
import java.util.Date;

/**
 * 商家客服（商家店小二）
 * Created by mao on 2015/7/24.
 */
public class Administrator implements Serializable{
    private Long id;
    private String name;
    private String password;
    private String cellphone;
    private String bandingEmail;
    private String headPicUrl;
    private SexType sex;
    private Date birthday;
    private String realName;
    private String idCardNo;
    private String QQ;
    private String aliwangwang;
    private String liveAddress;
    private Role role;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getCellphone() {
        return cellphone;
    }

    public void setCellphone(String cellphone) {
        this.cellphone = cellphone;
    }

    public String getBandingEmail() {
        return bandingEmail;
    }

    public void setBandingEmail(String bandingEmail) {
        this.bandingEmail = bandingEmail;
    }

    public String getHeadPicUrl() {
        return headPicUrl;
    }

    public void setHeadPicUrl(String headPicUrl) {
        this.headPicUrl = headPicUrl;
    }

    public SexType getSex() {
        return sex;
    }

    public void setSex(SexType sex) {
        this.sex = sex;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getIdCardNo() {
        return idCardNo;
    }

    public void setIdCardNo(String idCardNo) {
        this.idCardNo = idCardNo;
    }

    public String getQQ() {
        return QQ;
    }

    public void setQQ(String QQ) {
        this.QQ = QQ;
    }

    public String getAliwangwang() {
        return aliwangwang;
    }

    public void setAliwangwang(String aliwangwang) {
        this.aliwangwang = aliwangwang;
    }

    public String getLiveAddress() {
        return liveAddress;
    }

    public void setLiveAddress(String liveAddress) {
        this.liveAddress = liveAddress;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}
