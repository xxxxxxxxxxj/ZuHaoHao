package com.haohao.zuhaohao.ui.module.main.presenter;

import com.blankj.utilcode.util.ToastUtils;
import com.haohao.zuhaohao.AppConstants;
import com.haohao.zuhaohao.data.db.help.UserBeanHelp;
import com.haohao.zuhaohao.data.network.rx.RxSchedulers;
import com.haohao.zuhaohao.data.network.service.Api8Service;
import com.haohao.zuhaohao.data.network.service.ApiUserNewService;
import com.haohao.zuhaohao.ui.module.base.ABaseSubscriber;
import com.haohao.zuhaohao.ui.module.main.contract.MainMeContract;
import com.haohao.zuhaohao.ui.module.user.model.AcctManageBean;
import com.hwangjr.rxbus.RxBus;

import javax.inject.Inject;

/**
 * 我的逻辑处理层
 * date：2017/2/15 11:27
 * author：Seraph
 **/
public class MainMePresenter extends MainMeContract.Presenter {


    private UserBeanHelp userBeanHelp;

    private ApiUserNewService apiUserService;

    private Api8Service api8Service;

    @Inject
    MainMePresenter(UserBeanHelp userBeanHelp, ApiUserNewService apiUserService, Api8Service api8Service) {
        this.userBeanHelp = userBeanHelp;
        this.apiUserService = apiUserService;
        this.api8Service = api8Service;
    }

    @Override
    public void start() {
    }

    public boolean isLogin() {
        return userBeanHelp.isLogin(true);
    }

    public void updateUserBean() {
        RxBus.get().post(AppConstants.RxBusAction.TAG_MAIN_ME2, true);
        if (userBeanHelp.getUserBean() != null) {
            doAccountInfo();
        }
    }

    //获取账号资金信息
    private void doAccountInfo() {
        apiUserService.acctManageIndex(userBeanHelp.getAuthorization())
                .compose(RxSchedulers.io_main_business())
                .as(mView.bindLifecycle())
                .subscribe(new ABaseSubscriber<AcctManageBean>(mView) {
                    @Override
                    protected void onSuccess(AcctManageBean acctManageBean) {
                        if (mView == null || acctManageBean == null) {
                            return;
                        }
                        //发通知告诉我的界面资金数据更新
                        RxBus.get().post(AppConstants.RxBusAction.TAG_MAIN_ME, acctManageBean);
                    }

                    @Override
                    protected void onError(String errStr) {
                        ToastUtils.showShort(errStr);
                    }
                });
    }
}
