package com.haohao.zuhaohao.data.network.service;

import com.haohao.zuhaohao.ui.module.base.BaseDataResponse;
import com.haohao.zuhaohao.ui.module.login.model.UserBean;

import io.reactivex.Flowable;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * 新的用户中心的重构完成的接口
 * date：2017/2/16 11:57
 * author：Seraph
 **/
public interface ApiPassportService {

    String BASE_URL = "https://passport-server.zuhaohao.com/";

    String TEST_URL = "http://passport-server.test.zuhaohao.com/";

    String PRD_URL = "http://passport-server.xubei-prd.com/";

    //登录
    @GET("login/toLogin")
    Flowable<BaseDataResponse<String>> toLogin(@Query("userName") String userName, @Query("pwd") String pwd, @Query("loginWay") String loginWay);

    //第三方登录
    @GET("app/login/login/toLoginByOpenId")
    Flowable<BaseDataResponse<UserBean>> toLoginByOpenId(@Query("openId") String openId, @Query("unionId") String unionId);

    //第三方绑定手机
    @GET("app/login/bind")
    Flowable<BaseDataResponse<String>> bind(@Query("openId") String openId, @Query("unionId") String unionId, @Query("mobile") String mobile, @Query("code") String code, @Query("source") String source, @Query("channel") String channel);

    //短信登录
    @GET("sms/user/login")
    Flowable<BaseDataResponse<UserBean>> toSMSLogin(@Query("mobile") String mobile, @Query("code") String code, @Query("businessNo") String businessNo);

    //注册
    @GET("reg/registerUser")
    Flowable<BaseDataResponse<UserBean>> registerUser(@Query("mobile") String mobile, @Query("loginPwd") String loginPwd, @Query("code") String code, @Query("channel") String channel);

    //找回登录密码
    @GET("reg/changeLoginPwdExt")
    Flowable<BaseDataResponse<String>> changeLoginPwdExt(@Query("mobile") String mobile, @Query("loginPwd") String loginPwd, @Query("code") String code);
}
