package com.haohao.zuhaohao.data.network.service;

import com.haohao.zuhaohao.ui.module.account.model.AccBean;
import com.haohao.zuhaohao.ui.module.account.model.AccRSResultBean;
import com.haohao.zuhaohao.ui.module.account.model.GameActivityBean;
import com.haohao.zuhaohao.ui.module.account.model.GameAllAreaBean;
import com.haohao.zuhaohao.ui.module.account.model.GameAreaBean;
import com.haohao.zuhaohao.ui.module.account.model.GameConfigBean;
import com.haohao.zuhaohao.ui.module.account.model.GameSearchRelationBean;
import com.haohao.zuhaohao.ui.module.account.model.GoldBlBean;
import com.haohao.zuhaohao.ui.module.account.model.OutGoodsDetailBean;
import com.haohao.zuhaohao.ui.module.account.model.ShareMianFeiBean;
import com.haohao.zuhaohao.ui.module.base.BaseData;
import com.haohao.zuhaohao.ui.module.base.BaseDataCms;
import com.haohao.zuhaohao.ui.module.base.BaseDataResponse;
import com.haohao.zuhaohao.ui.module.main.model.BannerBean;
import com.haohao.zuhaohao.ui.module.main.model.NoticeBean;
import com.haohao.zuhaohao.ui.module.main.model.WelfareBean;
import com.haohao.zuhaohao.ui.module.setting.model.ProblemBean;
import com.haohao.zuhaohao.ui.module.setting.model.QuestionBean;
import com.haohao.zuhaohao.ui.module.welcome.model.ADBean;

import java.util.HashMap;
import java.util.List;

import io.reactivex.Flowable;
import okhttp3.RequestBody;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;

/**
 * 帮助中心
 * date：2017/2/16 11:57
 * author：Seraph
 **/
public interface Api8Service {

    String BASE_URL = "https://api.zuhaohao.com/";

    String TEST_URL = "http://api.test.zuhaohao.com";//"http://api8.fulu-test.com/";

    String PRD_URL = "http://api.fulu-prd.com/";

    //欢迎页广告
    @GET("fulu-page-cloud/anon/cms/getModDataByModId?modId=welcome_ad")
    Flowable<BaseDataResponse<BaseData<BaseDataCms<ADBean>>>> getWelcomeAd();

    //广告页点击
    @GET("fulu-page-cloud/anon/cms/pageModuleData/updateClickCount")
    Flowable<BaseDataResponse<String>> updateClickCount(@Query("dataId") String dataId);

    //首页banner
    @GET("fulu-page-cloud/anon/cms/getModDataByModId?modId=home_banner")
    Flowable<BaseDataResponse<BaseData<BaseDataCms<BannerBean>>>> getHomeBanner();

    //热销榜单
    @GET("fulu-page-cloud/anon/cms/getModDataByModId?modId=hot_list")
    Flowable<BaseDataResponse<BaseData<BaseDataCms<AccBean>>>> getHotList();

    //福利中心
    @GET("fulu-page-cloud/anon/cms/getModDataByModId?modId=welfare_center")
    Flowable<BaseDataResponse<BaseData<BaseDataCms<WelfareBean>>>> getWelfareCenter();

    //免费试玩助力
    @GET("fulu-page-cloud/anon/cms/getModDataByModId?modId=fulu_share_mianfei_wenan")
    Flowable<BaseDataResponse<BaseData<BaseDataCms<ShareMianFeiBean>>>> getShareMianfeiWenan();

    //金币比例
    @GET("fulu-page-cloud/anon/cms/getModDataByModId?modId=gongyong_jinbipeizhi")
    Flowable<BaseDataResponse<BaseData<BaseDataCms<GoldBlBean>>>> getJinbipeizhi();

    //帮助中心列表
    @GET("fulu-common-cloud/anon/helpCenter/categoryList")
    Flowable<BaseDataResponse<List<ProblemBean>>> categoryList();

    //帮助类型列表
    @GET("fulu-common-cloud/anon/helpCenter/getArticleByMenuId")
    Flowable<BaseDataResponse<List<QuestionBean>>> getArticleByMenuId(@Query("menuId") String menuId);

    //帮助文章详情
    @GET("fulu-common-cloud/anon/helpCenter/getArticleDetail")
    Flowable<BaseDataResponse<List<WelfareBean>>> getArticleDetail(@Query("articleId") String articleId);

    //获取推送消息1:所有装机用户,2号号用户,3:指定用户
    @GET("fulu-common-web/anon/message/push/getUserMessage")
    Flowable<BaseDataResponse<BaseData<NoticeBean>>> getUserMessage(@Header("Authorization") String authorization, @Query("type") String type, @Query("pageNo") Integer pageNo, @Query("pageSize") Integer pageSize);

    //更新用户消息已读状态
    @GET("fulu-common-web/anon/message/push/updateStatus")
    Flowable<BaseDataResponse<String>> updateStatus(@Header("Authorization") String authorization, @Query("messageId") Long messageId);

    //查询是否有未读消息
    @GET("fulu-common-web/anon/message/push/hasUnreadMessage")
    Flowable<BaseDataResponse<Boolean>> hasUnreadMessage(@Header("Authorization") String authorization);

    //获取游戏配置
    @GET("fulu-goods-cloud/anonapi/server/game/getPublishShowField")
    Flowable<BaseDataResponse<List<GameConfigBean>>> findGoodsConfig(@Query("serverId") String serverId, @Query("bigGameId") String bigGameId);

    //通过字段ID返回数据(select,radio,checkbox)
    @GET("fulu-goods-cloud/anonapi/server/gamewordscol/option/data")
    Flowable<BaseDataResponse<String>> optionData(@Query("fieldId") String serverId);

    //获取商品详情
    @GET("fulu-goods-cloud/anonapi/goodsDetail")
    Flowable<BaseDataResponse<OutGoodsDetailBean>> goodsDetail(@Query("goodsId") String goodsId, @Query("businessNo") String businessNo);

    //获取区服数据(所有)
    @GET("fulu-goods-cloud/anonapi/findGameAreaById")
    Flowable<BaseDataResponse<GameAllAreaBean>> findGameAreaById(@Query("id") String id);

    //获取区服数据(根据父节点查询子节点)
    @GET("fulu-goods-cloud/anonapi/findChdByGameParent")
    Flowable<BaseDataResponse<List<GameAreaBean>>> findChdByGameParent(@Query("parentId") Long parentId);

    //获取游戏活动
    @GET("fulu-goods-cloud/anonapi/findGoodsActity")
    Flowable<BaseDataResponse<List<GameActivityBean>>> findGoodsActity(@Query("gameId") String gameId);

    //获取账号列表
    @GET("fulu-goods-search/anon/findGoodsList")
    Flowable<BaseDataResponse<BaseData<AccBean>>> findGoodsList(@QueryMap HashMap<String, Object> map);

    //搜索游戏类目
    @Headers({"Content-Type: application/json", "Accept: application/json"})
    @POST("fulu-goods-cloud/anonapi/indexGameList")
    Flowable<BaseDataResponse<List<AccRSResultBean>>> indexGameList(@Body RequestBody requestBody);

    //游戏高级筛选
    @GET("fulu-goods-cloud/anonapi/getGameSearchRelation")
    Flowable<BaseDataResponse<List<GameSearchRelationBean>>> getGameSearchRelation(@Query("gameId") String gameId);


}
