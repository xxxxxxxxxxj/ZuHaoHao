package com.haohao.zuhaohao.ui.module.login.model;

import java.io.Serializable;

/**
 * 登录用户bean
 * date：2017/12/6 18:35
 * author：Seraph
 **/
public class UserBean implements Serializable {

    public String userId;

    public String imgUrl;

    public String mobile;

    public String nickName;

    public String qq;

    public String userName;

    public String authorization;

    public UserBean(String userId, String imgUrl, String mobile, String nickName, String qq, String userName, String authorization) {
        this.userId = userId;
        this.imgUrl = imgUrl;
        this.mobile = mobile;
        this.nickName = nickName;
        this.qq = qq;
        this.userName = userName;
        this.authorization = authorization;
    }
}
