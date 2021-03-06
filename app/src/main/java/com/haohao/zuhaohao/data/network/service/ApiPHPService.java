package com.haohao.zuhaohao.data.network.service;

import com.haohao.zuhaohao.ui.module.account.model.GameBean;
import com.haohao.zuhaohao.ui.module.base.BaseDataResponse;
import com.haohao.zuhaohao.ui.module.main.model.GameTypeBean;

import java.util.List;

import io.reactivex.Flowable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * PHP提供的部分接口
 * date：2017/2/16 11:57
 * author：Seraph
 *
 **/
public interface ApiPHPService {

    String BASE_URL = "http://rest-api.zuhaohao.com/appxb/";

    String TEST_URL = "http://restapi.test.zuhaohao.com/appxb/";


    //获取热门搜索
    @POST("appconfig/topSearch")
    @FormUrlEncoded
    Flowable<BaseDataResponse<List<GameBean>>> appconfigTopSearch(@Field("appKey") String appKey);

    //获取租号列表
    @POST("appconfig/rentPage")
    @FormUrlEncoded
    Flowable<BaseDataResponse<List<GameTypeBean>>> appconfigRentPage(@Field("appKey") String appKey);
}
