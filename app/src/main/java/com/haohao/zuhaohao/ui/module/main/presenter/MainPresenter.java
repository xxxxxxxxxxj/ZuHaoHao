package com.haohao.zuhaohao.ui.module.main.presenter;

import android.os.Bundle;

import com.alibaba.android.arouter.launcher.ARouter;
import com.blankj.utilcode.util.ActivityUtils;
import com.blankj.utilcode.util.SPUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.haohao.zuhaohao.AppConfig;
import com.haohao.zuhaohao.AppConstants;
import com.haohao.zuhaohao.data.db.help.UserBeanHelp;
import com.haohao.zuhaohao.data.network.service.ApiCommonService;
import com.haohao.zuhaohao.ui.module.account.model.GameBean;
import com.haohao.zuhaohao.ui.module.main.contract.MainContract;

import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;

/**
 * mian逻辑处理层
 * date：2017/2/15 11:27
 * author：Seraph
 **/
public class MainPresenter extends MainContract.Presenter {

    private int position = 0;

    private UserBeanHelp userBeanHelp;

    private ApiCommonService apiCommonService;

    private SPUtils spUtils;

    @Inject
    MainPresenter(ApiCommonService apiCommonService, UserBeanHelp userBeanHelp) {
        this.userBeanHelp = userBeanHelp;
        this.apiCommonService = apiCommonService;
        spUtils = SPUtils.getInstance(AppConstants.SPAction.SP_NAME);
    }

    /**
     * 数据保存的KEY
     */
    private static final String MAIN_SAVE_KEY = "showIndex";


    @Override
    public void start() {
    }

    //跳转订单
    public void doSelectOrder() {
        ARouter.getInstance().build(AppConstants.PagePath.ORDER_ALL).navigation();
    }

    //转跳租号界面
    public void doSelectLease() {
        //游戏列表
        ARouter.getInstance().build(AppConstants.PagePath.ACC_LIST).withSerializable("bean", new GameBean(AppConfig.GAME_ID,AppConfig.GAME_NAME)).navigation();
    }

    /**
     * 设置选中的碎片
     */
    public void setSelectedFragment(int positionIndex) {
        position = positionIndex;
    }


    public void onBackPressed() {
        doublePressBackToast();
    }

    public void onSaveInstanceState(Bundle outState) {
        //保存停留的页面
        outState.putInt(MAIN_SAVE_KEY, position);
    }


    /**
     * 判断是否已经点击过一次回退键
     */
    private boolean isBackPressed = false;

    private void doublePressBackToast() {
        if (!isBackPressed) {
            isBackPressed = true;
            ToastUtils.showShort("再按一次退出程序");
        } else {
            ActivityUtils.finishAllActivities();
        }
        Observable.timer(2, TimeUnit.SECONDS)
                .subscribeOn(AndroidSchedulers.mainThread())
                .as(mView.bindLifecycle())
                .subscribe(aLong -> isBackPressed = false);
    }

    //打开兑换中心
    public void doOpenWelfare() {
        spUtils.put(AppConstants.SPAction.IS_OPEN_WELFARE, true);
        ARouter.getInstance().build(AppConstants.PagePath.USER_REDEMPTIONCENTER).navigation();
    }


//    //获取随机金额
//    private void doRandomAmount() {
//        //判断是否登录
//        if (!userBeanHelp.isLogin()) {
//            return;
//        }
//        //如果有可以拆的红包
//        Flowable.intervalRange(0, 1, 3000, 3000, TimeUnit.MILLISECONDS)
//                .compose(RxSchedulers.io_main())
//                .as(mView.bindLifecycle())
//                .subscribe(o -> mView.showRedAmountDialog());
//    }
//
//
//    //请求网络拆红包
//    public void doOpenRed() {
//        Flowable.intervalRange(0, 1, 1000, 1000, TimeUnit.MILLISECONDS)
//                .compose(RxSchedulers.io_main())
//                //  .doOnSubscribe(subscription -> mView.showLoading().setOnDismissListener(dialog -> subscription.cancel()))
//                .as(mView.bindLifecycle())
//                .subscribe(aLong -> {
//                    mView.hideLoading();
//                    mView.showRedAmountDialogValue(0.5);
//                });
//    }

}
