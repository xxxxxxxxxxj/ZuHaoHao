package com.haohao.zuhaohao.ui.module.main.presenter;

import com.alibaba.android.arouter.launcher.ARouter;
import com.blankj.utilcode.util.ToastUtils;
import com.haohao.zuhaohao.AppConstants;
import com.haohao.zuhaohao.data.network.rx.RxSchedulers;
import com.haohao.zuhaohao.data.network.service.Api8Service;
import com.haohao.zuhaohao.ui.module.base.ABaseSubscriber;
import com.haohao.zuhaohao.ui.module.base.BaseData;
import com.haohao.zuhaohao.ui.module.base.BaseDataCms;
import com.haohao.zuhaohao.ui.module.main.contract.MainHomeContract;
import com.haohao.zuhaohao.ui.module.main.model.BannerBean;
import com.haohao.zuhaohao.ui.views.NoDataView;

import java.util.List;

import javax.inject.Inject;

/**
 * 首页逻辑处理层
 * date：2017/2/15 11:27
 * author：Seraph
 **/
public class MainHomePresenter extends MainHomeContract.Presenter {
    private Api8Service api8Service;

    @Inject
    MainHomePresenter(Api8Service api8Service) {
        this.api8Service = api8Service;
    }

    private List<BaseDataCms<BannerBean>> bannerList;

    @Override
    public void start() {
        doBanner();
    }

    //首页banner点击
    public void doBannerClick(int position) {
        BaseDataCms<BannerBean> bannerBean = bannerList.get(position);
        switch (bannerBean.properties.goto_type) {
            case "1"://游戏列表
                ARouter.getInstance().build(AppConstants.PagePath.ACC_LIST).withSerializable("bean", bannerBean.properties).navigation();
                break;
            case "2"://自定义url
                ARouter.getInstance().build(AppConstants.PagePath.COMM_AGENTWEB)
                        .withString("title", "详情")
                        .withString("webUrl", bannerBean.properties.goto_link)
                        .navigation();
                break;
        }
    }

    //获取banner图
    private void doBanner() {
        api8Service.getHomeBanner()
                .compose(RxSchedulers.io_main_business())
                .doOnSubscribe(subscription -> mView.setNoData(NoDataView.LOADING))
                .as(mView.bindLifecycle())
                .subscribe(new ABaseSubscriber<BaseData<BaseDataCms<BannerBean>>>() {
                    @Override
                    public void onSuccess(BaseData<BaseDataCms<BannerBean>> accountList) {
                        bannerList = accountList.datas;
                        mView.setGameList(bannerList);
                        mView.setNoData(NoDataView.LOADING_OK);
                    }

                    @Override
                    public void onError(String errStr) {
                        ToastUtils.showShort(errStr);
                        mView.setNoData(NoDataView.NET_ERR);
                    }
                });
    }
}
